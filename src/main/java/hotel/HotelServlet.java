package hotel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/HotelServlet")
@MultipartConfig
public class HotelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private HotelDB hotelDB;
    private List<Hotel> hotelList;

    private List<Booking> bookingList;
    @Resource(name = "jdbc/hotel_booking_system")

    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            hotelDB = new HotelDB(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String theCommand = request.getParameter("command");
            if (theCommand == null) {
                listHotels(request, response);
            } else {
                switch (theCommand) {
                    case "ADD":
                        addBooking(request, response);
                        break;
                    case "LOAD":
                        loadBooking(request, response);
                        break;
                    case "UPDATE":
                        updateBooking(request, response);
                        break;
                    case "DELETE":
                        deleteBooking(request, response);
                        break;
                    case "LOGIN":
                        login(request, response);
                        break;
                    case "DETAILS":
                        loadHotelDetails(request, response);
                        break;
                    case "SEARCH":
                        searchHotels(request, response);
                        break;
                    case "BOOK":
                        bookRoom(request, response);
                        break;
                    case "ADD_TO_CART":
                        addToCart(request, response);
                        break;
                    case "CART_DETAILS":
                        loadCartDetails(request, response);
                        break;
                    default:
                        listHotels(request, response);
                }
            }
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    private void bookRoom(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int hotelId = Integer.parseInt(request.getParameter("hotelId"));
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        // For simplicity, let's assume the booking is successful
        boolean bookingSuccess = true;
        if (bookingSuccess) {
            // Booking successful, redirect to a success page
            response.sendRedirect(request.getContextPath() + "/booking.jsp");
        }else{
        // Booking failed, redirect to an error page
        response.sendRedirect(request.getContextPath() + "/booking-error.jsp");
    }
    }


    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");

//            if (userId != null) {
                int roomId = Integer.parseInt(request.getParameter("roomId"));
                hotelDB.saveRoomToCart(userId, roomId);

            response.sendRedirect(request.getContextPath() + "/cart.jsp");
//            }
//            else {
//                // Redirect to login page or display an error message
//                response.sendRedirect(request.getContextPath() + "/login.jsp");
//            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }









    private void listHotels(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Hotel> hotels = hotelDB.getHotel();
        request.setAttribute("HOTEL_LIST", hotels);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage.jsp");
        dispatcher.forward(request, response);
    }
    private void loadHotelDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int hotelId = Integer.parseInt(request.getParameter("hotel_id"));
        Hotel hotel = hotelDB.getHotelById(hotelId);
        List<Room> rooms = hotelDB.getRoomsByHotelId(hotelId);

        for (Room room : rooms) {
            int roomTypeId = room.getRoom_type_id();
            RoomType roomType = (RoomType) hotelDB.getRoomTypeById(roomTypeId);
            room.setRoomType(roomType);
        }
        request.setAttribute("HOTEL", hotel);
        request.setAttribute("ROOMS", rooms);

        // Forward to the hotel-details.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/hotel-details.jsp");
        dispatcher.forward(request, response);
    }

    private void loadCartDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");

            List<Cart> cartItems = hotelDB.getCartItems(userId);

            request.setAttribute("cartItems", cartItems);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
























    private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO: Implement login functionality
    }

    private void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String bookingId = request.getParameter("bookingId");
        hotelDB.deleteBooking(bookingId);
        response.sendRedirect(request.getContextPath() + "/HotelServlet");
    }

    public void updateBooking(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        String status = request.getParameter("status");
        Booking booking = new Booking(bookingId, status);
        hotelDB.updateBooking(booking);
        response.sendRedirect(request.getContextPath() + "/HotelServlet");
    }

    public void loadBooking(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        Booking booking = (Booking) hotelDB.getBooking(bookingId);
        request.setAttribute("BOOKING", booking);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/load-booking.jsp");
        dispatcher.forward(request, response);
    }

    private void addBooking(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = bookingList.size() + 1;
        String hotelName = request.getParameter("hotelName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        Hotel hotel = new Hotel(id, hotelName, address, phone, email);
        hotelDB.bookingRoomInHotel(hotel);
        response.sendRedirect(request.getContextPath() + "/HotelServlet");
    }

    private void searchHotels(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String searchQuery = request.getParameter("search");
        List<Hotel> hotels = hotelDB.searchHotelsByName(searchQuery);
        request.setAttribute("HOTEL_LIST", hotels);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage.jsp");
        dispatcher.forward(request, response);
    }

}

