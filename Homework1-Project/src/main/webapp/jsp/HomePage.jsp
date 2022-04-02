<%--
  Created by IntelliJ IDEA.
  User: matte
  Date: 01/04/2022
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="productList" type="java.util.List<Product>"--%>
<%--@elvariable id="productCategoryList" type="java.util.List<ProductCategory>"--%>

<html>
<head>
    <title>Electromechanics Shop</title>
</head>

<body>

    <h1>Electromechanics shop</h1>
    <a href="<c:url value="/login"/>">Login</a>
    <a href="<c:url value="/viewmedia/list"/>">View Media</a>
    <hr />
    <ul>
    <c:forEach var="item" items="${productList}">
        <li>Product name ${item.name} - price: ${item.sale} - category: ${item.category.name}<br>
            <c:forEach var="picture" items="${item.pictures}">
                <img src="<c:url value="/viewmedia/${picture.id}"/>" alt="${picture.filename}" width="100px"/>
            </c:forEach>
        </li>
    </c:forEach>
    </ul>
    <ul>
        <c:forEach var="item" items="${productCategoryList}">
            <li>${item.name}</li>
        </c:forEach>
    </ul>

</body>
</html>