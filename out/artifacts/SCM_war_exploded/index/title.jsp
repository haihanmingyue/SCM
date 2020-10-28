
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>标题</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script type="text/javascript" src="${pageContext.request.contextPath}/resource/script/jquery.min.js"></script>
		<link href="${pageContext.request.contextPath}/resource/css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/resource/script/common.js"></script>

		<script type="text/javascript">
			
			function closeAlert() {
				var question = confirm("您要退出系统，确定吗？");
				if (question){
					window.open('${pageContext.request.contextPath}/logout', 'mainFrame');
				}
			}
			
		</script>
	</head>

	<body topmargin=0 leftmargin=0>
		<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" height="25">
		  <tr>
			<td class="toolbar">&nbsp;</td>
			  <td id="loginDateTd" class="toolbar" width="200px" style="text-align: right;">
				  登录时间:</td>
			  <td width="20px" nowrap class="toolbar">|</td>
			<td class="toolbar" id="loginBtn" width="45px" onMouseOver="OMO()" onMouseOut="OMOU()" onClick="window.open('login.jsp', 'mainFrame')">
			  <img src="../resource/images/jrxt.jpg" border="0">登录</td>
			<td width="20px" id="xian" nowrap class="toolbar">|</td>
			<td class="toolbar" width="45px" onMouseOver="OMO()" onMouseOut="OMOU()" onClick="closeAlert()">
			  <img src="../resource/images/lkxt.jpg" border="0">离开</td>
			<td width="20px" nowrap class="toolbar">|</td>

			  <td class="toolbar" width="200px" style="text-align: left;">
				  当前时间:<span id="getNowDate"></span></td>
			  <td width="20px" nowrap class="toolbar">|</td>
		  </tr>
		</table>
	</body>
<script>
	var t;
	var now = "";
	var loginDateTd = $("#loginDateTd");
	loginDateTd.hide();
	var loginBtn = $("#loginBtn");
	var xian = $("#xian");
	function showTime() {
		var date = new Date(); //日期对象
		now = date.getFullYear()+"-"+(date.getMonth() + 1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds(); //读英文就行了
		$("#getNowDate").text(now); //div的html是now这个字符串
	}
	t = setInterval("showTime()",1000);
	<c:if test="${not empty user}">
	loginDateTd.show()
	loginDateTd.text("${user.account}"+" "+"${user.name}");
	loginBtn.hide();
	xian.hide();
	</c:if>
</script>
</html>
