<%--
  Created by IntelliJ IDEA.
  User: Gianpietro
  Date: 18/04/2022
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="onlineInvoice" type="List<it.unipd.dei.wa2122.wadteam.resources.OnlineInvoice>"--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/jsp/include/head.jsp"/>

    <title>Invoice Detail ${onlineInvoice.id} | Electromechanics Shop</title>
</head>

<body>
<c:import url="/jsp/include/header.jsp"/>
<div class="container main-container">
    <nav class="custom-breadcrumb-divider" aria-label="breadcrumb">
        <ol class="breadcrumb bg-secondary bg-opacity-25 p-3 mt-3 rounded">
            <li class="breadcrumb-item"><a href="<c:url value="/"/>">Electromechanics Shop</a></li>
            <li class="breadcrumb-item"><a href="<c:url value="/invoice/list"/>">Invoice List</a></li>
            <li class="breadcrumb-item active" aria-current="page">Invoice Detail ${onlineInvoice.id}</li>
        </ol>
    </nav>

    <div id="invoice" class="w-lg-50 mx-auto">
        <h1 class="d-none d-print-block">Electromechanics Shop</h1>

        <div class="card bg-white p-3 rounded mt-3 mb-3">
            <h3>ID INVOICE # ${onlineInvoice.id}</h3>

            <div class="row">
                <div class="col">Transaction ID:</div>
                <div class="col">${onlineInvoice.transactionId}</div>
            </div>
            <div class="row">
                <div class="col">Payment Type:</div>
                <div class="col">${onlineInvoice.paymentType.text}</div>
            </div>
            <div class="row">
                <div class="col">Date:</div>
                <div class="col">${onlineInvoice.oiDate.humanDate}</div>
            </div>
            <div class="row">
                <div class="col">Total Price:</div>
                <div class="col">${String.format("%.2f", onlineInvoice.totalPrice)}€</div>
            </div>


            <h5 class="mt-3">ID ORDER # ${onlineInvoice.idOrder.idOrder}</h5>

            <div class="card row m-3">
                <div class="card-header">
                    <div class="row">
                        <div class="col">Product name</div>
                        <div class="col">Price</div>
                        <div class="col">Quantity</div>
                        <div class="col">Total price</div>
                    </div>
                </div>
                <ul class="list-group list-group-flush">
                    <c:forEach var="prod" items="${onlineInvoice.idOrder.products}">
                        <li class="list-group-item row px-0">
                            <div class="col text-break">
                                    ${prod.name}
                            </div>
                            <div class="col text-break">
                                    ${String.format("%.2f", prod.salePrice)}€
                            </div>
                            <div class="col text-break">
                                    ${prod.quantity}
                            </div>
                            <div class="col text-break">
                                    ${String.format("%.2f", prod.quantity * prod.salePrice)}€
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="mt-3 mb-3 d-flex justify-content-end">
            <button class="btn btn-primary d-print-none me-3" id="printInvoice"><i class="fa-solid fa-print me-3"></i><span>Print Invoice</span></button>
        </div>
    </div>
</div>
<c:import url="/jsp/include/footer.jsp"/>
</body>
</html>
