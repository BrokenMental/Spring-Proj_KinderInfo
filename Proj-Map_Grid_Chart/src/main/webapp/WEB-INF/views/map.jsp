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
		
		var map = new naver.maps.Map('map', {
			center : new naver.maps.LatLng(37.44802,126.6553154),
			zoom: 8
		});
		
		for (var i = 0; i <= jdata.kinderInfo.length; i++) {
			myaddress = jdata.kinderInfo[i].addr;
			naver.maps.Service.geocode(
							{
								address : myaddress
							},
							function(status, response) {
								if (status !== naver.maps.Service.Status.OK) {
									return alert(myaddress
											+ '의 검색 결과가 없거나 기타 네트워크 에러');
								}
								var result = response.result;
								// 검색 결과 갯수: result.total
								// 첫번째 결과 결과 주소: result.items[0].address
								// 첫번째 검색 결과 좌표: result.items[0].point.y, result.items[0].point.x
								
								var myaddr = new naver.maps.Point(
										result.items[0].point.x,
										result.items[0].point.y);
								
								//map.setCenter(myaddr); // 검색된 좌표로 지도 이동
								
								// 마커 표시
								var marker = new naver.maps.Marker({
									position : myaddr,
									map : map
								});
								
								// 마커 클릭 이벤트 처리
								naver.maps.Event.addListener(marker, "click",
										function(e) {
											if (infowindow.getMap()) {
												infowindow.close();
											} else {
												infowindow.open(map, marker);
												//console.log(result);
											}
										});
								
								// 마크 클릭시 인포윈도우 오픈
								var infowindow = new naver.maps.InfoWindow(
										{
											content : '매롱'
											//content : '<h4> [네이버 개발자센터]</h4><a href="https://developers.naver.com" target="_blank"><img src="https://developers.naver.com/inc/devcenter/images/nd_img.png"></a>'
										});

							});
		}
		/* var getJSON = function(url, callback) {
		var xhr = new XMLHttpRequest();
		xhr.open('GET', url, true);
		xhr.responseType = 'json';
		xhr.onload = function() {
			var status = xhr.status;
			if (status === 200) {
				callback(xhr.response.MgisToilet,
						xhr.response);
			} else {
				callback(status, xhr.response);
			}
		};
		xhr.send();
		};

		getJSON(
			"http://openapi.seoul.go.kr:8088/687946576c73747939397a5a6c574c/json/MgisToilet/1/100/", // 공중화장실
			function(data) {
				//console.log(data)
			}); */
	</script>
</body>
</html>