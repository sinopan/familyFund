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
<title>基金列表</title>
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
		$("#payment_record_form").css('height',(innerHeight-topHeight)+'px');

		var contentWidth = $("#below").width() - $("#left").width();
		
		$("#payment_record_form").css('width',(contentWidth-1)+'px');

		$("#menu>li ").css('margin-bottom','25px')
	})

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
		<div style="background-color: #FFEB3B; float:left;">
		    <center>
				<form id="payment_record_form" action="" method="post">
					基金计划列表
					<table width="90%" border=1 id="list_table">
					<tr>
						<td>目标金额</td>
						<td>已存金额</td>
						<td>开始时间</td>
						<td>截止时间</td>
						<td>计划类型</td>
						<td>备注</td>
						<td>状态</td>
					</tr>
					
					<c:forEach items="${pagebean.list}" var="fundplan">
						<tr>
							<td>${fundplan.amountGoal }</td>
							<td>${fundplan.realizeGoal }</td>
							<td>${fundplan.beginTime}</td>
							<td>${fundplan.endTime}</td>
							<td>${fundplan.planType}</td>
							<td>${fundplan.note}</td>
							<c:if test="${fundplan.planState==0 }">
								<td>未完成</td>
							</c:if>
							<c:if test="${fundplan.planState==1 }">
								<td>完成</td>
							</c:if>
						</tr>
					</c:forEach>
					</table>
					<!-- 分页（该分页为加载页面后的分页，如果是ajax查询则会将该标签内的内容清除，直接从之间在ajax请求的数据中取出显示即可） -->
					<div id="fenye">
						<c:if test="${pagebean.currPage!=1 }">
							<a href="${pageContext.request.contextPath}/fundplan/list/1">[首页]</a>
							<a href="${pageContext.request.contextPath}/fundplan/list/${pagebean.currPage-1 }">[上一页]</a>
						</c:if>
						
						<c:forEach begin="${(pagebean.currPage-5)>0 ? (pagebean.currPage-5):1}" var="n"
									end="${(pagebean.currPage+4)>pagebean.totalPage ? pagebean.totalPage:(pagebean.currPage+4)}">
							<c:if test="${pagebean.currPage==n}">
								${n}
							</c:if>
							<c:if test="${pagebean.currPage!=n}">
								<a href="${pageContext.request.contextPath}/fundplan/list/${n}">${n }</a>
							</c:if>
						</c:forEach>
						
						<c:if test="${pagebean.currPage!=pagebean.totalPage }">
							<a href="${pageContext.request.contextPath}/fundplan/list/${pagebean.currPage+1}">[下一页]</a>
							<a href="${pageContext.request.contextPath}/fundplan/list/${pagebean.totalPage}">[尾页]</a>
						</c:if>
					</div>
				</form>
		    </center>
		</div>
	</div>
</div>

	
</body>
</html>