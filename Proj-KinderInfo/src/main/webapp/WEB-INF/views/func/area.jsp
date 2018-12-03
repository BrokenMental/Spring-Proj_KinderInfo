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
function change_sel(sidoName) {
	$.ajax({
		type: "POST",
		url: "/changeSel",
		dataType:"json",
		data: {param:sidoName},
		success: function(result){
			console.log(result);
			$("#sigunguName").find("option").remove().end().append("<option value='all'>-선택-</option>");
			
			$.each(result, function(i) {
				console.log(result[i]);
				$("#sigunguName").append("<option value='"+result[i]+"'>"+result[i]+"</option>");
			});
			
			/* for(var count = 0; count < result.size(); count++){                
            	var option = $("<option>"+result[count]+"</option>");
            	console.log(count);
                $('#sigunguName').append(option);
            } */
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
			<select name="sidoName" onchange="change_sel(this.value)">
				<option>-선택-</option>
				<c:if test="${!empty selectSidoName }">
					<c:forEach var="cd" items="${selectSidoName }" varStatus="i">
						<option value="${cd.sidoName }"<c:if test="${cd.sidoName eq sidoName }">selected</c:if>>${cd.sidoName}</option>
					</c:forEach>	
				</c:if>
			</select>
			</td>
			<td>
			<select id="sigunguName" name="sigunguName">
				<option value='all'>-선택-</option>
			</select>
			</td>
		</tr>
	</table>
</body>
</html>