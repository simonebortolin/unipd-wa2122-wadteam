<%--
  Created by IntelliJ IDEA.
  User: GK10
  Date: 03/04/2022
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="it.unipd.dei.wa2122.wadteam.resources.TicketStatusEnum" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="description" content="Electromechanics Shop">
    <meta name="author" content="WAD Team">

    <title>Respond to Ticket | Electromechanics Shop</title>
</head>

<body>
<c:import url="/jsp/include/header.jsp"/>
<h1>Respond to Ticket</h1>

<form method="POST" action="">
    <select name="status" id="status">
        <option value ="${TicketStatusEnum.OPEN}">Open</option>
        <option value ="${TicketStatusEnum.PROCESSING}">Processing</option>
        <option value ="${TicketStatusEnum.CLOSED}">Closed</option>
        <option value ="${TicketStatusEnum.RETURN}">Return</option>
    </select>
    <br>
    <textarea name ="description" id="description"> </textarea>
    <label for = "description"><b> Description </b></label>
    <br>
    <br>
    <input type ="submit" value = "Respond">
</form>

<%@ include file="/html/include/footer.html"%>
</body>
</html>
