<%--
  Created by IntelliJ IDEA.
  User: matteolando
  Date: 14/04/2022
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="description" content="Electromechanics Shop">
    <meta name="author" content="WAD Team">

    <title>Delete Discount | Electromechanics Shop</title>
</head>
<body>
<c:import url="/jsp/include/header.jsp"/>
<h1>Delete Discount</h1>

Discount you are deleting:<br><br>
<table>
    <tr>
        <th>Id</th>
        <th>Percentage</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Product List</th>
    </tr>
    <tr>
        <td>${discountListProduct.discount.id}</td>
        <td>${discountListProduct.discount.percentage}</td>
        <td>${discountListProduct.discount.startDate.getHumanDateTimeless()}</td>
        <td>${discountListProduct.discount.endDate.getHumanDateTimeless()}</td>

        <td>
            <c:forEach var="prod" items="${discountListProduct.productList}">
                ${prod.name} <br>
            </c:forEach>
        </td>
    </tr>
</table><br>
Sure to delete?<br><br>

<form method="POST" action="">
    <input type ="submit" value = "Yes">
</form>
<a href="<c:url value="/management/discountManagement"/>">
    <input type ="submit" value = "No"/>
</a>

<%@ include file="/html/include/footer.html"%>
</body>
</html>




