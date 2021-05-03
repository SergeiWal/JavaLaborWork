<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.04.2021
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="Header.jsp"/>
    <h1 style="text-align: center">Login</h1>
    <div style="text-align: center; color: red">${error}</div>
    <form action="/Lab9-1.0/login" method="get" style="text-align: center">
        <h3>Login:</h3>
        <input type="text" name="login"/><br/>
        <h3>Password:</h3>
        <input type="password" name="password"/><br/><br/>
        <input type="submit" value="submit"/><br/>
    </form>
    <form action="registre-page" method="get" style="text-align: center">
        <input type="submit" value="Registration">
    </form>
    <jsp:include page="Footer.jsp"/>
</body>
</html>
