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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收支记录</title>
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
    return this.getFullYear() + "/" + (this.getMonth() + 1) + "/" + this.getDate() + "/ " + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();
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
		//$("#payment_withdraw_form").css('height',(innerHeight-topHeight)+'px');
		$("#mainContent").css('height',(innerHeight-topHeight)+'px');

		var contentWidth = $("#below").width() - $("#left").width();
		
		$("#payment_withdraw_form").css('width',(contentWidth-1)+'px');
		
		$("#menu>li ").css('margin-bottom','25px')
	})
	
	$(document).ready(function(){
		/* 发送jajax请求获取基金类型（枚举） */
		$.get("${pageContext.request.contextPath}/fundplanType/records",function(result){
			//console.log(result);
			
			var optionStr = "<option value=\"\">--请选择--</option>";
			var consumptionTypesJson = JSON.parse(result);
			for (var key in consumptionTypesJson) {
			    //console.log(key +"--"+ consumptionTypesJson[key]);     //获取key值、value值
			    optionStr += "<option value="+key+">"+consumptionTypesJson[key]+"</option>"
			}
			$(optionStr).appendTo($("#fundPlanType"));
		})
		
	})
	
	/* 定义ajax请求查询返回的结果，用于查询后静态分页（从内存中取出显示即可） */	
	var shouzhiJsonObj = null;
	var paymentWithdrawlist = null;
	var pageSize = null;
	var totalCount = null;
	var totalPage = null;
	/* 点击搜索按钮  */
	function search(){
		var param = {
			currentPage:1,	
			exchangtype:$("select[name='exchangtype']").val(),	
			fundPlanType:$("#fundPlanType").val(),
			username:$("#username").val()
		}
		/* Ajax查询 */
		$.post("${pageContext.request.contextPath}/paymentWithdraw/list_search", param, function(result){
			console.log(result);
			shouzhiJsonObj = JSON.parse(result);
			paymentWithdrawlist = shouzhiJsonObj.list;
			
			pageSize =  shouzhiJsonObj.pageSize;
			var currPage =  shouzhiJsonObj.currPage;
			totalCount =  shouzhiJsonObj.totalCount;
			totalPage =  shouzhiJsonObj.totalPage;
			
			var tableContentStr = getSearchResultShowStr(1);//显示第一页
			var linkStr = getPageLinkStr(currPage);
			
			$("#payment_withdraw_form").html("");
			$(tableContentStr).appendTo($("#payment_withdraw_form"));	
			
			$(linkStr).appendTo($("#payment_withdraw_form"));
		})
	}
	/* 拼接搜索结果（静态查询：一次从数据库查询后保存在内存中） */
	function getSearchResultShowStr(currPage){
		var	tableContentStr = 	"<table width=\"90%\" border=1>"
								+	"<tr>"
								+		"<td>交易人</td>"
								+		"<td>交易类型</td>"
								+		"<td>交易时间</td>"
								+		"<td>交易金额:</td>"
								+		"<td>交易地点</td>"
								+		"<td>所属基金计划</td>"
								+		"<td>备注</td>"
								+	"</tr>";
		/* 判断需要显示的数据（从内存中取出显示即可） */
		var start = 0;
		var end = 4;
		if (currPage==1) {
			if (currPage==totalPage) {
				end = totalCount;
			} else if(currPage!=totalPage){
				
			}
		}
		if (currPage!=1) {
			if (currPage!=totalPage) {
				start = (currPage-1) * 5;
				end = (currPage * 5)-1;
			} 
		}
		if (currPage==totalPage) {
			start = (currPage-1) * 5;
			end = totalCount-1;
		}
		if (paymentWithdrawlist.length>0) {
			for (var i = 0; i < paymentWithdrawlist.length; i++) {
				if(i>=start && i<=end){
					var exchangeType = "支出";
					if (paymentWithdrawlist[i].exchangeType==1) {
						exchangeType = "存入";
					}
					var exchangeTime = paymentWithdrawlist[i].exchangeTime;
					var unixTimestamp = new Date(exchangeTime) ;
					exchangeTime = unixTimestamp.toLocaleString();
					//alert(commonTime);
					tableContentStr += "<tr style=\"\">"
								+		"<td>"+ paymentWithdrawlist[i].userId +"</td>"
								+		"<td>"+exchangeType+"</td>"
								+		"<td>"+ exchangeTime +"</td>"
								+		"<td>"+ paymentWithdrawlist[i].exchangeAmount +"</td>"
								+		"<td>"+ paymentWithdrawlist[i].exchangePlace +"</td>"
								+		"<td>"+ paymentWithdrawlist[i].fundPlanId +"</td>"
								+		"<td>"+ paymentWithdrawlist[i].exchangeDeatil +"</td>"
								+	"</tr>";
				}
			}
		}
		tableContentStr += "</table>";
		return tableContentStr;
	}	
	
	/* 拼接搜索结果的分页字符串 */
	function getPageLinkStr(currPage){
		var linkStr = "";
		//判断当前页为首页
		if(currPage!=1){
			linkStr += 	 "<span onclick=\"pageLink("+ 1 +")\">[首页]</span>"
					   + "<span onclick=\"pageLink("+ (currPage-1) +")\">[上一页]</span>";
		}
		var i = 1; 
		var j = 1; 
		if((currPage-5)>0){
			i = currPage-5;
		}else{
			i = 1;
		}
		if((currPage+4)>totalPage){
			j = totalPage;
		}else{
			j = currPage+4;
		}
		for (i;  i<= j; i++) {
			if (i==currPage) {
				linkStr += "<span>["+i+"]</span>";
			} else {
				linkStr += "<span onclick=\"pageLink("+ i +")\">["+i+"]</span>";
			}
		}
		if(currPage!=totalPage){
			linkStr += "<span onclick=\"pageLink("+ (currPage+1) +")\">[下一页]</span>"
					  +"<span onclick=\"pageLink("+ totalPage +")\">[尾页]</span>";
		}
		return linkStr;
	}
	/* 搜索结果点击页签调用 */
	function pageLink(currPage){
		//alert(currPage)
		var tableContentStr = getSearchResultShowStr(currPage);//显示第一页
		var linkStr = getPageLinkStr(currPage);
		$("#payment_withdraw_form").html("");
		$(tableContentStr).appendTo($("#payment_withdraw_form"));	
		$(linkStr).appendTo($("#payment_withdraw_form"));
	}

	$(function(){
		$("#exportButton").click(function(){
			exchangtype = $("select[name='exchangtype']").val();	
			fundPlanType = $("#fundPlanType").val();
			username = $("#username").val();
			
			if (""==exchangtype) {
				exchangtype = "all";
			}
			if (""==fundPlanType) {
				fundPlanType = "all";
			}
			if (""==username) {
				username = "all";
			}
			window.location.href =("${pageContext.request.contextPath}/paymentWithdraw/export/"
					+exchangtype+"/"+fundPlanType+"/"+username+"");
		})
	})
