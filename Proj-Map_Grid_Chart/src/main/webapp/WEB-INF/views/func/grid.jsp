<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>RealGrid Sample Page</title>

</head>

<body>
	<div id="realgrid" style="width: 100%; height: 300px;"></div>
</body>
<script type="text/javascript" src="resources/realgridjs-lic.js"></script>
<script type="text/javascript"
	src="resources/realgridjs_eval.1.1.29.min.js"></script>
<script type="text/javascript" src="resources/realgridjs-api.1.1.29.js"></script>
<script type="text/javascript" src="resources/jszip.min.js"></script>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
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
			fieldName : "field1"
		}, {
			fieldName : "field2"
		}, {
			fieldName : "field3"
		}, {
			fieldName : "field4"
		}, {
			fieldName : "field5"
		} ];
		
		//DataProvider의 setFields함수로 필드를 입력합니다.
		dataProvider.setFields(fields);

		//필드와 연결된 컬럼 배열 객체를 생성합니다.
		var columns = [ {
			name : "col1",
			fieldName : "field1",
			header : {
				text : "name"
			},
			width : 60
		}, {
			name : "col2",
			fieldName : "field2",
			header : {
				text : "artist"
			},
			width : 50
		}, {
			name : "col3",
			fieldName : "field3",
			header : {
				text : "type"
			},
			width : 80
		}, {
			name : "col4",
			fieldName : "field4",
			header : {
				text : "release"
			},
			width : 80
		}, {
			name : "col5",
			fieldName : "field5",
			header : {
				text : "genre"
			},
			width : 80
		} ];

		//컬럼을 GridView에 입력 합니다.
		gridView.setColumns(columns);

		// json 형식 데이터 넣기
	    var data = ${gdata};
	    dataProvider.setRows(data);
	    
	    // 아래와 같은 형식으로 넣어도 무방하다.
	    /* var data = [
	        ["data1", "data2", "data3"]
	    ]; */
	    
		// json 외부 삽입
		/* $("#btnFillJSONData").click(function() {
			$.ajax({
				url : "/data/samples/sampleJSONData.json",
				cache : false
			}).done(function(data) {
				dataProvider.fillJsonData(data, {
					fillMode : "append"
				});
			});
		}) */

	});
</script>

</html>
