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
gridView.setPanel({visible:false}); // 상단바(그루핑 영역)
gridView.setFooter({visible: false}); // 하단바
gridView.setStateBar({visible: false}); // 숫자 옆 공백
gridView.setOptions({
    indicator: {zeroBase: false}, // 상태바
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
}];

//컬럼을 GridView에 입력 합니다.
gridView.setColumns(columns);

// json 형식 데이터 넣기
//var data = ${jdata};
function gridinput(jd){
	jdata = jd;
	dataProvider.setRows(jdata.kinderInfo);
}