</script>
</head>
<body style="background-image:${pageContext.request.contextPath}/picture/1.jpg">
<div id="top">
	<jsp:include page="top.jsp"></jsp:include>
</div>
<div id="below">
	<div id="left" style="float: left;">
		<jsp:include page="left.jsp"></jsp:include>
	</div>
	<div id="content" style="float: left">
		<div id="mainContent" style="background-color: rgb(206, 223, 209); float:left;position: absolute;">
			<div style="padding-left: 50px;padding-top: 15px;">
				<button id="exportButton">导出为Excel</button>
			</div>
		    <center>
				<br>
				交易类型：
					<select name="exchangtype">
						<option value="">--请选择--</option>
						<option value="1">存入</option>
						<option value="0">支出</option>
					</select>
				所属基金：<select id="fundPlanType" name="fundPlanType">
						<!-- <option value="">居家消费</option>
						<option value="">子女教育</option> -->
					   </select>
				交易人：<input type="text" id="username" name="username"></input>
				<button onclick="search();">查询</button><br>
				<form id="payment_withdraw_form" action="" method="post">
					<br><br>
						<h3>收支记录表</h3>
						<table width="90%" border=1>
						<tr>
							<td>交易人</td>
							<td>交易类型</td>
							<td>交易金额:</td>
							<td>交易时间</td>
							<td>交易地点</td>
							<td>所属基金计划</td>
							<td>备注</td>
						</tr>
						<c:forEach items="${pagebean.list}" var="paymentWithdraw">
								<c:if test="${paymentWithdraw.exchangeType==1}">
									<tr style="background-color: aqua">
								</c:if>
								<c:if test="${paymentWithdraw.exchangeType==0}">
									<tr style="background-color: red">
								</c:if>
								<td>${paymentWithdraw.userId}</td>
								<c:if test="${paymentWithdraw.exchangeType==1}">
									<td>存入</td>
								</c:if>
								<c:if test="${paymentWithdraw.exchangeType==0}">
									<td>支出</td>
								</c:if>
								<td>${paymentWithdraw.exchangeAmount}</td>
								<td>${paymentWithdraw.exchangeTime}</td>
								<td>${paymentWithdraw.exchangePlace}</td>
								<td>${paymentWithdraw.fundPlanId}</td>
								<td>${paymentWithdraw.exchangeDeatil }</td>
							</tr>
						</c:forEach>
						</table>
						<!-- 分页（该分页为加载页面后的分页，如果是ajax查询则会将该标签内的内容清除，直接从之间在ajax请求的数据中取出显示即可） -->
						<div id="fenye">
							<c:if test="${pagebean.currPage!=1 }">
								<a href="${pageContext.request.contextPath}/paymentWithdraw/list/1">[首页]</a>
								<a href="${pageContext.request.contextPath}/paymentWithdraw/list/${pagebean.currPage-1 }">[上一页]</a>
							</c:if>
							
							<c:forEach begin="${(pagebean.currPage-5)>0 ? (pagebean.currPage-5):1}" var="n"
										end="${(pagebean.currPage+4)>pagebean.totalPage ? pagebean.totalPage:(pagebean.currPage+4)}">
								<c:if test="${pagebean.currPage==n}">
									${n}
								</c:if>
								<c:if test="${pagebean.currPage!=n}">
									<a href="${pageContext.request.contextPath}/paymentWithdraw/list/${n}">${n }</a>
								</c:if>
							</c:forEach>
							
							<c:if test="${pagebean.currPage!=pagebean.totalPage }">
								<a href="${pageContext.request.contextPath}/paymentWithdraw/list/${pagebean.currPage+1}">[下一页]</a>
								<a href="${pageContext.request.contextPath}/paymentWithdraw/list/${pagebean.totalPage}">[尾页]</a>
							</c:if>
						</div>
					</form>
		    </center>
		</div>
	</div>
</div>

	
</body>
</html>