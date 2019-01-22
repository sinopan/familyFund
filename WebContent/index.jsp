<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>家庭启航</title>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){    
    
    var innerHeight = $(window).height();
   $("#welcomeDiv").css('height',(innerHeight*0.1)+'px');
    var eleHeight = innerHeight*0.77;
    var welcomeDivHeight =  $("#welcomeDiv").height();
    
   $("#links").css('height',(innerHeight-eleHeight-welcomeDivHeight)+'px');
   $(".carousel-inner").css('height',eleHeight+'px');
    
    $("#name").focus(function(){
        $("#tips").html("");
    })
    
})
/* 轮播时间控制 */
$(function(){
       $('#myCarousel').carousel({
         interval: 2000//设置轮播图片显示时长
       })
  })  
   
function formValidate(){
	var name = $("input[name='name']").val();
	var password = $("input[name='password']").val();
	
	/* 从上往下判断 */
	if (''==name || name.length==0) {
		$("#tips").html("用户名不能为空！");
		return false;
	} else if(''==password || password.length==0){
		$("#tips").html("密码不能为空！");
		return false;
	} 
	return true;
}   
</script>
<style>
#huyingyu {
	background-color: aqua;
}
.container-fluid {
    padding-right: 0px;
    padding-left: 0px;
    margin-right: 0px;
    margin-left: 0px;
}
</style>
<body>
	<div id="welcomeDiv" class="">
		<!-- 欢迎语 -->
		<div id="huanyingyu"
			style="border: 0px solid aqua; background-color: aqua;height: 100%;"
			class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<center>
				<h1>名城基金欢迎您的加入！</h1>
			</center>
		</div>
	</div>
	<div class="container-fluid">
		<!-- 轮播名城图片 -->
		<div id="lunbo" style="border: 0px solid red;padding-left: 0px;padding-right: 0px;"
			class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

			<div id="myCarousel" class="carousel slide">
				<!-- 轮播（Carousel）指标 -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				<!-- 轮播（Carousel）项目 -->
				<div class="carousel-inner" style="height: 550px;">
					<div class="item active">
						<img src="${pageContext.request.contextPath }/picture/1.jpg" alt="First slide">
						<div class="carousel-caption">标题 1</div>
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath }/picture/2.jpg" alt="Second slide">
						<div class="carousel-caption">标题 2</div>
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath }/picture/3.jpg" alt="Third slide">
						<div class="carousel-caption">标题 3</div>
					</div>
				</div>
				<!-- 轮播（Carousel）导航 -->
				<a class="left carousel-control" href="#myCarousel" role="button"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel" role="button"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
	</div>
		<!-- 底部 -->
		<!-- 注册、登陆 -->
		<div id="links" style="background-color: #e0e1b1;"
			class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><!-- #323716fc -->
			<center>
				<form method="post" action="${pageContext.request.contextPath }/user/login" onsubmit="return formValidate();">
					<div class="container-fluid" style="padding-top: 10px;">
						<div id="links"
							class="col-lg-1 col-md-6 col-sm-6 col-xs-6">
						</div>
						<div id="links"
							class="col-lg-1 col-md-6 col-sm-6 col-xs-6">
						</div>
						<div id="links"
							class="col-lg-1 col-md-6 col-sm-6 col-xs-6">
						</div>
						<div id="links"
							class="col-lg-1 col-md-6 col-sm-6 col-xs-6">
						</div>
						<div id="links"
							class="col-lg-1 col-md-6 col-sm-6 col-xs-6">
							username:
						</div>
						<div id="links"
							class="col-lg-1 col-md-6 col-sm-6 col-xs-6">
							<c:if test="${not empty name }">
								<input id="name" name="name" type="text" style="width: 100px;" 
									value="${name }">
							</c:if>	
							<c:if test="${empty name }">
								<input id="name" name="name" type="text" style="width: 100px;" 
									value="">
							</c:if>
							</input>
						</div>
						<div id="links"
							class="col-lg-1 col-md-6 col-sm-6 col-xs-6">
							password:
						</div>
						<div id="links"
							class="col-lg-1 col-md-6 col-sm-6 col-xs-6">
							<input id="password" name="password" type="password" style="width: 100px;">
						</div>
						<div id="links"
							class="col-lg-1 col-md-6 col-sm-6 col-xs-6">
						</div>
						<div id="links"
							class="col-lg-1 col-md-6 col-sm-6 col-xs-6">
						</div>
						<div id="links"
							class="col-lg-1 col-md-6 col-sm-6 col-xs-6">
						</div>
						<div id="links"
							class="col-lg-1 col-md-6 col-sm-6 col-xs-6">
						</div>
						
						
					</div>
					<div style="margin-top: 10px;">
						<input id="login" type="submit" value="登陆"></input>
						<input id="" type="button" value="注册" onclick="window.location.href='${pageContext.request.contextPath }/user/regist'"></input>
					</div>
					<div>
						<span id="tips" style="width: 150px; color: red;">
							${failedMsg }
						</span>
					</div>
				</form>
			</center>
		</div>
</body>
</html>