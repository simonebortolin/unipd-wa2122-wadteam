<%--
  Created by IntelliJ IDEA.
  User: simonebastasin
  Date: 12/04/2022
  Time: 05:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="order" type="it.unipd.dei.wa2122.wadteam.resources.OnlineOrder"--%>

<html>
<head>
    <title>Delete Order</title>
</head>

<body>
<c:import url="/jsp/include/header.jsp"/>
<h1>Delete order</h1>

Order you are deleting:<br><br>
<table>
    <tr>
        <th>ID</th>
        <th>ID_Customer</th>
        <th>Date</th>
        <th>Status</th>
    </tr>
    <tr>
        <td>${onlineOrder.idOrder}</td>
        <td>${onlineOrder.idCustomer}</td>
        <td>${onlineOrder.ooDateTime}</td>
        <td>${onlineOrder.status}</td>
    </tr>
</table><br>
with these products:
<table>
    <tr>
        <th>Alias</th>
        <th>Brand</th>
        <th>Name</th>
        <th>Quantity</th>
        <th>Sale_Price</th>
    </tr>
    <c:forEach var="product" items="${onlineOrder.products}">
        <tr>
            <td>${product.alias}</td>
            <td>${product.brand}</td>
            <td>${product.name}</td>
            <td>${product.quantity}</td>
            <td>${product.salePrice}</td>
        </tr>
    </c:forEach>
</table><br>
Sure to delete?<br><br>

<form method="POST" action="">
    <input type ="submit" value = "Yes">
</form>
<a href="<c:url value="/management/orderManagement"/>">
    <input type ="submit" value = "No"/>
</a>

<%@ include file="/html/include/footer.html"%>
</body>
</html>