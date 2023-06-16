
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>

  <title>Login Page</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="css/login.css">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/style.css">
  <script nonce="0ec905e9-2ad7-43c6-bdbf-a324dce54cc6">(function(w,d){!function(dK,dL,dM,dN){dK[dM]=dK[dM]||{};dK[dM].executed=[];dK.zaraz={deferred:[],listeners:[]};dK.zaraz.q=[];dK.zaraz._f=function(dO){return function(){var dP=Array.prototype.slice.call(arguments);dK.zaraz.q.push({m:dO,a:dP})}};for(const dQ of["track","set","debug"])dK.zaraz[dQ]=dK.zaraz._f(dQ);dK.zaraz.init=()=>{var dR=dL.getElementsByTagName(dN)[0],dS=dL.createElement(dN),dT=dL.getElementsByTagName("title")[0];dT&&(dK[dM].t=dL.getElementsByTagName("title")[0].text);dK[dM].x=Math.random();dK[dM].w=dK.screen.width;dK[dM].h=dK.screen.height;dK[dM].j=dK.innerHeight;dK[dM].e=dK.innerWidth;dK[dM].l=dK.location.href;dK[dM].r=dL.referrer;dK[dM].k=dK.screen.colorDepth;dK[dM].n=dL.characterSet;dK[dM].o=(new Date).getTimezoneOffset();if(dK.dataLayer)for(const dX of Object.entries(Object.entries(dataLayer).reduce(((dY,dZ)=>({...dY[1],...dZ[1]})),{})))zaraz.set(dX[0],dX[1],{scope:"page"});dK[dM].q=[];for(;dK.zaraz.q.length;){const d_=dK.zaraz.q.shift();dK[dM].q.push(d_)}dS.defer=!0;for(const ea of[localStorage,sessionStorage])Object.keys(ea||{}).filter((ec=>ec.startsWith("_zaraz_"))).forEach((eb=>{try{dK[dM]["z_"+eb.slice(7)]=JSON.parse(ea.getItem(eb))}catch{dK[dM]["z_"+eb.slice(7)]=ea.getItem(eb)}}));dS.referrerPolicy="origin";dS.src="/cdn-cgi/zaraz/s.js?z="+btoa(encodeURIComponent(JSON.stringify(dK[dM])));dR.parentNode.insertBefore(dS,dR)};["complete","interactive"].includes(dL.readyState)?zaraz.init():dK.addEventListener("DOMContentLoaded",zaraz.init)}(w,d,"zarazData","script");})(window,document);</script></head>

    <style>
        th{
            background-color: black;
            color: white;
            text-align: center;
        }
        td{
          text-align: center;

          background-color: white;
            color: black;
        }

        tbody {
          padding: 10px;
          line-height: 1.5;
          width: 122%;
          display: table;
          border: 1px solid black;
          text-align: center;
          margin-left: -50px;

        }
    </style>
</head>
<body>
<div class="container-xxl py-5">
  <div class="container">
    <div class="row g-5 align-items-center">
      <div class="col-lg-8">
        <h6 class="section-title text-start text-primary text-uppercase">Hotel Details</h6>
        <h1 class="mb-4">Welcome to <span class="text-primary text-uppercase">Hotel Details</span></h1>
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
                <th>Image</th>
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
                    <td><img src="img/${room.img}" alt="Room Images" style="max-width: 100px">
                  <td>${room.roomType.room_type_name}</td>
                  <td>${room.room_number}</td>
                  <td>${room.price}</td>
                  <td>${room.description}</td>
                  <td>${room.availability}</td>
                  <td>
                    <a href="HotelServlet?command=ADD_TO_CART&roomId=${room.room_id}">Add to List</a>

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
        <div class="row g-3 pb-4" >
          <div class="col-sm-4 wow fadeIn" data-wow-delay="0.1s" style="visibility: visible; animation-delay: 0.1s; animation-name: fadeIn;">
            <div class="border rounded p-1">
              <div class="border rounded text-center p-4">
                <i class="fa fa-hotel fa-2x text-primary mb-2"></i>
                <h2 class="mb-1" data-toggle="counter-up">1234</h2>
                <p class="mb-0">Rooms</p>
              </div>
            </div>
          </div>
          <div class="col-sm-4 wow fadeIn" data-wow-delay="0.3s" style="visibility: visible; animation-delay: 0.3s; animation-name: fadeIn;">
            <div class="border rounded p-1">
              <div class="border rounded text-center p-4">
                <i class="fa fa-users-cog fa-2x text-primary mb-2"></i>
                <h2 class="mb-1" data-toggle="counter-up">1234</h2>
                <p class="mb-0">Staffs</p>
              </div>
            </div>
          </div>
          <div class="col-sm-4 wow fadeIn" data-wow-delay="0.5s" style="visibility: visible; animation-delay: 0.5s; animation-name: fadeIn;">
            <div class="border rounded p-1">
              <div class="border rounded text-center p-4">
                <i class="fa fa-users fa-2x text-primary mb-2"></i>
                <h2 class="mb-1" data-toggle="counter-up">1234</h2>
                <p class="mb-0">Clients</p>
              </div>
            </div>
          </div>
        </div>
        <a class="btn btn-primary py-3 px-5 mt-2" href="">Explore More</a>
      </div>
      <div class="col-lg-4" style="-webkit-box-flex: 0;
            -ms-flex: 0 0 33.33333%;
            flex: 0 0 33.33333%;
            max-width: 33.33333%;
            left: 95px;">
        <div class="row g-3">
          <div class="col-6 text-end">
            <img class="img-fluid rounded w-75 wow zoomIn" data-wow-delay="0.1s" src="img/about-1.jpg" style="margin-top: 25%; visibility: visible; animation-delay: 0.1s; animation-name: zoomIn;">
          </div>
          <div class="col-6 text-start">
            <img class="img-fluid rounded w-100 wow zoomIn" data-wow-delay="0.3s" src="img/about-2.jpg" style="visibility: visible; animation-delay: 0.3s; animation-name: zoomIn;">
          </div>
          <div class="col-6 text-end">
            <img class="img-fluid rounded w-50 wow zoomIn" data-wow-delay="0.5s" src="img/about-3.jpg" style="visibility: visible; animation-delay: 0.5s; animation-name: zoomIn;">
          </div>
          <div class="col-6 text-start">
            <img class="img-fluid rounded w-75 wow zoomIn" data-wow-delay="0.7s" src="img/about-4.jpg" style="visibility: visible; animation-delay: 0.7s; animation-name: zoomIn;">
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<hr>

<%--<h2>Booking Details</h2>--%>
<%--<c:forEach var="booking" items="${BOOKINGS}">--%>
<%--    <p>--%>
<%--        Booking ID: ${booking.id}<br>--%>
<%--        Status: ${booking.status}--%>
<%--    </p>--%>
<%--</c:forEach>--%>
</body>
</html>
