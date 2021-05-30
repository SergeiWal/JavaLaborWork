<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.04.2021
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="wsaprinttable" uri="../WEB-INF/tld/WsaPrintTable.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="sql" uri = "http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="ru-Ru"/>
<fmt:requestEncoding value="utf-8"/>
<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver"
                   url="jdbc:mysql://localhost/lab9?serverTimezone=Europe/Moscow&useSSL=false"
                   user="root"
                   password="123hateGnom546"/>
<c:set var="UserRole" value="user"/>
<sql:query var="rs" dataSource="${db}">
    SELECT * FROM users WHERE user_role = ?;
    <sql:param value="${UserRole}"/>
</sql:query>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
    <div>
        <c:import url="Header.jsp"/>
    </div>

    <c:set var="Variable" value="10" scope="page"/>


    <div style="text-align: center">
        <h2 style="text-align: center">${fn:trim(" Add new Uniwer")}</h2>
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
                <th>${fn:toUpperCase("Id")}</th>
                <th>${fn:toUpperCase("Name")}</th>
                <th>${fn:toUpperCase("City")}</th>
                <th>${fn:toUpperCase("Country")}</th>
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
        <h4>Uni count: ${fn:length(uniwers)}</h4>
    </div>

    <div style="margin-top: 20px " >
        <h3>Info about users</h3>
        <table class="w3-table-all w3-hoverable">
            <tr>
                <th>${fn:toLowerCase("Id")}</th>
                <th>${fn:toLowerCase("Name")}</th>
                <th>${fn:toLowerCase("Login")}</th>
                <th>${fn:toLowerCase("Role")}</th>
            </tr>
            <c:forEach var="user" items="${rs.rows}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.user_name}</td>
                    <td>${user.login}</td>
                    <td>${user.user_role}</td>
                </tr>
            </c:forEach>
        </table>

    </div>

    <div>
        <wsaprinttable:print uniwers="${uniwers}"/>
    </div>

    <div>
        <c:import url="Footer.jsp"/>
        <c:catch var="formatException">
            <c:if test="${empty Variable}">
                <c:out value="Empty"/>
            </c:if>
            <c:if test="${not empty Variable}">
                <c:out value="Value: ${Variable}"/>
            </c:if>
        </c:catch>
        <c:remove var="Variable"/>
    </div>


    <div style="text-align: center">
        <jsp:useBean id="now" class="java.util.Date"/>
        <fmt:formatDate value="${now}" type="time" timeStyle="medium"/><br/>
        <fmt:formatNumber value="100" type="percent"/><br/>
    </div>
</body>
</html>
