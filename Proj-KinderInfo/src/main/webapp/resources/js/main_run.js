gridView.onDataCellClicked = function(grid, index) {
	map.setCenter(markers[index.dataRow].position);
	map.setZoom(8, true);
	infowindow.setContent(contStart + jdata.kinderInfo[index.dataRow].kindername + contEnd);
	$("#detailP *").remove(); // 기존 append 값 지우기(뒤에 *를 추가해 자식요소만 없앤다.)
	$("#detailP").append("<b>"+jdata.kinderInfo[index.dataRow].kindername+"</b>"); // 피커 선택시 상세정보창에 미리 입력
	infowindow.open(map, markers[index.dataRow]);
};

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

detv.style.display="none";
function detailFunc(){
	if(detv.style.display=="block"){
		detv.style.display="none";
	}else{
		detv.style.display="block";
	}
}
