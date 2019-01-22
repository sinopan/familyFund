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
		$("#paymentWithdrawForm").css('height',(innerHeight-topHeight)+'px');

		var contentWidth = $("#below").width() - $("#left").width();
		
		$("#paymentWithdrawForm").css('width',(contentWidth-1)+'px');
		
		/* 调整tr高度 */
		$("table tr").attr('height','45px');
		
		$("#menu>li ").css('margin-bottom','25px')
	})
	
	$(document).ready(function(){
		var optionMemberStr = "";
		var optionPlanStr = "";
		/* 发送Ajax获取用户  */
		 $.get("${pageContext.request.contextPath}/user/ajax_list", function(data){
			var jsonObj = eval(data);
			for(var i=0; i<jsonObj.length; i++){
				optionMemberStr += "<option value='"+jsonObj[i].id+"'>"+jsonObj[i].name+"</option>"
			}
			$(optionMemberStr).appendTo($("#user_id"));
		 });
		
	})

	$(function(){
		 /* 交易时间下拉框改变时动态查询交易时间月度范围内基金计划列表，追加到fund_plan_id节点  */
		 $("input[name='exchangeTime']").change(function(){
			/* 发送Ajax获取已有的基金计划类型（注：必须是现有基金计划！每个收支的所属基金类型一定属于一个已有的基金计划。）  */
			$.ajaxSettings.async = false;
			var url = "${pageContext.request.contextPath}/fundplan/existfundplan/"+$("input[name='exchangeTime']").val();
			console.log(url)
			$.get(url, function(result){
				//console.log(data)
				//console.log(data.length)
				var optionStr = "";
				if (result.length>0) {
					var consumptionTypesJson = JSON.parse(result);
					for (var key in consumptionTypesJson) {
					    //console.log(key +"--"+ consumptionTypesJson[key]);     //获取key值、value值
					    optionStr += "<option value="+key+">"+consumptionTypesJson[key]+"</option>"
					}
				}
				$("#fund_plan_id").html("");
				$(optionStr).appendTo($("#fund_plan_id"));
			 });
		 })
	})
	
	
$(function(){
	 $("input[name='exchangeAmount']").focus(function(){
     	$("#exchangeAmount_tips").html("");
     })
     $("select[name='exchangeType']").focus(function(){
     	$("#exchangeType_tips").html("");
     })
     $("input[name='exchangeTime']").focus(function(){
     	$("#exchangeTime_tips").html("");
     })
     $("textarea[name='exchangePlace']").focus(function(){
     	$("#exchangePlace_tips").html("");
     })
     $("textarea[name='exchangeDeatil']").focus(function(){
 		$("#exchangeDeatil_tips").html("");
     })
     $("select[name='userId']").focus(function(){
     	$("#userId_tips").html("");
     })
     $("select[name='fundPlanId']").focus(function(){
     	$("#fundPlanId_tips").html("");
     })
})	
	
/* 表单校验 */
function formValidate(){
	var exchangeAmount = $("input[name='exchangeAmount']").val();
	var exchangeType = $("select[name='exchangeType']").val();
	var exchangeTime = $("input[name='exchangeTime']").val();
	var exchangePlace = $("textarea[name='exchangePlace']").val();
	var exchangeDeatil = $("textarea[name='exchangeDeatil']").val();
	var userId = $("select[name='userId']").val();
	var fundPlanId = $("select[name='fundPlanId']").val();
	
	/* 从上往下判断 */
	if (''==exchangeAmount || exchangeAmount.length==0) {
		$("#exchangeAmount_tips").html("交易金额不能为空！");
		return false;
	} else if(isNaN(exchangeAmount)){/* 判断是否为数字 */
		//console.log(isNaN(exchangeAmount))
		$("#exchangeAmount_tips").html("交易金额必须是数字");
		return false;
	}else if(''==exchangeType || exchangeType.length==0){
		$("#exchangeType_tips").html("请选择交易类型！");
		return false;
	} else if(''==exchangeTime || exchangeTime.length==0){
		$("#exchangeTime_tips").html("请选择交易时间！");
		return false;
	} else if(''==exchangePlace || exchangePlace.length==0){
		$("#exchangePlace_tips").html("请填写交易地点！");
		return false;
	}  else if(''==exchangeDeatil || exchangeDeatil.length==0){
		$("#exchangeDeatil_tips").html("备注不能为空！");
		return false;
	} else if(''==userId || userId.length==0){
		$("#userId_tips").html("选择交易人！");
		return false;
	} else if(''==fundPlanId || null==fundPlanId || fundPlanId.length==0){
		$("#fundPlanId_tips").html("请选择所属计划类型！");
		return false;
	}
	return true;
}	
/* 点击提交按钮 */
function submitForm() {
	if (formValidate()) {//如果校验通过再提交
		console.log($('#paymentWithdrawForm').serialize())
		$.ajax({
			//几个参数需要注意一下
			type : "POST",//方法类型
			dataType : "json",//预期服务器返回的数据类型
			url : "${pageContext.request.contextPath}/paymentWithdraw/add",//url
			data : $('#paymentWithdrawForm').serialize(),
			success : function(result) {
				console.log(result);//打印服务端返回的数据(调试用)
				if (result.resultCode == 200) {
					console.log(result)
					//alert("SUCCESS");
					window.location.href="${pageContext.request.contextPath}/paymentWithdraw/list/1"
				}else {
					$("#message").html("添加交易记录失败！");
				}
				
			},
			error : function(result) {
				
			}
		});
	}
}	
</script>
<style>
input[type='button'] {
    width: 50px;
}
</style>
<!--  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收支录入</title>
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
		    	<form id="paymentWithdrawForm" action="#" method="post">
					<table>
						<tr>
							<td>交易金额:</td>
							<td><input type="text" name="exchangeAmount"></td>
							<td id="exchangeAmount_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td>交易类型:</td>
							<td>
								<select name="exchangeType">
									<option value="1" selected="selected">存款</option>
									<option value="0">消费</option>
								</select>
							</td>
							<td style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td>交易日期:</td>
							<td><input type="date" name="exchangeTime"></td>
							<td id="exchangeTime_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td>交易地点：</td>
							<td><textarea rows="2" cols="45" name="exchangePlace" ></textarea></td>
							<td id="exchangePlace_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td>备注：</td>
							<td>
								<textarea rows="8" cols="100" id="exchangeDeatil" name="exchangeDeatil" ></textarea>
							</td>
							<td id="exchangeDeatil_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td>交易人：</td>
							<td>
								<select id="user_id" name="userId">
								</select>
							</td>
							<td id="userId_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td>所属基金计划：</td>
							<td>
								<select id="fund_plan_id" name="fundPlanId">
								</select>
							</td>
							<td id="fundPlanId_tips" style="width: 150px; color: red;"></td>
						</tr>
						<tr>
							<td></td>
							<td colspan="2">
								<input type="button" id="submitButton" value="提交" onclick="submitForm();"/>
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