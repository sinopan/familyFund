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
	var param = {conditionType:null, conditionValue:null,exchangeType:0};//type--查询条件类型（月份/季度），condition--月份或季度值
	loadCharts('${pageContext.request.contextPath}/paymentWithdraw/exchange_chart_show', param,"container");
	/* 发送ajax，显示本周存入 */
	var param = {conditionType:null, conditionValue:null,exchangeType:1};//type--查询条件类型（月份/季度），condition--月份或季度值
	loadCharts('${pageContext.request.contextPath}/paymentWithdraw/exchange_chart_show', param,"container1");
	
	/* 月份下拉框改变  */
	$("#month").change(function(){
		var month = $("#month").val();
		
		$("#periodoftime").html(month+"月份  ");
		
		/* 发送ajax，显示本周支出 */
		var param = {conditionType:1, conditionValue:month,exchangeType:0};//type--查询条件类型（月份/季度），condition--月份或季度值
		loadCharts('${pageContext.request.contextPath}/paymentWithdraw/exchange_chart_show', param,"container");
		/* 发送ajax，显示本周存入 */
		var param = {conditionType:1, conditionValue:month,exchangeType:1};//type--查询条件类型（月份/季度），condition--月份或季度值
		loadCharts('${pageContext.request.contextPath}/paymentWithdraw/exchange_chart_show', param,"container1");
	})

})

	function loadCharts(url, param, divId) {
		$.ajaxSettings.async = false;
		$.post(url, param, function(result) {
			//console.log(result);
			//console.log(JSON.parse(result).length);
			var arr = JSON.parse(result);
			var data = [];
			if (arr != '' && arr.length != 0) {

				for (var i = 0; i < arr.length; i++) {
					var map = [];

					map = [ arr[i].consumationType, arr[i].amount ];
					data[i] = map;
				}
			} else {
				data = [];
			}
			console.log(data);
			//var data1 = [['ss',86],['ss',1000],['ssss',86]]; 
			var map = {
				key1 : 'abc',
				key2 : 'def'
			};
			//
			var options = {
				chart : {
					type : 'pie',
					options3d : {
						enabled : true,
						alpha : 45
					}
				},
				title : {
					text : ''
				},
				subtitle : {
					text : ''
				},
				plotOptions : {
					pie : {
						innerSize : 100,
						depth : 80
					}
				},
				series : [ {
					name : '人民币￥',
					data : data
				} ]
			}
			$("#" + divId + "").highcharts(options);

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
					<span style="float: left;">月收支饼图展示了指定月份的各类收支占比</span>
					<center>
						<hr/>
						<div>
							<b>月收支展示</b>
							<select id="month" name="month">
								<option value="1">1月</option>
								<option value="2">2月</option>
								<option value="3">3月</option>
								<option value="4">4月</option>
								<option value="5">5月</option>
								<option value="6">6月</option>
								<option value="7">7月</option>
								<option value="8">8月</option>
								<option value="9">9月</option>
								<option value="10">10月</option>
								<option value="11">11月</option>
								<option value="12">12月</option>
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<!-- <b>季度收支展示</b>
							<select name="quarter">
								<option>第1季度</option>
								<option>第2季度</option>
								<option>第3季度</option>
								<option>第4季度</option>
							</select> -->
						</div>
					</center>
					
					
					<hr/>
					<center>
						<h4><h3><span id=""><b><span id="periodoftime">本周</span>支出/存入饼图展示</b></span></h3></h4>
					</center>
					<div>
						<div id="container" style="height: 400px;width: 50%;float: left;"></div>
						<div id="container1" style="height: 400px;width: 50%;float: left;"></div>
					</div>
				</div>
		</div>
	</div>
</div>

	
</body>
</html>