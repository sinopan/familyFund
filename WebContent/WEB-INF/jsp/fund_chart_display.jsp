<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<head><link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script> 
<script src="http://cdn.hcharts.cn/highcharts/highcharts-3d.js"></script> 
<script src="http://cdn.hcharts.cn/highcharts/modules/exporting.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收支图表展示</title>
<style type="text/css">
	.ui-menu { width: 150px; }
	
	#navigate  div{
		float: left;
		background-color: #5fbe40;
	}
	.ui-widget-content {
	    border: 1px solid #ddd;
	    background: #8aadc2bf url(images/ui-bg_highlight-soft_100_eeeeee_1x100.png) 50% top repeat-x;
	    color: #333;
	}
</style>
<script type="text/javascript">	
Date.prototype.toLocaleString = function() {
    <!-- return this.getFullYear() + "/" + (this.getMonth() + 1) + "/" + this.getDate() + "/ " + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds(); -->
    return this.getFullYear() + "." + (this.getMonth() + 1) + "." + this.getDay();
};
	$(function(){
		$( "#menu" ).menu();
		
		/* 设置高度 */
		var innerHeight = $(window).height();
		var innerWidth = $(window).width();
		var topHeight = $("#top").height();

		var innerWidth = $(document).width();
		$("#below").css('height',(innerHeight-topHeight)+'px');
		$("#left").css('height',(innerHeight-topHeight)+'px');
		$("#left").css('width',(innerWidth*0.172)+'px');
		$("#mainContent").css('height',(innerHeight-topHeight)+'px');

		var contentWidth = $("#below").width() - $("#left").width();
		
		$("#mainContent").css('width',(contentWidth-1)+'px');

		$("#menu>li ").css('margin-bottom','25px')
	})
	
	/*  */
	
$(document).ready(function(){
	/* 发送ajax，显示本周支出 */
	var param = {quarter:4};//type--查询条件类型（月份/季度），condition--月份或季度值
	loadCharts('${pageContext.request.contextPath}/fundplan/fund_chart_show', param,"container");
	
	/* 月份下拉框改变  */
	$("#quarter").change(function(){
		var month = $("#quarter").val();
		
		/* 发送ajax，显示本周支出 */
		var param = {quarter:month};//type--查询条件类型（月份/季度），condition--月份或季度值
		loadCharts('${pageContext.request.contextPath}/fundplan/fund_chart_show', param,"container");
	})

})

	function loadCharts(url, param, divId) {
		$.ajaxSettings.async = false;
		$.post(url, param, function(result) {
			//console.log(result);
			//console.log(JSON.parse(result).length);
			var arr = JSON.parse(result);
			
			var series = [];
			var serie1 = {};
			var serie2 = {};
			var data1 = [];
			var data2 = [];
			var categories = [];
			/*  series: [{
		            name: '存入',
		            data: [5, 3, 4, 7, 2]
		        }, {
		            name: '支出',
		            data: [2, 18, 33, 2, 1]
		        }] */
			if (arr != '' && arr.length != 0) {
				for (var i = 0; i < arr.length; i++) {
					var planType = arr[i].planType;
					if (planType==1) {
						planType = "居家基金";
					} else if(planType==2){
						planType = "旅游基金";
					} else if(planType==3){
						planType = "教育基金";
					} else if(planType==4){
						planType = "通讯基金";
					} else if(planType==5){
						planType = "出行基金";
					} else if(planType==6){
						planType = "人情基金";
					} else if(planType==7){
						planType = "饮食基金";
					}
					
					data1[i] = arr[i].amountGoal;
					data2[i] = arr[i].realizeGoal;
					categories[i] = new Date(arr[i].beginTime).toLocaleString()+"至"+new Date(arr[i].endTime).toLocaleString()
											+"<br>"+planType + "--" + arr[i].note;
				}
				serie1 = {
							name: '目标金额',
							data: data1
					};
				serie2 = {
							name: '已存金额',
							data: data2
					};
				series =[serie1,serie2]
				
			} else {
				series = [];
			}
			console.log(series);
			var options = {
				 chart: {
			            type: 'column'
			        },
			        title: {
			            text: ''
			        },
			        xAxis: {
			            categories: categories
			        },
			        credits: {
			            enabled: true
			        },
			        plotOptions: {
			            column: {
			                // 关于柱状图数据标签的详细配置参考：https://api.hcharts.cn/highcharts#plotOptions.column.dataLabels
			                dataLabels: {
			                    enabled: true,
			                    // verticalAlign: 'top', // 竖直对齐方式，默认是 center
			                    inside: true
			                }
			            }
			        },
			        series: series
			}
			if (series.length==0) {
				$("#container").css("background-color","white");
				$("#container").html("暂无数据");
			} else {
				$("#" + divId + "").highcharts(options);
			}

		})
	}
</script>
</head>
<body>
<div id="top">
	<jsp:include page="top.jsp"></jsp:include>
</div>
<div id="below">
	<div id="left" style="float: left;">
		<jsp:include page="left.jsp"></jsp:include>
	</div>
	<div id="content" style="float: left">
		<div style="background-color: #62d55b; float:left;">
				<div id="mainContent" style="float: left;">
					<span style="float: left;">基金计划3D柱状图展示了指定季度的所有基金计划的现状</span>
					<center>
						<hr/>
						<div>
							<b>季度3D柱状图展示</b>
							<select id="quarter" name="quarter">
								<option value="1">第1季度</option>
								<option value="2">第2季度</option>
								<option value="3">第3季度</option>
								<option value="4" selected="selected">第4季度</option>
							</select>
						</div>
					</center>
					
					
					<hr/>
					<center>
						<h4><h3><span id=""><b>基金计划3D柱状图</b></span></h3></h4>
					</center>
					<div>
						<div id="container" style="height: 400px;width: 100%;float: left;"></div>
					</div>
				</div>
		</div>
	</div>
</div>

	
</body>
</html>