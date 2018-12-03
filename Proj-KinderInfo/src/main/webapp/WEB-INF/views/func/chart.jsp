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

		xAxis : {
			categories: [2014, 2015, 2016, 2017, 2018],
	        //minRange: 3, // 데이터를 중심으로 총 몇개의 x 데이터를 표현할것인지 설정
        	gridLineWidth: 1, // 라인 굵기
        	gridZIndex: 4, // 구분선과 차트간의 거리
            //offset: 10 // 0의 y.bottom margin
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
				pointStart : 2014 // 시작지점, 시작지점이 정해져있으면 xAxis를 무시한다.
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