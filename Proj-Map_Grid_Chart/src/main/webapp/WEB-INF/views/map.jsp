<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>유치원 정보 검색 사이트</title>
<style type="text/css">
	#realgrid {
		height: 650px;
		width: 50%;
		float: left;
	}
	#map {
		height: 650px;
		width: 50%;
		float: right;
	}
	#container {
		clear: both;
	}
</style>
</head>
<body>
	<div>
		지역 : 
		<select>
			<option>인천</option>
		</select>
		<select>
			<option>미추홀구</option>
		</select>
	</div>
	<div>
		조건 : 
		<input type="checkbox" name="chk1" value="통학차량">통학차량
		<input type="checkbox" name="chk1" value="급식">급식
		<input type="checkbox" name="chk1" value="국공립">국공립
		<input type="checkbox" name="chk1" value="사립">사립
		<input type="button" name="search" value="검색">
	</div>
	<div id="realgrid"></div>
	<div id="map"></div>
	<div>
		지역 정보
	</div>
	<div id="container"></div>
	<div id="highchart"></div>
	<!--naver map-->
	<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=EMVSTOQjBCVdZWa3CPxg&submodules=geocoder"></script>
	<!--real grid-->
	<script type="text/javascript" src="resources/realgridjs-lic.js"></script>
	<script type="text/javascript"
		src="resources/realgridjs_eval.1.1.29.min.js"></script>
	<script type="text/javascript" src="resources/realgridjs-api.1.1.29.js"></script>
	<script type="text/javascript" src="resources/jszip.min.js"></script>
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<!--high chart-->
	<script src="resources/highcharts.js"></script>
	<script src="resources/series-label.js"></script>
	<script src="resources/exporting.js"></script>
	<script src="resources/export-data.js"></script>
	<script>
		/*-----------
		  naver map
		-----------*/
		var jdata = ${jdata};
		var infowindows = [], markers = [], adds = [];

		var map = new naver.maps.Map('map', {
			center : new naver.maps.LatLng(37.44802, 126.6553154),
			zoom : 8
		});

		for (var i = 0; i < jdata.kinderInfo.length; i++) {
			var myaddress = jdata.kinderInfo[i].addr;
			naver.maps.Service.geocode({
				address : myaddress
			}, function(status, response) {
				if (status !== naver.maps.Service.Status.OK) {
					return alert(myaddress + '의 검색 결과가 없거나 기타 네트워크 에러');
				}
				var result = response.result;

				var marker = new naver.maps.Marker({
					position : new naver.maps.LatLng(
							result.items[0].point.y,
							result.items[0].point.x),
					map : map
				});

				markers.push(marker);
				adds.push(result.items[0].address);

				var infowindow = new naver.maps.InfoWindow();

				naver.maps.Event.addListener(marker, "click", function(e) {
					for(var j=0; j<markers.length; j++){
						if(markers[j].position === marker.position){
							for(var k=0; k<adds.length; k++){
								if(adds[j].match((jdata.kinderInfo[k].addr))){
									if (infowindow.getMap()) {
										infowindow.close();
									} else {
										infowindow.setContent(jdata.kinderInfo[k].kindername);
										infowindow.open(map, marker);
									}
								}
							}
						}
					}
				});
				
				
			});
		}

		/*-----------
		  real grid
		-----------*/
		var gridView;
		var dataProvider;

		$(document).ready(function() {

			RealGridJS.setTrace(false);
			RealGridJS.setRootContext("resources");

			dataProvider = new RealGridJS.LocalDataProvider();
			gridView = new RealGridJS.GridView("realgrid");
			gridView.setDataSource(dataProvider);

			//다섯개의 필드를 가진 배열 객체를 생성합니다.
			var fields = [ {
				fieldName : "kindername"
			}, {
				fieldName : "establish"
			}, {
				fieldName : "addr"
			}, {
				fieldName : "telno"
			}, {
				fieldName : "opertime"
			} ];
			
			//DataProvider의 setFields함수로 필드를 입력합니다.
			dataProvider.setFields(fields);

			//필드와 연결된 컬럼 배열 객체를 생성합니다.
			var columns = [ {
				name : "col1",
				fieldName : "kindername",
				header : {
					text : "유치원명"
				},
				width : 170
			}, {
				name : "col2",
				fieldName : "establish",
				header : {
					text : "구분"
				},
				width : 65
			}, {
				name : "col3",
				fieldName : "addr",
				header : {
					text : "주소"
				},
				width : 260
			}, {
				name : "col4",
				fieldName : "telno",
				header : {
					text : "전화번호"
				},
				width : 85
			}, {
				name : "col5",
				fieldName : "opertime",
				header : {
					text : "운영시간"
				},
				width : 115
			} ];

			//컬럼을 GridView에 입력 합니다.
			gridView.setColumns(columns);

			// json 형식 데이터 넣기
		    //var data = ${jdata};
		    dataProvider.setRows(${jdata}.kinderInfo);
		});
		
		/*-----------
		 high chart
		-----------*/
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

			series : ${cdata},

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
		
		Highcharts.chart('highchart', {

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

			series : ${cdata},

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
</body>
</html>