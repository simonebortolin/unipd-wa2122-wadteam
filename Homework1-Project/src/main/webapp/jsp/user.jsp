<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 31/03/2022
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="user" type="it.unipd.dei.wa2122.wadteam.resources.UserCredential"--%>
<%--@elvariable id="employee" type="it.unipd.dei.wa2122.wadteam.resources.Employee"--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="description" content="Electromechanics Shop">
    <meta name="author" content="WAD Team">

    <title>User: <c:out value="${employee.username}"/> | Electromechanics Shop</title>
</head>

<body>
<c:import url="/jsp/include/header.jsp"/>
<h1>User: <c:out value="${employee.username}"/></h1>

<ul>
    <li>Name: ${employee.name}</li>
    <li>Surname: ${employee.surname}</li>
    <li>Role: ${employee.role.toString()}</li>
    <br>
    <a href="<c:url value="/user/modify"/>">Edit</a>
    <a href="<c:url value="/user/password"/>">Change Password</a>
</ul>

<%@ include file="/html/include/footer.html"%>
</body>
</html>