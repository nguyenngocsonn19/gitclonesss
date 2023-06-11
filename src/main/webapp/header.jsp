<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
  .header {
    background-color: #ffd700;
    padding: 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .header h1 {
    margin: 0;
    color: #333;
  }

  .header ul {
    list-style: none;
    display: flex;
    gap: 20px;
  }

  .header ul li {
    margin: 0;
  }

  .header ul li a {
    text-decoration: none;
    color: #333;
  }
</style>

<div class="header">
  <li><a href="HotelServlet">Trang Chủ</a></li>
  <ul>
    <li><a href="cart.jsp">LIST ROOM</a></li>
    <li><a href="user.jsp">User</a></li>
  </ul>
</div>
