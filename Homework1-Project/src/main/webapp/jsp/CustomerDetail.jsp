<%--
  Created by IntelliJ IDEA.
  User: matte
  Date: 07/04/2022
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="customer" type="it.unipd.dei.wa2122.wadteam.resources.Customer"--%>
<%--@elvariable id="user" type="it.unipd.dei.wa2122.wadteam.resources.UserCredential"--%>
<html>
<head>
    <title>Customer ${customer.username}</title>
</head>
<body>
    <c:import url="/jsp/include/header.jsp"/>
    <ul>
        <li>Name: ${customer.name}</li>
        <li>Surname: ${customer.surname}</li>
        <li>Address: ${customer.address}</li>
        <li>Phone number: ${customer.phoneNumber}</li>
        <li>Email: ${customer.email}</li>
        <li>FiscalCode: ${customer.fiscalCode}</li>
    </ul>

    <a href="<c:url value="/user/CUSTOMER/modify"/>">Edit</a>
    <a href="<c:url value="/user/CUSTOMER/password"/>">change Password</a>
</body>
</html>