/**
 * 图形报表组件
 * 参数：.autoCanvas 自动生成图形报表
 * 		fix_width 如果没有这个参数则自动适应屏幕宽度，否则按照设定的宽度来
 * 		data 报表数据
 * 		type 报表展示的类型（line 普通线图、pie 普通饼图、ic 互动报表、tc 跟踪曲线、scc 堆栈图表、pc 饼图）
 */

$(window).resize(resizeCanvas);
function resizeCanvas() {
	$(".autoCanvas").each(function() {
		if(typeof $(this).attr("fix_width")!='undefined'&&$(this).attr("fix_width")!=''){
			$(this).css("width", $(this).attr("fix_width")); 
		}else if(typeof $(this).attr("vary_width")!='undefined'){
			$(this).css("width", $(this).parent("div:first").get(0).clientWidth-60);
		}else{
			$(this).css("width", $(window).get(0).innerWidth-120); 
		}
		if($(this).css("height")=='undefined'||$(this).css("height")==''||$(this).css("height")=="0px"){
			$(this).css("height",300);
		}
		if("ic"==$(this).attr("type")){
			drawInteractiveChart($(this),$(this).attr("data"));
		}else if("tc"==$(this).attr("type")){
			drawTrackingCurves($(this),$(this).attr("data"));
		}else if("scc"==$(this).attr("type")){
			drawStackChartControls($(this),$(this).attr("data"));
		}else if("line"==$(this).attr("type")){
			drawLine($(this),$(this).attr("data"));
		}else if("pie"==$(this).attr("type")){
			drawPie($(this),$(this).attr("data"));
		}
	});
}
resizeCanvas();

//画普通线图
function drawLine(canvas,data){
	var jsonData=eval('('+data+')');
	var chartData = [];
	var options={
		series: {
	        lines: {
	            show: true,
	            lineWidth: 2,
	            fill: true,
	            fillColor: {
	                colors: [{opacity: 0.09},{opacity: 0.09}]
	            }
	        },
	        points: {
	            show: true
	        },
	        shadowSize: 2
	    },
	    grid: {
	        hoverable: true,
	        clickable: true,
	        tickColor: "#eee",
	        borderWidth: 0
	    },
	    colors: ["#d12610", "#37b7f3", "#52e136"],
	    xaxis: {
	        ticks: [],
	        tickDecimals: 0,
	        labelWidth:50
	    },
	    yaxis: {
	        ticks: 11,
	        tickDecimals: 0
	    }
	};
	$.each(jsonData.datas,function(i,item){
		var data=[];
		$.each(item,function(j,it){
			data.push([j,it]);
		});
		chartData.push({
			data:data,
			label:jsonData.labels[i]
		});
	});
	$.each(jsonData.ticks,function(i,item){
		options.xaxis.ticks.push([i,item]);
	});
	var plot = $.plot(canvas, chartData,options);


	function showTooltip(x, y, contents) {
	    $('<div id="tooltip">' + contents + '</div>').css({
	        position: 'absolute',
	        display: 'none',
	        'z-index':'1051',
	        top: y + 5,
	        left: x + 15,
	        border: '1px solid #333',
	        padding: '4px',
	        color: '#fff',
	        'border-radius': '3px',
	        'background-color': '#333',
	        opacity: 0.80
	    }).appendTo("body").fadeIn(200);
	}
	
	var previousPoint = null;
	canvas.bind("plothover", function (event, pos, item) {
	    $("#x").text(pos.x.toFixed(2));
	    $("#y").text(pos.y.toFixed(2));
	    if (item) {
	        if (previousPoint != item.dataIndex) {
	            previousPoint = item.dataIndex;
	
	            $("#tooltip").remove();
	            var x = item.datapoint[0],
	                y = item.datapoint[1];
	            showTooltip(item.pageX, item.pageY, jsonData.ticks[x] +" "+ item.series.label + " = " + y);
	        }
	    } else {
	        $("#tooltip").remove();
	        previousPoint = null;
	    }
	});
}

//画普通饼图
function drawPie(canvas,data){
	var jsonData=eval('('+data+')');
	var ticks = jsonData.ticks[0];
	if(ticks == undefined){
		ticks = "value";
	}
	var chartData = [];
	var options={
		series: {
			pie: {
		        show: true,
		        radius: 1,
		        label: {
		            show: true,
		            radius: 3 / 4,
		            formatter: function (label, series) {
		                return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">' + label + '<br/>' + Math.round(series.percent) + '%</div>';
		            },
		            background: {
		                opacity: 0.5
		            }
		        }
		    }
	    },
	    grid: {
	        hoverable: true,
	        clickable: true
	    }
	};
	var total =0;
	$.each(jsonData.datas,function(i,item){
		total = total + item;
		chartData.push({
			data:item,
			label:jsonData.labels[i]
		});
	});
	var plot = $.plot(canvas, chartData,options);
	
	canvas.bind("plothover", pieHover);

    function pieHover(event, pos, obj) {
        if (!obj) return;
        percent = parseFloat(obj.series.percent).toFixed(2);
        canvas.find("#pieShow").remove();
        canvas.find(".legend").append('<span id="pieShow" style="position:absolute;top:5px;left:5px;font-weight: bold; color:white;padding: 2px;border:1px solid black;background-color: ' + obj.series.color + '">' + obj.series.label +' '+ticks+'='+obj.datapoint[1][0][1] +'/'+ total +' ('+ percent + '%)</span>');
    }
}

