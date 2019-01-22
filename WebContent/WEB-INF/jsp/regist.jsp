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
<style type="text/css">
tr {
	height: 40px;
}

</style>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		
        $("input[name='name']").blur(function(){
        	var name = $("input[name='name']").val();
    		if (""!=name && name.length>0) {	
    			var param = {name:name};
    			$.post("${pageContext.request.contextPath}/user/check",param,function(message){
    					//console.log(message)
    					if (message.trim().length>0) {
    						$("#name_tips").html(message);
						}
    				})
    			}
        })
		
        $("input[name='name']").focus(function(){
        	$("#name_tips").html("");
        })
        $("input[name='password']").focus(function(){
        	$("#password_tips").html("");
        })
        $("input[name='gender']").focus(function(){
        	$("#gender_tips").html("");
        })
        $("input[name='bithday']").focus(function(){
        	$("#bithday_tips").html("");
        })
        $("input[name='age']").focus(function(){
    		$("#age_tips").html("");
        })
        $("input[name='mobile']").focus(function(){
        	$("#mobile_tips").html("");
        })
    })  
    
function formValidate(){
	/* 表单校验 */
	var name = $("input[name='name']").val();
	var password = $("input[name='password']").val();
	var gender = $("select[name='gender']").val();
	var bithday = $("input[name='bithday']").val();
	var age = $("input[name='age']").val();
	var mobile = $("input[name='mobile']").val();
	
	/* 从上往下判断 */
	if (''==name || name.length==0) {
		$("#name_tips").html("用户名不能为空！");
		return false;
	} else if(''==password || password.length==0){
		$("#password_tips").html("密码不能为空！");
		return false;
	} else if(''==gender || gender.length==0){
		$("#gender_tips").html("请选择性别！");
		return false;
	} else if(''==bithday || bithday.length==0){
		$("#bithday_tips").html("请填写生日！");
		return false;
	} else if(''==age || age.length==0){
		$("#age_tips").html("请填写年龄！");
		return false;
	}  else if(isNaN(age)){/* 判断是否魏数字 */
		$("#age_tips").html("年龄必须是整数！");
		return false;
	} else if(''==mobile || mobile.length==0){
		$("#mobile_tips").html("请填写年龄！");
		return false;
	}  else if(isNaN(mobile)){/* 判断是否魏数字 */
		$("#mobile_tips").html("手机格式错误！");
		return false;
	} 
	return true;	
}

function login() {
	if (formValidate()) {//如果校验通过再提交
		console.log($('#registForm').serialize())
		$.ajax({
			//几个参数需要注意一下
			type : "POST",//方法类型
			dataType : "json",//预期服务器返回的数据类型
			url : "${pageContext.request.contextPath}/user/add",//url
			data : $('#registForm').serialize(),
			success : function(result) {
				console.log(result);//打印服务端返回的数据(调试用)
				if (result.resultCode == 200) {
					console.log(result)
					//alert("SUCCESS");
					window.location.href="${pageContext.request.contextPath}/user/regist_success"
				}else {
					$("#message").html("注册失败！");
				}
				
			},
			error : function(result) {
				
			}
		});
	}
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
		<form id="registForm" method="post" action="#">
			<span id="message"></span>
			<table style="margin-top: 80px;">
				<tr>
					<td>用户名:</td>
					<td><input type="text" name="name"></td>
					<td style="width: 300px;"><span id="name_tips" style="color: red;"></span></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input type="password" name="password"></td>
					<td style="width: 150px;"><span id="password_tips" style="color: red;"></span></td>
				</tr>
				<tr>
					<td>性别:</td>
					<td><select name="gender" style="width: 178.4px;">
						<option value="1">男</option>
						<option value="0">女</option>
					</select></td>
					<td style="width: 150px;"><span id="gender_tips" style="color: red;"></span></td>
				</tr>
				<tr>
					<td>生日:</td>
					<td><input type="date" name="bithday"></td>
					<td style="width: 150px;"><span id="bithday_tips" style="color: red;"></span></td>
				</tr>
				<tr>
					<td>年龄:</td>
					<td><input type="text" name="age"></td>
					<td style="width: 150px;"><span id="age_tips" style="color: red;"></span></td>
				</tr>
				<tr>
					<td>手机:</td>
					<td><input type="text" name="mobile"></td>
					<td style="width: 150px;"><span id="mobile_tips" style="color: red;"></span></td>
				</tr>
				<tr>
					<td colspan="3">
						<input type="button" value="提交" onclick="login();"></input>
						<input type="button" value="取消" onclick="window.history.go(-1)"></input>
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>