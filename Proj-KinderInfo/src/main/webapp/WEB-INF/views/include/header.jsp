<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/main.css"/>
</head>
<body>
	<div class="top_fix_zone" id="topBar">
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
	</div>
</body>
</html>