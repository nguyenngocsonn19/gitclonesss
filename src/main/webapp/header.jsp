<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
  <meta charset="utf-8">
  <title>Hotelier - Hotel HTML Template</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicon -->


  <!-- Google Web Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">

  <!-- Icon Font Stylesheet -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

  <!-- Libraries Stylesheet -->
  <link href="lib/animate/animate.min.css" rel="stylesheet">
  <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

  <!-- Customized Bootstrap Stylesheet -->
  <link href="css/bootstrap.min.css" rel="stylesheet">

  <!-- Template Stylesheet -->
  <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid bg-dark px-0">
  <div class="row gx-0">
    <div class="col-lg-3 bg-dark d-none d-lg-block">
      <a href="index.html" class="navbar-brand w-100 h-100 m-0 p-0 d-flex align-items-center justify-content-center">
        <h1 class="m-0 text-primary text-uppercase">Hotelier</h1>
      </a>
    </div>
    <div class="col-lg-9">
      <div class="row gx-0 bg-white d-none d-lg-flex">
        <div class="col-lg-7 px-5 text-start">
          <div class="h-100 d-inline-flex align-items-center py-2 me-4">
            <i class="fa fa-envelope text-primary me-2"></i>
            <p class="mb-0">info@example.com</p>
          </div>
          <div class="h-100 d-inline-flex align-items-center py-2">
            <i class="fa fa-phone-alt text-primary me-2"></i>
            <p class="mb-0">+012 345 6789</p>
          </div>
        </div>
        <div class="col-lg-5 px-5 text-end">
          <div class="d-inline-flex align-items-center py-2">
            <a class="me-3" href=""><i class="fab fa-facebook-f"></i></a>
            <a class="me-3" href=""><i class="fab fa-twitter"></i></a>
            <a class="me-3" href=""><i class="fab fa-linkedin-in"></i></a>
            <a class="me-3" href=""><i class="fab fa-instagram"></i></a>
            <a class="" href=""><i class="fab fa-youtube"></i></a>
          </div>
        </div>
      </div>
      <nav class="navbar navbar-expand-lg bg-dark navbar-dark p-3 p-lg-0">
        <a href="index.html" class="navbar-brand d-block d-lg-none">
          <h1 class="m-0 text-primary text-uppercase">Hotelier</h1>
        </a>
        <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
          <div class="navbar-nav mr-auto py-0">
            <a href="/HotelServlet" class="nav-item nav-link active">Home</a>
            <a href="HotelServlet?command=CART_DETAILS&userId=${sessionScope.userId}" class="nav-item nav-link">Cart</a>
            <a href="HotelServlet?command=LOAD&userId=${sessionScope.userId}"  class="nav-item nav-link">Booking</a>
            <a href="room.html" class="nav-item nav-link">Rooms</a>
            <div class="nav-item dropdown">
              <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
              <div class="dropdown-menu rounded-0 m-0">
                <a href="booking.jsp" class="dropdown-item">Booking</a>
                <a href="team.html" class="dropdown-item">Our Team</a>
                <a href="testimonial.html" class="dropdown-item">Testimonial</a>
              </div>
            </div>
            <a href="contact.html" class="nav-item nav-link">Contact</a>
          </div>

        </div>
      </nav>
    </div>
  </div>
</div>

</body>
</html>
