/*-----------
  high chart
-----------*/
Highcharts.chart('container', {

	title : {
		text : '인천지역별 범죄율, 2010-2017'
	},

	subtitle : {
		text : '경찰서별 5대강력범죄'
	},
	
	xAxis : {
		categories:['2010','2011','2012','2013','2014','2015','2016','2017']
	},

	yAxis : {
		title : {
			text : '범죄횟수'
		}
	},
	legend : {
		layout : 'vertical',
		align : 'right',
		verticalAlign : 'middle'
	},

	plotOptions : {
		series : {
			label : {
				connectorAllowed : false
			},
			pointStart : 2010
		}
	},

	series : crimedata,

	responsive : {
		rules : [ {
			condition : {
				maxWidth : 500
			},
			chartOptions : {
				legend : {
					layout : 'horizontal',
					align : 'center',
					verticalAlign : 'bottom'
				}
			}
		} ]
	}

});

Highcharts.chart('highchart', {

	title : {
		text : 'Solar Employment Growth by Sector, 2010-2016'
	},

	subtitle : {
		text : 'Source: thesolarfoundation.com'
	},

	yAxis : {
		title : {
			text : 'Number of Employees'
		}
	},
	legend : {
		layout : 'vertical',
		align : 'right',
		verticalAlign : 'middle'
	},

	plotOptions : {
		series : {
			label : {
				connectorAllowed : false
			},
			pointStart : 2010
		}
	},

	series : cdata,

	responsive : {
		rules : [ {
			condition : {
				maxWidth : 500
			},
			chartOptions : {
				legend : {
					layout : 'horizontal',
					align : 'center',
					verticalAlign : 'bottom'
				}
			}
		} ]
	}

});