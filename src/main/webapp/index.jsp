<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="HotelServlet">Student Servlet</a>


<h1>Hotel Details</h1>

<c:if test="${not empty HOTEL}">

    <p>
        Hotel ID: ${HOTEL.hotel_id}<br>
        Hotel Name: ${HOTEL.hotel_name}<br>
        Address: ${HOTEL.address}<br>
        Phone: ${HOTEL.phone}<br>
        Email: ${HOTEL.email}
    </p>

    <h2>Room List</h2>
    <c:if test="${not empty ROOMS}">
        <table>
            <tr>
                <th>Room ID</th>
                <th>Room Type</th>
                <th>Room Number</th>
                <th>Price</th>
                <th>Description</th>
                <th>Availability</th>
                <th>Action</th>
            </tr>
            <c:forEach var="room" items="${ROOMS}">
                <tr>
                    <td>${room.room_id}</td>
                    <td>${room.roomType.room_type_name}</td>
                    <td>${room.room_number}</td>
                    <td>${room.price}</td>
                    <td>${room.description}</td>
                    <td>${room.availability}</td>
                    <td>
                        <a href="HotelServlet?command=ADD_TO_CART&roomId=${room.room_id}">Add to List</a> |
                        <a href="HotelServlet?command=BOOK&hotelId=${HOTEL.hotel_id}&roomId=${room.room_id}">Booking Now</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty ROOMS}">
        <p>No rooms available for this hotel.</p>
    </c:if>
</c:if>

<c:if test="${empty HOTEL}">
    <p>Hotel not found.</p>
</c:if>

<hr>

</body>
</html>