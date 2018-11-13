<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Naver Map</title>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=EMVSTOQjBCVdZWa3CPxg&submodules=geocoder"></script>
</head>
<body>
	<div id="map" style="width: 100%; height: 800px;"></div>
	<script>
		var jdata = ${jdata};
		var infowindows = [], markers = [], adds = [];
		var evt;

		var map = new naver.maps.Map('map', {
			center : new naver.maps.LatLng(37.44802, 126.6553154),
			zoom : 8
		});
		
		function formap(callback){
			for (var i = 0; i < jdata.kinderInfo.length; i++) {
				var myaddress = jdata.kinderInfo[i].addr;
				mapgo(myaddress);
			}
			console.log("abc11");
            callback();
		}
		
		function mapgo(myaddress, callback){
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
				gl(marker,infowindow);
	            callback();
			});
		}
		
		function gl(marker,infowindow) {
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
									/* for(var iw in infowindow.getContent()){
										console.log("infowindow : " + iw);
									} */
								}
							}
						}
					}
				}

			});
		}
		
		formap(function(){
			console.log("abc");
		});
		
	</script>
</body>
</html>