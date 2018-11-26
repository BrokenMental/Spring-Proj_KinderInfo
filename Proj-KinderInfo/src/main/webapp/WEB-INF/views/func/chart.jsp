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
	margin: 0 auto
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
	console.log(cdata);
	Highcharts.chart('container', {

		title : {
			text : 'Solar Employment Growth by Sector, 2010-2016'
		},

		subtitle : {
			text : 'Source: thesolarfoundation.com'
		},

		yAxis : {
			title : {
				text : 'Number of Employees'
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
				pointStart : 2010
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