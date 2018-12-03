<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Naver Map</title>
	<style>
		body{
			height: 800px;
		}
		
		#detaildiv {
			width: 99%;
			height: 300px;
			top: -305px;
			margin: auto;
			border: 1px solid;
			background-color: white;
			z-index: 500;
			position: relative;
		}
	</style>
</head>
<body>
	<div id="map" style="width: 100%; height: 800px;"></div>
	<div id="detaildiv"></div>
	<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=EMVSTOQjBCVdZWa3CPxg&submodules=geocoder"></script>
	<script>
		var jdata = ${jdata};
		var markers = [], adds = []; // markers : 피커 정보, adds : 주소

		var map = new naver.maps.Map('map', {
			center : new naver.maps.LatLng(37.44802, 126.6553154),
			zoom : 8
		});
		
		for (var i = 0; i < jdata.kinderInfo.length; i++) {
			var myaddress = jdata.kinderInfo[i].addr;
			mapgo(myaddress);
		}
		
		function mapgo(myaddress){
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
	
				var infowindow = new naver.maps.InfoWindow({
				    maxWidth: 300,
					borderWidth: 5,
					borderColor: "#2db400" //초록
				});
				
				naver.maps.Event.addListener(marker, "click", function(e) {
					for(var j=0; j<markers.length; j++){
						if(markers[j].position === marker.position){
							for(var k=0; k<adds.length; k++){
								if(adds[j].match((jdata.kinderInfo[k].addr))){
									if (infowindow.getMap()) {
										infowindow.close();
									} else {
							 		    map.setCenter(marker.position);
							 		    var contStrst = [
							 		    	'<div style="text-align:center;">',
							 		        '   <h4>'
							 		    ].join('');
							 		    var contStrce = [
							 		    	'</h4>',
							 		        '   <p>',
							 		        '		<input type="button" value="상세보기" onclick="'
							 		    ].join('');
							 		    var contStren = [
							 		    	'">',
							 		        '   </p>',
							 		        '</div>'
							 		    ].join('');
							 		    //console.log(typeof(jdata.kinderInfo[k].kindername));
							 		    //map.setZoom(8, true); // 다른 피커 선택 시 Zoom 레벨 변경하면 정보창 깨짐
										infowindow.setContent(contStrst + jdata.kinderInfo[k].kindername + contStrce + detailFunc(jdata.kinderInfo[k].kindername) + contStren);
										infowindow.open(map, marker);
										//for(var iw in infowindow.getContent()){
										//	console.log("infowindow : " + iw);
										//}
									}
								}
							}
						}
					}

				});
			});
		}
		
		var detv = document.getElementById("detaildiv");
		
		detv.style.display="none";
		function detailFunc(strv){
			if(detv.style.display=="block"){
				detv.style.display="none";
			}else{
				console.log("hi");
				detv.style.display="block";
				detv.innerHTML=strv;
			}
		}
		
	</script>
</body>
</html>