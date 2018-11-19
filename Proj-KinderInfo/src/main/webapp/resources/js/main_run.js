gridView.onDataCellClicked = function(grid, index) {
	map.setCenter(markers[index.dataRow].position);
	map.setZoom(8, true);
	infowindow.setContent(jdata.kinderInfo[index.dataRow].kindername);
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


