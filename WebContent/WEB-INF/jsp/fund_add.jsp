<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
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
	$(document).ready(function(){
		/* 发送jajax请求 */
		$.get("${pageContext.request.contextPath}/fundplanType/records",function(result){
			console.log(result);
			
			var optionStr = "";
			var consumptionTypesJson = JSON.parse(result);
			for (var key in consumptionTypesJson) {
			    //console.log(key +"--"+ consumptionTypesJson[key]);     //获取key值、value值
			    optionStr += "<option value="+key+">"+consumptionTypesJson[key]+"</option>"
			}
			$(optionStr).appendTo($("#planType"));
		})
		
	})


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
		$("#addFundForm").css('height',(innerHeight-topHeight)+'px');

		var contentWidth = $("#below").width() - $("#left").width();
		
		$("#addFundForm").css('width',(contentWidth-1)+'px');
		
		/* 调整tr高度 */
		$("table tr").attr('height','45px');
		
		$("#menu>li ").css('margin-bottom','25px')
	})
	
$(function(){
      $("input[name='amountGoal']").focus(function(){
      	$("#amountGoal_tips").html("");
      })
      $("input[name='realizeGoal']").focus(function(){
      	$("#realizeGoal_tips").html("");
      })
      $("input[name='beginTime']").focus(function(){
      	$("#beginTime_tips").html("");
      })
      $("input[name='endTime']").focus(function(){
      	$("#endTime_tips").html("");
      })
      $("select[name='planType']").focus(function(){
  		$("#planType_tips").html("");
      })
      $("input[name='planState']").focus(function(){
      	$("#planState_tips").html("");
      })
      $("input[name='note']").focus(function(){
      	$("#note_tips").html("");
      })
  })  
	
/* 表单校验 */
function formValidate(){
	var amountGoal = $("input[name='amountGoal']").val();
	var realizeGoal = $("input[name='realizeGoal']").val();
	var beginTime = $("input[name='beginTime']").val();
	var endTime = $("input[name='endTime']").val();
	var planType = $("select[name='planType']").val();
	var planState = $("input[name='planState']").val();
	var note = $("input[name='note']").val();
	
	/* 从上往下判断 */
	if (''==amountGoal || amountGoal.length==0) {
		$("#amountGoal_tips").html("目标金额不能为空！");
		return false;
	} else if(isNaN(amountGoal)){/* 判断是否魏数字 */
		$("#amountGoal_tips").html("目标金额必须是数字！");
		return false;
	} else if(''==realizeGoal || realizeGoal.length==0){
		$("#realizeGoal_tips").html("已存金额不能为空！");
		return false;
	} else if(isNaN(realizeGoal)){/* 判断是否魏数字 */
		$("#realizeGoal_tips").html("已存金额必须是数字！");
		return false;
	}  else if(''==beginTime || beginTime.length==0){
		$("#beginTime_tips").html("请选择开始时间！");
		return false;
	} else if(''==endTime || endTime.length==0){
		$("#endTime_tips").html("请选择结束时间！");
		return false;
	} else if(''==planType || planType.length==0){
		$("#planType_tips").html("请选择计划类型！");
		return false;
	} else if(''==note || note.length==0){
		$("#note_tips").html("请填写备注，以区分同类型的其他基金计划！");
		return false;
	}
	return true;
}	
	
function submitForm() {
	if (formValidate()) {//如果校验通过再提交
		console.log($('#addFundForm').serialize())
		$.ajax({
			//几个参数需要注意一下
			type : "POST",//方法类型
			dataType : "json",//预期服务器返回的数据类型
			url : "${pageContext.request.contextPath}/fundplan/add", //url
			data : $('#addFundForm').serialize(),
			success : function(result) {
				console.log(result);//打印服务端返回的数据(调试用)
				if (result.resultCode == 200) {
					console.log(result)
					//alert("SUCCESS");
					window.location.href="${pageContext.request.contextPath}/fundplan/list/1"
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
		    	<form id="addFundForm" action="#" method="post">
					<span id="message" style="color: red;"></span>
					<table>
						<tr>
							<td>存款目标:</td>
							<td><input type="text" name="amountGoal"></td>
							<td id="amountGoal_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td>已存金额:</td>
							<td><input type="text" name="realizeGoal"></td>
							<td id="realizeGoal_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td>开始日期：</td>
							<td><input type="date" name="beginTime"></td>
							<td id="beginTime_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td>截至日期：</td>
							<td><input type="date" name="endTime"></td>
							<td id="endTime_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td>计划类型：</td>
							<td><select name="planType" id="planType">
									<option value="">---请选择---</option>
							</select></td>
							<td id="planType_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td>计划说明：</td><!-- 一定要填写，这样增加消费记录页面会便于选择 -->
							<td>
								<input type="text"  name="note" id="note"></input>	
							</td>
							<td id="note_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td>计划状态：</td>
							<td><input type="radio" name="planState" value="0" checked="checked">未完成</input>
								<input type="radio" name="planState" value="1">已完成</input></td>
							<td id="planState_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td colspan="3">
								<input type="button" value="提交" onclick="submitForm();"></input>
								<input type="reset">
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