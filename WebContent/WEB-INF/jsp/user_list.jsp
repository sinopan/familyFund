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
<title>用户列表</title>
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
		$("#userForm").css('height',(innerHeight-topHeight)+'px');

		var contentWidth = $("#below").width() - $("#left").width();
		
		$("#userForm").css('width',(contentWidth-1)+'px');
		
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
		<div style="background-color: #c1ea7f; float:left;">
		    <center>
		    	<form id="userForm" action="" method="post" style="width: 1271px;">
					<center>成员列表：</center>
					<table width="90%" border=1>
					<tr>
						<td>姓名</td>
						<td>性别</td>
						<td>生日</td>
						<td>年龄</td>
						<td>电话</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${pagebean.list}" var="user">
						<tr>
							<td>${user.name }</td>
							<c:if test="${user.gender ==1 }">
								<td>男</td>
							</c:if>
							<c:if test="${user.gender ==0 }">
								<td>女</td>
							</c:if>
							<td>${user.bithday}</td>
							<td>${user.age }</td>
							<td>${user.mobile }</td>
							<td><a href="${pageContext.request.contextPath}/user/modify_page/${user.id}">修改</a></td>
						</tr>
					</c:forEach>
					</table>
					<!-- 分页 -->
					<div>
						<c:if test="${pagebean.currPage!=1 }">
							<a href="${pageContext.request.contextPath}/user/list/1">[首页]</a>
							<a href="${pageContext.request.contextPath}/user/list/${pagebean.currPage-1 }">[上一页]</a>
						</c:if>
						
						<c:forEach begin="${(pagebean.currPage-5)>0 ? (pagebean.currPage-5):1}" var="n"
									end="${(pagebean.currPage+4)>pagebean.totalPage ? pagebean.totalPage:(pagebean.currPage+4)}">
							<c:if test="${pagebean.currPage==n}">
								${n}
							</c:if>
							<c:if test="${pagebean.currPage!=n}">
								<a href="${pageContext.request.contextPath}/user/list/${n}">${n }</a>
							</c:if>
						</c:forEach>
						
						<c:if test="${pagebean.currPage!=pagebean.totalPage }">
							<a href="${pageContext.request.contextPath}/user/list/${pagebean.currPage+1}">[下一页]</a>
							<a href="${pageContext.request.contextPath}/user/list/${pagebean.totalPage}">[尾页]</a>
						</c:if>
					</div>
				</form>	
		    </center>
		</div>
	</div>
</div>
	
</body>
</html>