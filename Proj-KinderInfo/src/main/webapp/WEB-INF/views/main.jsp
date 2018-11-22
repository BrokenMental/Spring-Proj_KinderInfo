<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>유치원 정보 검색 사이트</title>
</head>
<body>
	<div class="backdiv" id="header">
		<%@include file="include/header.jsp"%>
	</div>
	<div class="backdiv" id="map">
		<div class="frontdiv" id="realgrid"></div>
		<div class="frontdiv" id="detaildiv">
			<p id="detailP">Menu</p>
			<!-- <div id="container"></div> -->
			<div id="highchart"></div>
		</div>
	</div>
	<!--response data start-->
	<script type="text/javascript">
		var jdata = ${jdata};
		var cdata = ${cdata};
		var crimedata = ${crimedata};
		var detv = document.getElementById("detaildiv");
	</script>
	<!--response data end-->

	<!--real grid start-->
	<script type="text/javascript" src="resources/realgridjs-lic.js"></script>
	<script type="text/javascript"
		src="resources/realgridjs_eval.1.1.29.min.js"></script>
	<script type="text/javascript" src="resources/realgridjs-api.1.1.29.js"></script>
	<script type="text/javascript" src="resources/jszip.min.js"></script>
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="resources/js/grid.js"></script>
	<!--real grid end-->

	<!--high chart start-->
	<script src="resources/highcharts.js"></script>
	<script src="resources/series-label.js"></script>
	<script src="resources/exporting.js"></script>
	<script src="resources/export-data.js"></script>
	<script src="resources/js/chart.js"></script>
	<!--high chart end-->

	<!--naver map start-->
	<script type="text/javascript"
		src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=EMVSTOQjBCVdZWa3CPxg&submodules=geocoder"></script>
	<script src="resources/js/map.js"></script>
	<!--naver map start end-->

	<!-- mapping code start -->
	<script src="resources/js/main_run.js"></script>
	<!-- mapping code end -->
</body>
</html>