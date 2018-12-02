<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Charts Test</title>
<style type="text/css">
#container {
	min-width: 310px;
	max-width: 800px;
	height: 400px;
	margin: 0 auto;
}
</style>
</head>
<body>
	<div id="container"></div>
</body>
<script src="resources/highcharts.js"></script>
<script src="resources/series-label.js"></script>
<script src="resources/exporting.js"></script>
<script src="resources/export-data.js"></script>
<script type="text/javascript">
	var cdata = ${cdata};
	//console.log(cdata);
	Highcharts.chart('container', {

		title : {
			text : '인천 인구 유입률 2014-2018'
		},

		subtitle : {
			text : 'Source: 공공데이터 포털'
		},

		yAxis : {
	        floor: 0, // y축 최소값
	        ceiling: 7000, // y축 최대값
			title : {
				text : 'Year'
			}
		},
		legend : {
			layout : 'vertical',
			align : 'right',
			verticalAlign : 'middle'
		},

		plotOptions : {
			series : {
				label : {
					connectorAllowed : false
				},
				pointStart : 2014
			}
		},

		series : cdata,

		responsive : {
			rules : [ {
				condition : {
					maxWidth : 500
				},
				chartOptions : {
					legend : {
						layout : 'horizontal',
						align : 'center',
						verticalAlign : 'bottom'
					}
				}
			} ]
		}

	});
</script>
</html>