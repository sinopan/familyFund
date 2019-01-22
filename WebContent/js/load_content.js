
/* 点击左侧导航时加载 */
function load_member_list(url){
	/* 发送ajax请求 */
	$.get(url, function(result){
/*		function load_member_list(){
			 发送ajax请求 
			$.get("${pageContext.request.contextPath}/member/list", function(result){
*/		console.log(result[0].name)
		console.log(result[0].bithday)
	    //$("div").html(result);
		var xunhuanStr = "";

		for (var i = 0; i < result.length; i++) {
			xunhuanStr += "<tr>"
			+		"<td>"+result[i].name+"</td>"
			+		"<td>"+result[i].gender+"</td>"
			+		"<td>"+new Date(result[i].bithday)+"</td>"
			+		"<td>"+result[i].age+"</td>"
			+		"<td>"+result[i].mobile+"</td>"
			+		"<td><a href=\"#\">修改</a></td>"
			+	"</tr>	"
		}
	    var eleStr = "<form id=\"memberForm\" action=\"\" method=\"post\">"
						+"成员列表："
						+"<table width=\"90%\" border=1>"
						+"<tr>"
						+	"<td>姓名</td>"
						+	"<td>性别</td>"
						+	"<td>生日</td>"
						+	"<td>年龄</td>"
						+	"<td>电话</td>"
						+	"<td>操作</td>"
						+"</tr>"
						
						+xunhuanStr
						/* 
						+"<c\:forEach items=\"${result}\" var=\"member\">"
						+	"<tr>"
						+		"<td>${member.name }</td>"
						+		"<td>${member.gender }</td>"
						+		"<td><fmt\:formatDate value=${member.bithday} pattern=\"yyyy-MM-dd HH:mm:ss\"/></td>"
						+		"<td>${member.age }</td>"
						+		"<td>${member.mobile }</td>"
						+		"<td><a href=\"#\">修改</a></td>"
						+	"</tr>	"
						+"</c\:forEach>" */
						
						+"</table>"
					+"</form>"
		$("#content").html("");
		$(eleStr).appendTo($("#content"));				
    });
}