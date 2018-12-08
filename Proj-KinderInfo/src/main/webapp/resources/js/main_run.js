//Grid item Click Event(Chart 연동) start
gridView.onDataCellClicked = function(grid, index) {
	map.setCenter(markers[index.dataRow].position);
	map.setZoom(8, true);
	infowindow.setContent(contStart + jdata.kinderInfo[index.dataRow].kindername + jdata.kinderInfo[index.dataRow].establish + contEnd);
	$("#detailP *").remove(); // 기존 append 값 지우기(뒤에 *를 추가해 자식요소만 없앤다.)
	
	// 피커 선택시 상세정보창 값 변경
    $("#detailP").append("<b> 유치원 명 : " + jdata.kinderInfo[index.dataRow].kindername
	    		+ " " + jdata.kinderInfo[index.dataRow].establish
	    		+ " | 오픈시간 : " + jdata.kinderInfo[index.dataRow].opertime
	    		+ "<br>주소 : " + jdata.kinderInfo[index.dataRow].addr
	    		+ " | 연락처 : " + jdata.kinderInfo[index.dataRow].telno) + "</b>";
	infowindow.open(map, markers[index.dataRow]);
};
//Grid item Click Event(Chart 연동) end

//header fix start
var topBar = $("#topBar").offset();

$(window).scroll(function(){
	
	var docScrollY = $(document).scrollTop()
	var barThis = $("#topBar")

	if( docScrollY > topBar.top ) {
		barThis.addClass("topBar_fix");
	}else{
		barThis.removeClass("topBar_fix");
	}
});
//header fix end

//상세보기 창 컨트롤 start
detv.style.display="none";
function detailFunc(){
	if(detv.style.display=="block"){
		detv.style.display="none";
	}else{
		detv.style.display="block";
	}
}
//상세보기 창 컨트롤 end

//시도셀렉트박스 선택 시
function change_sel() {
	
	var sdName = $("#sidoName option:checked").text();
	console.log(sdName);
	
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