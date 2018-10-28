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
		var myaddress = '인천광역시 남구 학익1동 인하로 100'; // 도로명 주소나 지번 주소만 가능 (건물명 불가!!!!)
		var infowindows = [],
			markers = [];
		
		var map = new naver.maps.Map('map', {
			center : new naver.maps.LatLng(37.44802, 126.6553154),
			zoom : 8
		});
		
		for (var i = 0; i < jdata.kinderInfo.length; i++) {
			myaddress = jdata.kinderInfo[i].addr;
			naver.maps.Service.geocode({
				address : myaddress
			},function(status, response) {
				if (status !== naver.maps.Service.Status.OK) {
					return alert(myaddress + '의 검색 결과가 없거나 기타 네트워크 에러');
				}
				var result = response.result;
				// 검색 결과 갯수: result.total
				// 첫번째 결과 결과 주소: result.items[0].address
				// 첫번째 검색 결과 좌표: result.items[0].point.y, result.items[0].point.x
				
				var marker = new naver.maps.Marker({
					position : new naver.maps.LatLng(
							result.items[0].point.y,
							result.items[0].point.x),
					map : map
				});

				markers.push(marker);
				
				naver.maps.Event.addListener(marker, "click", function(e) {
				    if (infowindow.getMap()) {
				        infowindow.close();
				    } else {
				        infowindow.open(map, marker);
				    }
				});
				
				var infowindow = new naver.maps.InfoWindow({
				    content: '<h4>'+ myaddress +'</h4>'
				});
				
				infowindows.push(infowindow);
			});
		}
		
	</script>
</body>
</html>