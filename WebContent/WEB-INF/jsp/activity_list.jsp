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
		$("#family_activity_form").css('height',(innerHeight-topHeight)+'px');

		var contentWidth = $("#below").width() - $("#left").width();
		
		$("#family_activity_form").css('width',(contentWidth-1)+'px');

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
		<div style="background-color: #828fbe75; float:left;">
		    <center>
				<form id="family_activity_form" action="" method="post">
							家庭活动计划表
							<table width="90%" border=1>
							<tr>
								<td>活动详情</td>
								<td>策划人</td>
								<td>参与人员</td>
								<td>开始时间</td>
								<td>结束时间</td>
							</tr>
							<c:forEach items="${pagebean.list}" var="familyActivity">
								<tr>
									<td>${familyActivity.content }</td>
									<td>${familyActivity.planer }</td>
									<td>${familyActivity.participants}</td>
									<td>${familyActivity.beginTime}</td>
									<td>${familyActivity.endTime}</td>
								</tr>
							</c:forEach>
							</table>
							<!-- 分页 -->
							<div>
								<c:if test="${pagebean.currPage!=1 }">
									<a href="${pageContext.request.contextPath}/activity/list/1">[首页]</a>
									<a href="${pageContext.request.contextPath}/activity/list/${pagebean.currPage-1 }">[上一页]</a>
								</c:if>
								
								<c:forEach begin="${(pagebean.currPage-5)>0 ? (pagebean.currPage-5):1}" var="n"
											end="${(pagebean.currPage+4)>pagebean.totalPage ? pagebean.totalPage:(pagebean.currPage+4)}">
									<c:if test="${pagebean.currPage==n}">
										${n}
									</c:if>
									<c:if test="${pagebean.currPage!=n}">
										<a href="${pageContext.request.contextPath}/activity/list/${n}">${n }</a>
									</c:if>
								</c:forEach>
								
								<c:if test="${pagebean.currPage!=pagebean.totalPage }">
									<a href="${pageContext.request.contextPath}/activity/list/${pagebean.currPage+1}">[下一页]</a>
									<a href="${pageContext.request.contextPath}/activity/list/${pagebean.totalPage}">[尾页]</a>
								</c:if>
							</div>
						</form>
		    </center>
		</div>
	</div>
</div>

	
</body>
</html>