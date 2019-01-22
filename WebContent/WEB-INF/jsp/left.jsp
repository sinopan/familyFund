<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.ui-menu { width: 150px; }
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div id="mainContentDiv" style="height:100%;background-color: green; padding-left: 30px;padding-top: 50px;">
		<div style="">
			<ul id="menu">
				<!-- <li class="ui-state-disabled"><a href="#">Aberdeen</a></li> -->
				<li><a href="#">成员管理</a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/user/list/1">成员列表</a></li>
					</ul>
				</li>
				<li><a href="#">收支管理</a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/paymentWithdraw/add_page">收支录入</a></li>
						<li><a href="${pageContext.request.contextPath}/paymentWithdraw/list/1">收支列表</a></li>
					</ul>
				</li>
				<li><a href="#">基金期划</a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/fundplan/add_page">新增基金规划</a></li>
						<li><a href="${pageContext.request.contextPath}/fundplan/list/1">规划列表</a></li>
					</ul>
				</li>
				<li><a href="#">家庭活动</a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/activity/add_page">活动规划</a></li>
						<li><a href="${pageContext.request.contextPath}/activity/list/1">活动记录</a></li>
					</ul>
				</li>
				<li><a href="#">图表展示</a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/paymentWithdraw/chart_display">收支图表</a></li>
						<li><a href="${pageContext.request.contextPath}/fundplan/chart_display">家庭基金图表</a></li>
						<li><a href="#" onclick="alert('敬请期待！')">活动图表</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>