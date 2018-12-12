<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search News API</title>
</head>
<body>
	<div class="backdiv" id="header">
		<%@include file="../include/header.jsp"%>
	</div>
	<div id="divbox" style="width:300px; height:300px; border:1px solid;">
	</div>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
var sdName;
var sigunguName;
var dbox = $("#divbox");

//시도셀렉트박스 선택 시
function change_sel() {
	
	sdName = $("#sidoName option:checked").text();
	
	$.ajax({
		type: "POST",
		url: "/changeSel",
		dataType:"json",
		data: {param:sdName},
		success: function(result){
			$("#sigunguName").find("option").remove().end().append("<option value='all'>-선택-</option>");
			$.each(result, function(i) {
				$("#sigunguName").append("<option value='"+result[i].sigunguCode+"'>"+result[i].sigunguName+"</option>");
			});
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert("오류가 발생하였습니다.");
		}                     
	});
}

//조회버튼 함수
function search() {
	
	sigunguName = $("#sigunguName option:checked").text();
	var sidoCode = $("#sidoName").val();
	var sigunguCode = $("#sigunguName").val();
	
	$.ajax({
		type: "POST",
		url: "/ndata",
		dataType:"json",
		data: {sdName:sdName, sigunguName:sigunguName},
		success: function(jdata){
			console.log(jdata);
			for(var i=0; i<jdata.display; i++){
				dbox.text(jdata.items[i].title);
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert("오류가 발생하였습니다.");
		}                     
	});
}
</script>
</body>
</html>