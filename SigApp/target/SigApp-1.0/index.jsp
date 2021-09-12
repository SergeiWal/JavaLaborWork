<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller">
    <label>FIO</label>
    <input type="text"  name="FIO"/><br/>
    <label>Avg Number</label>
    <input type="number"  name="number"/><br/>
    <button type="submit" name="submit" value="ADD">ADD</button>
    <button type="submit" name="submit" value="GET">GET</button>
</form>
</body>
</html>