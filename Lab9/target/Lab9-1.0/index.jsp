<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a><br/>
<a href="base_info_page">Base info</a><br/>
<a href="login_page">Login</a><br/><br/>
<a href="home">Home</a><br/><br/>
<form action="getHello" method="get">
    <input type="submit" value="get"/>
</form><br/>
<form action="getHello" method="post">
    <input type="submit" value="post"/>
</form><br/>
<form action="first" method="get">
    <input type="submit" value="backAndForth"/>
</form>
</body>
</html>