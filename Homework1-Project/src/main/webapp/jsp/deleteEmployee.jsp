<%--
  Created by IntelliJ IDEA.
  User: simonebastasin
  Date: 12/04/2022
  Time: 00:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="employee" type="it.unipd.dei.wa2122.wadteam.resources.Employee"--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="description" content="Electromechanics Shop">
    <meta name="author" content="WAD Team">

    <title>Delete Employee | Electromechanics Shop</title>
</head>

<body>
<c:import url="/jsp/include/header.jsp"/>
<h1>Delete Employee</h1>

Employee you are deleting:<br>
<br>
<table>
    <tr>
        <th>Username</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Role</th>
    </tr>
    <tr>
        <td>${employee.username}</td>
        <td>${employee.name}</td>
        <td>${employee.surname}</td>
        <td>${employee.role}</td>
    </tr>
</table><br>
Sure to delete?<br>

<br>
<form method="POST" action="">
    <input type ="submit" value = "Yes">
</form>
<a href="<c:url value="/management/employeeManagement"/>">
    <input type ="submit" value = "No"/>
</a>

<%@ include file="/html/include/footer.html"%>
</body>
</html>