//画互动报表
function drawInteractiveChart(canvas,data){
	var jsonData=eval('('+data+')');
	var chartData = [];
	var options={
		series: {
	        lines: {
	            show: true,
	            lineWidth: 2,
	            fill: true,
	            fillColor: {
	                colors: [{opacity: 0.09},{opacity: 0.09}]
	            }
	        },
	        points: {
	            show: true
	        },
	        shadowSize: 2
	    },
	    grid: {
	        hoverable: true,
	        clickable: true,
	        tickColor: "#eee",
	        borderWidth: 0
	    },
	    colors: ["#d12610", "#37b7f3", "#52e136"],
	    xaxis: {
	        ticks: 11,
	        tickDecimals: 0
	    },
	    yaxis: {
	        ticks: 11,
	        tickDecimals: 0
	    }
	};
	$.each(jsonData.datas,function(i,item){
		chartData.push({
			data:item,
			label:jsonData.labels[i]
		});
	});
	var plot = $.plot(canvas, chartData,options);


	function showTooltip(x, y, contents) {
	    $('<div id="tooltip">' + contents + '</div>').css({
	        position: 'absolute',
	        display: 'none',
	        top: y + 5,
	        left: x + 15,
	        border: '1px solid #333',
	        padding: '4px',
	        color: '#fff',
	        'border-radius': '3px',
	        'background-color': '#333',
	        opacity: 0.80
	    }).appendTo("body").fadeIn(200);
	}
	
	var previousPoint = null;
	canvas.bind("plothover", function (event, pos, item) {
	    $("#x").text(pos.x.toFixed(2));
	    $("#y").text(pos.y.toFixed(2));
	
	    if (item) {
	        if (previousPoint != item.dataIndex) {
	            previousPoint = item.dataIndex;
	
	            $("#tooltip").remove();
	            var x = item.datapoint[0].toFixed(2),
	                y = item.datapoint[1].toFixed(2);
	
	            showTooltip(item.pageX, item.pageY, item.series.label + " (" + x + " , " + y+")");
	        }
	    } else {
	        $("#tooltip").remove();
	        previousPoint = null;
	    }
	});
}

//画曲线跟踪图
function drawTrackingCurves(canvas,data){
	var jsonData=eval('('+data+')');
	var chartData = [];
	var options={
		    series: {
		        lines: {
		            show: true
		        }
		    },
		    crosshair: {
		        mode: "x"
		    },
		    grid: {
		        hoverable: true,
		        autoHighlight: false
		    }
		};
	$.each(jsonData.datas,function(i,item){
		chartData.push({
			data:item,
			label:jsonData.labels[i]
		});
	});
	var plot = $.plot(canvas, chartData,options);

	var legends = canvas.find(".legendLabel");
	legends.each(function () {
	    $(this).css('width', $(this).width()+5);
	});
	
	var updateLegendTimeout = null;
	var latestPosition = null;
	
	function updateLegend() {
	    updateLegendTimeout = null;
	
	    var pos = latestPosition;
	
	    var axes = plot.getAxes();
	    if (pos.x < axes.xaxis.min || pos.x > axes.xaxis.max || pos.y < axes.yaxis.min || pos.y > axes.yaxis.max) return;
	
	    var i, j, dataset = plot.getData();
	    for (i = 0; i < dataset.length; ++i) {
	        var series = dataset[i];
	
	        // find the nearest points, x-wise
	        for (j = 0; j < series.data.length; ++j)
	            if (series.data[j][0] > pos.x) break;
	
	            // now interpolate
	        var y, p1 = series.data[j - 1],
	            p2 = series.data[j];
	        if (p1 == null) y = p2[1];
	        else if (p2 == null) y = p1[1];
	        else y = p1[1] + (p2[1] - p1[1]) * (pos.x - p1[0]) / (p2[0] - p1[0]);
	
	        legends.eq(i).text(series.label.replace(/=.*/, "= " + y.toFixed(2)));
	    }
	}
	
	canvas.bind("plothover", function (event, pos, item) {
	    latestPosition = pos;
	    if (!updateLegendTimeout) updateLegendTimeout = setTimeout(updateLegend, 50);
	});
}

//画堆栈图表
function drawStackChartControls(canvas,data){
	var jsonData=eval('('+data+')');
	var chartData = [];
	var options={
		 series: {
	        bars: {
	            show: true,
	            align: "center",
	            barWidth: 0.6
	        },
	        xaxis: {
				mode: "categories",
				tickLength: 0
			}
	    }
	};
	$.each(jsonData.datas,function(i,item){
		chartData.push({
			data:item,
			label:jsonData.labels[i]
		});
	});
	var plot = $.plot(canvas, chartData,options);
}