<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="Header.jsp"/>
<h1 style="text-align: center">Login</h1>
<div style="text-align: center; color: red">${error}</div>
<form method="post" action="${pageContext.request.contextPath}/controller?command=login" style="text-align: center">
    <h3>Login:</h3>
    <input type="text" name="login"/><br/>
    <h3>Password:</h3>
    <input type="password" name="password"/><br/><br/>
    <input type="submit" value="Login">
</form>
<form action="${pageContext.request.contextPath}/controller?command=registration_page" method="post" style="text-align: center">
    <input type="submit" value="Registration">
</form>
<jsp:include page="Footer.jsp"/>
</body>
</html>
