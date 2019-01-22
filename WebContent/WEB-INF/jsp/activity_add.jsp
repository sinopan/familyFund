<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

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
		$("#activityAddForm").css('height',(innerHeight-topHeight)+'px');

		var contentWidth = $("#below").width() - $("#left").width();
		
		$("#activityAddForm").css('width',(contentWidth-1)+'px');
		
		/* 调整tr高度 */
		$("table tr").attr('height','45px');
		
		$("#menu>li ").css('margin-bottom','25px')
	})
	
	
	$(document).ready(function(){
		var optionStr = "";
		var optionCanYuRenYuanStr = "";
		/* 发送Ajax获取用户  */
		 $.get("${pageContext.request.contextPath}/user/ajax_list", function(data){
			var jsonObj = eval(data);
			console.log(jsonObj)
			console.log(jsonObj.length)
			for(var i=0; i<jsonObj.length; i++){
				optionStr += "<option value='"+jsonObj[i].id+"'>"+jsonObj[i].name+"</option>"
				
				optionCanYuRenYuanStr +=  "<option value='"+jsonObj[i].id+"'>"+jsonObj[i].name+"</option>"
			}
			
			$(optionCanYuRenYuanStr).appendTo($("#select_left"));
			$(optionStr).appendTo($("#planer"));
		 });
	})
</script>
<!--  -->
<script type="text/javascript">
window.onload = function() {
    //单个移动按钮派发事件
    document.getElementById("toRight1").onclick = function() {
          //获取左边所有的option
          var arr = document.getElementById("select_left").options;
          //判断是否被选中
          for(var i = 0; i < arr.length; i++) {
               if(arr[i].selected) {
                    document.getElementById("select_right").appendChild(arr[i]);
                    break;
               }
          }
    }
    //部分移动按钮派发事件
    document.getElementById("toRight2").onclick = function() {
          //获取左边所有的option
          var arr = document.getElementById("select_left").options;
          //
          for(var i = 0; i < arr.length; i++) {
               if(arr[i].selected) {
                    document.getElementById("select_right").appendChild(arr[i]);
                    i--;
               }
          }
    }
    //全部移动按钮派发事件
    document.getElementById("toRight3").onclick = function() {
          //获取左边所有的option
          var arr = document.getElementById("select_left").options;
          
          for(var i = 0; i < arr.length;) {
               document.getElementById("select_right").appendChild(arr[i]);
               //注意长度变化（相当于只移动第一个）
               //i--;
          }
    }
    //单个移动按钮派发事件
    document.getElementById("toLeft1").onclick = function() {
          //获取左边所有的option
          var arr = document.getElementById("select_right").options;
          //判断是否被选中
          for(var i = 0; i < arr.length; i++) {
               if(arr[i].selected) {
                    document.getElementById("select_left").appendChild(arr[i]);
                    break;
               }
          }
    }
    //部分移动按钮派发事件
    document.getElementById("toLeft2").onclick = function() {
          //获取左边所有的option
          var arr = document.getElementById("select_right").options;
          //
          for(var i = 0; i < arr.length; i++) {
               if(arr[i].selected) {
                    document.getElementById("select_left").appendChild(arr[i]);
                    i--;
               }
          }
    }
    //全部移动按钮派发事件
    document.getElementById("toLeft3").onclick = function() {
          //获取左边所有的option
          var arr = document.getElementById("select_right").options;
          
          for(var i = 0; i < arr.length;) {
               document.getElementById("select_left").appendChild(arr[i]);
               //注意长度变化（相当于只移动第一个）
               //i--;
          }
    }
}
/* 表单校验 */
function formValidate(){
	var beginTime = $("input[name='beginTime']").val();
	var endTime = $("input[name='endTime']").val();
	var content = $("textarea[name='content']").val();
	var planer = $("select[name='planer']").val();
	
	var participantsStr = "";
	var arr = document.getElementById("select_right").options;
    for(var i = 0; i < arr.length;i++) {
   	 participantsStr += arr[i].value+",";
    }
    //alert(participantsStr)
    console.log(participantsStr)
    $("#participants").val(participantsStr);
	var participants = $("input[name='participants']").val();
	
	/* 从上往下判断 */
	if (''==beginTime || beginTime.length==0) {
		$("#beginTime_tips").html("请选择开始时间！");
		return false;
	} else if(''==endTime || endTime.length==0){
		$("#endTime_tips").html("请选择结束时间！");
		return false;
	} else if(''==content || content.length==0){
		$("#content_tips").html("活动详情不能为空！");
		return false;
	} else if(''==planer || planer.length==0){
		$("#planer_tips").html("请选择规划人！");
		return false;
	} else if(''==participants || participants.length==0){
		$("#participants_tips").html("请选择参与人员！");
		return false;
	}
	return true;
}

