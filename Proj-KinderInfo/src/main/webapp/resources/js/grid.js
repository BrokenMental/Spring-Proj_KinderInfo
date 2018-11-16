/*-----------
  real grid
-----------*/
var gridView;
var dataProvider;

RealGridJS.setTrace(false);
RealGridJS.setRootContext("resources");

dataProvider = new RealGridJS.LocalDataProvider();
gridView = new RealGridJS.GridView("realgrid");
gridView.setDataSource(dataProvider);
gridView.setStateBar({visible: false}); // 숫자 옆 공백
gridView.setOptions({
    indicator: {zeroBase: false}, // ? 뭐지
    checkBar: {visible: false} // 체크바
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
dataProvider.setRows(jdata.kinderInfo);