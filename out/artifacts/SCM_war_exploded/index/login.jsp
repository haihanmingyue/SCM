<%--
  Created by IntelliJ IDEA.
  User: haihanmingyue
  Date: 2020/8/25
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <link href="${pageContext.request.contextPath}/resource/css/style.css" rel="stylesheet" type="text/css">

</head>
<body bgcolor="#ffffff" >

<form action="${pageContext.request.contextPath}/login" method="post">
    <table width="100%"  border="0"  >
        <tr>
            <td class="title1">&nbsp;</td>
        </tr>
        <tr>
            <td width="50%" align="right">用户名&nbsp;&nbsp;</td>
            <td width="50%"><input type="text" name="username" required="required"/></td>
        </tr>
        <tr>
            <td align="right">密码&nbsp;&nbsp;&nbsp;</td>
            <td><input type="password" name="password" required="required"/></td>
        </tr>
        <tr>
            <td class="title1"><div align="right"><input type="submit"  value="登录"/></div></td>
            <td class="title1"><div align="left"><input type="reset" value="重置"/></div></td>
        </tr>
    </table>
</form>
</body>
<script>
    window.open('/SCM/index/title.jsp','topFrame');
</script>
</html>
