<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>RealGrid Sample Page</title>

</head>

<body>
	<div class="backdiv" id="header">
		<%@include file="../include/header.jsp"%>
	</div>
	<div id="realgrid" style="width: 100%; height: 600px;"></div>
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
		gridView.setPanel({visible:false}); // 상단 그루핑 패널
		gridView.setFooter({visible: false}); // 하단바
		gridView.setStateBar({visible: false}); // 숫자 옆 공백
		gridView.setOptions({
		    indicator: {zeroBase: false}, // 상태바
		    checkBar: {visible: false}, // 체크바
		});

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
	    //dataProvider.setRows(${jdata}.kinderInfo);
	    
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
		}); */
		
		// CORS 문제때문에 사용할 수 없음
		/* $.ajax({
			url : "http://e-childschoolinfo.moe.go.kr/api/notice/basicInfo.do?key=cba3828f0113465fa66bc6123d70903f&sidoCode=28&sggCode=28177",
			cache : false
		}).done(function(data) {
			data = JSON.parse("{"+data.substring(data.indexOf(',')+1,data.length-1)+"}");
			dataProvider.fillJsonData(data.kinderInfo, {
				fillMode : "insert"
			});
		}); */
		
		// jquery foreach문
		/* $(jdata.kinderInfo).each(function(index, jinfo){
			console.log(jinfo.kindername);
		}); */
		
		gridView.onDataCellClicked = function (grid, index) {
			console.log(grid.getValues(index.dataRow).kindername);
	    };
	    
	    gridView.setPaging(true, 10);

	    page = gridView.getPage();
	    totalPage = gridView.getPageCount();

	    $(".current-page-view").text(page);
	    $(".total-page-view").text(${jdata}.kinderInfo.length);
	    
	    gridView.onPageChanged = function(grid, page) {
    	  $(".current-page-view").text(page);
    	}

    	gridView.onPageCountChanged = function(grid, pageCount) {
    	  $(".total-page-view").text(${jdata}.kinderInfo.length);
    	}
	});

	//시도셀렉트박스 선택 시
	function change_sel() {
		
		var sdName = $("#sidoName option:checked").text();
		
		$.ajax({
			type: "POST",
			url: "/changeSel",
			dataType:"json",
			data: {param:sdName},
			success: function(result){
				$("#sigunguName").find("option").remove().end().append("<option value='all'>-선택-</option>");
				$.each(result, function(i) {
					$("#sigunguName").append("<option value='"+result[i].sigunguCode+"'>"+result[i].sigunguName+"</option>");
				});
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert("오류가 발생하였습니다.");
			}                     
		});
	}

	//조회버튼 함수
	function search() {
		var sidoCode = $("#sidoName").val();
		var sigunguCode = $("#sigunguName").val();
		
		$.ajax({
			type: "POST",
			url: "/search",
			dataType:"json",
			data: {sidoCode:sidoCode,sigunguCode:sigunguCode},
			success: function(jdata){
			    dataProvider.setRows(jdata.kinderInfo);
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert("오류가 발생하였습니다.");
			}                     
		});
	}
</script>

</html>
