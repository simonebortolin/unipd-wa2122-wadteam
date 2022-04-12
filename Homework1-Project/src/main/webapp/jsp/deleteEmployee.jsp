<%--
  Created by IntelliJ IDEA.
  User: simonebastasin
  Date: 12/04/2022
  Time: 00:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Delete Employee</title>
</head>

<body>
<c:import url="/jsp/include/header.jsp"/>
<h1>Delete employee</h1>

Employee you are deleting:<br><br>
<table>
    <tr>
        <th>Username</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Role</th>
    </tr>
    <c:forEach var="employee" items="${employeeList}">
        <tr>
            <td>${employee.username}</td>
            <td>${employee.name}</td>
            <td>${employee.surname}</td>
            <td>${employee.role}</td>
        </tr>
    </c:forEach>
</table><br>
Sure to delete?<br><br>

<form method="POST" action="">
    <input type ="hidden" name = "employeeToDelete" value = ${employee.username}>
    <input type ="submit" value = "Yes">
</form>
<a href="<c:url value="/management/userManagement"/>">
    <input type ="submit" value = "No"/>
</a>

</body>
</html>