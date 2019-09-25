<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/21 0021
  Time: 上午 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/loginServlet" method="post">
    用户名：<input type="text" name="username" placeholder="请输入用户名"/><br/>
    密码：<input type="password" name="password" placeholder="请输入密码"/><br/>
    <input type="submit" value="提交">
</form>

<div style="color: red">
   ${login_error}
</div>
</body>
</html>
