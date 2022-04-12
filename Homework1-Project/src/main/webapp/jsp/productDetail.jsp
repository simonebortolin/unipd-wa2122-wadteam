<%--
  Created by IntelliJ IDEA.
  User: gioel
  Date: 04/04/2022
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="product" type="Product"--%>

<html>
<head>
    <title>Electromechanics shop</title>
</head>

<body>

    <c:import url="/jsp/include/header.jsp"/>
    <h1>Product: ${product.name}</h1>

    <ul>
        <li>Brand: ${product.brand}</li>
        <li>Description: ${product.description}</li>
        <li>Quantity: ${product.quantity}</li>
        <li>Price: ${product.sale}€</li>
    </ul>

<hr />

    <form method="POST" action="<c:url value="/buy/product/${product.alias}"/>">
        <label for="quantity">Selected quantity</label>
        <input type="number" name ="quantity" max="${product.quantity}" min="1" id="quantity" required> <br>

        <input type ="submit" value = "Buy now">
    </form>
    <%@ include file="/html/include/footer.html"%>
</body>
</html>
