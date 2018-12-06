<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html;"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/main.css"/>
<style>
	select {
		width: 100px;
	}
</style>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
function change_sel() {
	
	var sdName = $("#sidoName option:checked").text();
	console.log(sdName);
	
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

function search() {
	var sidoName = $("#sidoName").val();
	var sigunguName = $("#sigunguName").val();
	
	$.ajax({
		type: "POST",
		url: "/search",
		dataType:"json",
		data: {sidoName:sidoName,sigunguName:sigunguName},
		success: function(result){
			console.log(1);
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert("오류가 발생하였습니다.");
		}                     
	});
}
</script>
</head>
<body>
	<table>
		<tr>
			<td>지역</td>
			<td>
				<select name="sidoName" id="sidoName" onchange="change_sel()">
					<option>-선택-</option>
					<c:if test="${!empty selectSidoName }">
						<c:forEach var="cd" items="${selectSidoName }" varStatus="i">
							<option value="${cd.sidoCode }"<c:if test="${cd.sidoName eq sidoName }">selected</c:if>>${cd.sidoName}</option>
						</c:forEach>	
					</c:if>
				</select>
			</td>
			<td>
				<select id="sigunguName" name="sigunguName">
					<option value='all'>-선택-</option>
				</select>
			</td>
			<td>
				<input type="button" name="btnSearch" id="btnSearch" value="조회" onclick="search()">
			</td>
		</tr>
	</table>
</body>
</html>