<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/main.css"/>
</head>
<body>
	<div id="area">
		지역 : 
		<select>
			<option>인천</option>
		</select>
		<select>
			<option>미추홀구</option>
		</select>
	</div>
	<div id="condition">
		조건 : 
		<input type="checkbox" name="chk1" value="통학차량">통학차량
		<input type="checkbox" name="chk1" value="급식">급식
		<input type="checkbox" name="chk1" value="국공립">국공립
		<input type="checkbox" name="chk1" value="사립">사립
		<input type="button" name="search" value="검색">
	</div>
</body>
</html>