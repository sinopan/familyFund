<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
Date.prototype.toLocaleString = function() {
    return this.getFullYear() + "/" + (this.getMonth() + 1) + "/" + this.getDate() + "/ " + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();
};
</script>
</head>
<body>
	<div id="welcome">
		<div style="border: 1px solid red; background-color: aqua;">
			<%-- <center><h1>${sessionScope.activeMember.name}, 欢迎使用家庭基金管理系统</h1></center> --%>
			<center>
				<MARQUEE behavior="scroll" contenteditable="true" scrollamount="3" width="100%">
					<SPAN unselectable="on"><h1>${sessionScope.user.name}, 欢迎使用家庭基金管理系统</h1></SPAN>
				</MARQUEE>
			</center>
		</div>
	</div>
</body>
</html>