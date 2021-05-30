<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.05.2021
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Vegetable Information:</h2>
<c:set var="vegetable">
    <vegetables>
        <vegetable>
            <name>onion</name>
            <price>40/kg</price>
        </vegetable>
        <vegetable>
            <name>Potato</name>
            <price>30/kg</price>
        </vegetable>
        <vegetable>
            <name>Tomato</name>
            <price>90/kg</price>
        </vegetable>
    </vegetables>
</c:set>
<x:parse xml="${vegetable}" var="output"/>
<b>Name of the vegetable is</b>:
<x:out select="$output/vegetables/vegetable[1]/name" /><br>
<b>Price of the Potato is</b>:
<x:out select="$output/vegetables/vegetable[2]/price" />
</body>
</html>
