gridView.onDataCellClicked = function(grid, index) {
	map.setCenter(markers[index.dataRow].position);
	map.setZoom(8, true);
	infowindow.setContent(jdata.kinderInfo[index.dataRow].kindername);
	infowindow.open(map, markers[index.dataRow]);
};

var topBar = $("#topBar").offset();

// haeder fix
$(window).scroll(function(){
	
	var docScrollY = $(document).scrollTop()
	var barThis = $("#topBar")
	var fixNext = $("#frontdiv")

	if( docScrollY > topBar.top ) {
		barThis.addClass("top_bar_fix");
		fixNext.addClass("pd_top_80");
	}else{
		barThis.removeClass("top_bar_fix");
		fixNext.removeClass("pd_top_80");
	}
});