function submitForm() {
	if (formValidate()) {//如果校验通过再提交
		console.log($('#activityAddForm').serialize())
		$.ajax({
			//几个参数需要注意一下
			type : "POST",//方法类型
			dataType : "json",//预期服务器返回的数据类型
			url : "${pageContext.request.contextPath}/activity/add",//url
			data : $('#activityAddForm').serialize(),
			success : function(result) {
				console.log(result);//打印服务端返回的数据(调试用)
				if (result.resultCode == 200) {
					console.log(result)
					//alert("SUCCESS");
					window.location.href="${pageContext.request.contextPath}/activity/list/1"
				}else {
					$("#message").html("添加基金计划失败！");
				}
				
			},
			error : function(result) {
				
			}
		});
	}
}
$(function(){
	$("input[name='beginTime']").focus(function(){
		$("#beginTime_tips").html("");
	})
	$("input[name='endTime']").focus(function(){
		$("#endTime_tips").html("");
	})
	$("textarea[name='content']").focus(function(){
		$("#content_tips").html("");
	})
	$("select[name='planer']").focus(function(){
		$("#planer_tips").html("");
	})
	$("#select_left").focus(function(){
		$("#participants_tips").html("");
	})
})
</script>
<style>
input[type='button'] {
    width: 50px;
}
</style>
<!--  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
		<div style="background-color: #8fd15ea6; float:left;">
		    <center>
		    	<form id="activityAddForm" action="#" method="post">
					<table>
						<tr>
							<td>活动开始时间:</td>
							<td><input type="date" name="beginTime"></td>
							<td id="beginTime_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td>活动结束时间:</td>
							<td><input type="date" name="endTime"></td>
							<td id="endTime_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td>活动详情：</td>
							<td><textarea rows="8" cols="100" name="content" ></textarea></td>
							<td id="content_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td>规划人：</td>
							<td>
								<select id="planer" name="planer"></select>
							<td id="planer_tips" style="width: 150px; color: red;"></td>
							</td>
						</tr>
						<tr>
							<td>选取参与人员：</td>
							<td><input type="hidden" id="participants" name="participants">
								<table>
									<tr>
										<td><select id="select_left" multiple="true"
											style="width: 100px" size="10">
										</select></td>
										<td><input type="button" value=">" id="toRight1"><br>
											<input type="button" value=">>" id="toRight2"><br>
											<input type="button" value=">>>" id="toRight3"><br>
										<br> <input type="button" value="<" id="toLeft1"><br>
											<input type="button" value="<<" id="toLeft2"><br>
											<input type="button" value="<<<" id="toLeft3"></td>
										<td><select id="select_right" multiple="true"
											style="width: 100px" size="10">

										</select></td>
									</tr>
								</table>
							</td>
						<td id="participants_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td></td>
							<td colspan="2" style="text-align: left;">
								<input type="button" id="submitButton" onclick="submitForm();" value="提交"/></input><input type="reset"></input>
							</td>
						</tr>
					</table>
				</form>
		    </center>
		</div>
	</div>
</div>
	
</body>
</html>