<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cart Details</title>
</head>
<body>
<h1>Cart Details</h1>
<table>
    <thead>
    <tr>
        <th>Cart ID</th>
        <th>Room ID</th>
        <th>Room Number</th>
        <th>Price</th>
        <th>Availability</th>
        <th>Hotel Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cartItem" items="${cartItems}">
        <tr>
            <td>${cartItem.id}</td>
            <td>${cartItem.room_id}</td>
            <td>${cartItem.room.room_number}</td>
            <td>${cartItem.room.price}</td>
            <td>${cartItem.room.availability}</td>
            <td>${cartItem.hotel.hotel_name}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
