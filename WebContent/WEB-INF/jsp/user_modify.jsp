<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<title>欢迎注册</title>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript">
    
/* 表单校验 */
function formValidate(){
	var name = $("input[name='name']").val();
	var password = $("input[name='password']").val();
	var confirmPassword = $("input[name='confirmPassword']").val();
	var gender = $("select[name='gender']").val();
	var bithday = $("input[name='bithday']").val();
	var age = $("input[name='age']").val();
	var mobile = $("input[name='mobile']").val();
	
	/* 从上往下判断 */
	if (''==name || name.length==0) {
		alert("用户名不能为空！");
		return false;
	} else if(''==password || password.length==0){
		alert("密码不能为空！");
		return false;
	} else if(''==confirmPassword || confirmPassword.length==0){
		alert("请输入确认密码！");
		return false;
	} else if(confirmPassword.trim()!=password.trim()){
		alert("密码输入不一致！");
		return false;
	} else if(''==gender || gender.length==0){
		alert("请选择性别！");
		return false;
	} else if(''==bithday || bithday.length==0){
		alert("请填写生日！");
		return false;
	} else if(''==age || age.length==0){
		alert("请填写年龄！");
		return false;
	}  else if(isNaN(age)){/* 判断是否魏数字 */
		alert("年龄必须是整数"); 
		return false;
	} else if(''==mobile || mobile.length==0){
		alert("请填写年龄！");
		return false;
	}  else if(isNaN(mobile)){/* 判断是否魏数字 */
		alert("手机格式错误"); 
		return false;
	} 
	return true;
}	
</script>
<body>
	<center>
		<div>
			<!-- 欢迎语 -->
			<div style="border: 1px solid aqua; background-color: aqua;">
				<center>
					<h1>名城基金欢迎您的加入！</h1>
				</center>
			</div>
		</div>
		<form method="post" action="${pageContext.request.contextPath }/user/modify" onsubmit="return formValidate()">
			<div>
				<input type="hidden" value="${user.id }" name="id">
				<!-- 用户名 -->
				<div>
					用户名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" name="name" readonly="readonly" value="${user.name }" style="background-color: #c6e0c6;">
				</div>
				<!-- 密码 -->
				<div>
					密码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="password" name="password" value="">
				</div>
				<div>
					确认密码：
					<input type="password" name="confirmPassword" value="">
				</div>
				<!-- 性别 -->
				<div style="">
					性别：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select name="gender" style="width: 178.4px;">
						<option value="1">男</option>
						<option value="0">女</option>
					</select>
					<!-- <input type="text" name="gender"> -->
				</div>
				<!-- 生日 -->
				<div id="links" style="position: relative; left: -19px;">
					生日：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="date" name="bithday" value="" />
				</div>
				<!-- 年龄 -->
				<div>
					年龄：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" name="age" value="" />
				</div>
				<!-- 手机 -->
				<div>
					手机：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" name="mobile" value="" />
				</div>
				
			</div>
			
			<div style="margin-top: 15px;">
				<input type="submit" value="提交"></input>
				<input type="reset" value="清除"></input>
			</div>
		</form>
	</center>
</body>
<script type="text/javascript">
	$(function(){
		$("form>div>div").css('margin-top','20px')
		$("form>div>div:first").css('margin-top','100px');
	})
</script>
</html>