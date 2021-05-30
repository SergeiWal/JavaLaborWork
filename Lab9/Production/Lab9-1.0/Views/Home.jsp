<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.04.2021
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
    <div>
        <jsp:include page="Header.jsp"/>
    </div>

    <div style="text-align: center">
        <h2 style="text-align: center"> Add new Uniwer</h2>
        <h3 style="color: red">${error}</h3>
        <form action="/Lab9-1.0/add" method="get" style="text-align: center">
            <h3>Uniwer name:</h3>
            <input type="text" name="name"/>
            <h3>City:</h3>
            <input type="text" name="city"/>
            <h3>Country:</h3>
            <input type="text" name="country"/><br/>
            <input type="submit" value="add">
        </form>
    </div>

    <div style="text-align: center">
        <h2 style="text-align: center"> Delete Uniwer</h2>
        <h3 style="color: red">${error}</h3>
        <form action="/Lab9-1.0/del" method="get" style="text-align: center">
            <h3>Uniwer id:</h3>
            <input type="number" name="id"/>
            <br/><br/>
            <input type="submit" value="del">
        </form>
    </div>

    <div style="margin-top: 20px " >

        <table class="w3-table-all w3-hoverable">
            <tr>
                <th> Id </th>
                <th> Name </th>
                <th> City </th>
                <th> Country </th>
            </tr>
            <c:forEach var="uniwer" items="${uniwers}">
                <tr>
                    <td>${uniwer.getId()}</td>
                    <td>${uniwer.getUni_name()}</td>
                    <td>${uniwer.getCity()}</td>
                    <td>${uniwer.getCountry()}</td>
                </tr>
            </c:forEach>
        </table>

    </div>

    <div>
        <jsp:include page="Footer.jsp"/>
    </div>
</body>
</html>
