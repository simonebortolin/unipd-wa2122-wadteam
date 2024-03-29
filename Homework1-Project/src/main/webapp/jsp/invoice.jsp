<%--
  Created by IntelliJ IDEA.
  User: Gianpietro
  Date: 14/04/2022
  Time: 09:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="onlineInvoiceList" type="List<it.unipd.dei.wa2122.wadteam.resources.OnlineInvoice>"--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="description" content="Electromechanics Shop">
    <meta name="author" content="WAD Team">

    <title>Your Invoice List | Electromechanics Shop</title>
</head>

<body>
<c:import url="/jsp/include/header.jsp"/>
<h1>Your Invoice List</h1>

<c:forEach var="invoice" items="${onlineInvoiceList}">
    <h2>Invoice ID: ${invoice.id}</h2>
    <li>Date: ${invoice.oiDate.humanDateTimeless}</li>
    <li>Price: ${invoice.totalPrice}€</li>
    <li><a href="<c:url value="/invoice/detail/${invoice.id}"/>">Detail Invoice</a></li>
</c:forEach>

<%@ include file="/html/include/footer.html"%>
</body>
</html>