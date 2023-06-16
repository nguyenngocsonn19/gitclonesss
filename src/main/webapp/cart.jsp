<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="js/cart.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            // Gọi hàm redirectToBooking() khi nhấn nút "Booking"
            document.querySelector('form[action="HotelServlet"] button[type="submit"]').addEventListener("click", redirectToBooking);

            // Gọi hàm deleteSelectedItems() khi nhấn nút "Delete"
            document.querySelector('form[action="HotelServlet"] button[type="submit"][name="command"][value="DELETE_CART"]').addEventListener("click", deleteSelectedItems);
        });

        function redirectToBooking() {
            var selectedRooms = document.querySelectorAll('input[name="selectedItems"]:checked');
            if (selectedRooms.length > 0) {
                var roomId = selectedRooms[0].getAttribute("data-roomId");
                var userId = selectedRooms[0].getAttribute("data-userId");
                var price = selectedRooms[0].getAttribute("data-price");
                var hotelId = selectedRooms[0].getAttribute("data-hotel");
                window.location.href = "booking.jsp?roomId=" + roomId + "&userId=" + userId + "&price=" + price + "&hotelId=" + hotelId;
            }
        }
    </script>
    <title>Cart</title>
    <style>
        /* Định dạng cho các cột <td> */
        td {
            text-align: center;

            background-color: white; /* Màu nền trắng */
            color: black; /* Chữ màu đen */
        }

        /* Định dạng cho các cột <th> */
        th {
            text-align: center;

            background-color: black; /* Màu nền đen */
            color: white; /* Chữ màu trắng */
        }
        table {
            margin: 0 auto;
            width: 100%;
            height: 15%;
            text-align: center;
            border: 1px solid black;
        }


        .delete-btn {
            background-color: red;
            color: white;
            padding: 10px 20px;
            border: none;
            margin-top: 20px;
            margin-left: 1200px;
            border-radius: 4px;
            cursor: pointer;
        }
        .booking-btn {
            background-color: green;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        table {
            margin: 0 auto; /* Căn giữa bảng */
            width: 100%; /* Chiều rộng 100% để bảng đầy đủ chiều ngang */
            text-align: center; /* Căn giữa nội dung trong bảng */
        }
    </style>
</head>
<body>

<h1>Cart</h1>
<form action="HotelServlet" method="POST">
    <table>
        <thead>
        <tr>
            <th>Select</th>
            <th>Cart ID</th>
            <th>Room ID</th>
            <th>Room Number</th>
            <th>Price</th>
            <th>Availability</th>
            <th>Hotel Name</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${CART_ITEMS}" var="cartItem">
            <tr>
                <td>
                    <input type="checkbox" name="selectedItems"
                           value="${cartItem.id}"
                           data-roomId="${cartItem.roomId}"
                           data-userId="${cartItem.userId}"
                           data-price="${cartItem.price}"
                           data-hotel="${cartItem.hotel_id}"
                    >
                </td>
                <td>${cartItem.id}</td>
                <td>${cartItem.roomId}</td>
                <td>${cartItem.roomNumber}</td>
                <td>${cartItem.price}</td>
                <td>${cartItem.availability}</td>
                <td>${cartItem.hotelName}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="hidden" name="command" value="DELETE_CART">
    <button type="submit" class="delete-btn">Delete</button>
    <button type="button" class="booking-btn" onclick="redirectToBooking()">Booking Now</button>
</form>
</body>
</html>
