gridView.onDataCellClicked = function(grid, index) {
	map.setCenter(markers[index.dataRow].position);
	map.setZoom(8, true);
	infowindow.setContent(jdata.kinderInfo[index.dataRow].kindername);
	infowindow.open(map, markers[index.dataRow]);
};