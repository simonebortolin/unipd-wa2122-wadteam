<%--
  Created by IntelliJ IDEA.
  User: gioel
  Date: 05/04/2022
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="productCategoryList" type="java.util.List<ProductCategory>"--%>
<%--@elvariable id="productList" type="java.util.List<Product>"--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/jsp/include/head.jsp"/>

    <title>Category: ${category.name} | Electromechanics Shop</title>
</head>

<body>
<c:import url="/jsp/include/header.jsp"/>
<div class="container main-container">

<c:forEach var="category" items="${productCategoryList}">

    <div class="d-flex align-items-baseline">
        <h1 class="title me-3"> ${category.name}</h1>
        <h3>${category.description}</h3>
    </div>



    <c:choose>
        <c:when test="${productList.size() > 0}">
            <c:forEach var="prod" items="${productList}">
                <c:choose>
                    <c:when test="${not empty prod.discount}">
                        <div class="mx-auto w-lg-75" >
                            <div class="card mt-3">
                                <div class="row g-0 justify-content-center">
                                    <div class="col-md-4 justify-content-center ratio ratio-1x1">
                                        <c:choose>
                                            <c:when test="${not empty prod.pictures}">

                                                <c:set var="i" value="${0}"/>
                                                <c:forEach var="pic" items="${prod.pictures}">
                                                    <c:if test="${i == 0}">
                                                        <c:set var="picture" value="${pic}"/>
                                                    </c:if>
                                                    <c:set var="i" value="${1}"/>
                                                </c:forEach>
                                                <img src="<c:url value="/media/view/${picture}"/>" alt="${prod.alias}" class="img-fluid w-100" >

                                            </c:when>
                                            <c:otherwise>

                                                <img src="<c:url value="/images/No_image_available_circle.png"/>" alt="${prod.alias}" class="img-fluid w-100" >

                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="col-md-8">
                                        <div class="card-body">
                                            <a class="stretched-link" href="<c:url value="/products/details/${prod.alias}"/>"></a>
                                            <h2 class="title">${prod.name}</h2>
                                            <span class="roboto text-primary">Brand: ${prod.brand}</span>
                                            <hr/>
                                            <div class="row card-text">
                                                <div class="col">
                                                    Price:
                                                    <span  class="text-decoration-line-through">${String.format("%.2f",prod.salePrice)}€</span>
                                                    <span class="text-red display-6">${String.format("%.2f",prod.discountSale)}€</span>
                                                </div>
                                            </div>
                                            <c:if test="${prod.quantity <= 10}">
                                                <div class="text-center mt-5 text-red bold">
                                                    Only ${prod.quantity} left in stock.
                                                </div>
                                            </c:if>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:when>
                    <c:otherwise>
                            <div class="mx-auto w-lg-75" >
                                <div class="card mt-3">
                                <div class="row g-0 justify-content-center">
                                    <div class="col-md-4 justify-content-center ratio ratio-1x1">
                                        <c:choose>
                                            <c:when test="${not empty prod.pictures}">

                                                <c:set var="i" value="${0}"/>
                                                <c:forEach var="pic" items="${prod.pictures}">
                                                    <c:if test="${i == 0}">
                                                        <c:set var="picture" value="${pic}"/>
                                                    </c:if>
                                                    <c:set var="i" value="${1}"/>
                                                </c:forEach>
                                                <img src="<c:url value="/media/view/${picture}"/>" alt="${prod.alias}" class="w-100 img-fluid" >

                                            </c:when>
                                            <c:otherwise>

                                                <img src="<c:url value="/images/No_image_available_circle.png"/>" alt="${prod.alias}" class="w-100 img-fluid" >

                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="col-md-8">
                                        <div class="card-body">
                                            <a class="stretched-link" href="<c:url value="/products/details/${prod.alias}"/>"></a>
                                            <h2 class="title">${prod.name}</h2>
                                            <p class="card-text">
                                                <span class="roboto text-primary"> Brand: ${prod.brand} </span>
                                                <hr/>
                                                Price:
                                                <span class="display-6">
                                                ${String.format("%.2f",prod.salePrice)}€
                                                </span>
                                                <c:if test="${prod.quantity <= 10}">
                                                    <div class="text-center text-red mt-5 bold">
                                                        Only ${prod.quantity} left in stock.
                                                    </div>
                                                </c:if>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                </div>
                            </div>
                            <br>



                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:when>
        <c:otherwise>
            There are no more products for this category! =(
        </c:otherwise>
    </c:choose>

</c:forEach>


</div>
<c:import url="/jsp/include/footer.jsp"/>
</body>
</html>
