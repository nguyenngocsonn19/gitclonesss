package hotel;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDB {
    @Resource(name="jdbc/hotel_booking_system")

    private DataSource dataSource;

    public HotelDB(DataSource theDataSource) {
        dataSource = theDataSource;
    }




    public List<Hotel> getHotel() throws Exception {
        List<Hotel> students = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try{
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);

            String sql = "select * from hotels";

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery(sql);

            while (myRs.next()){
                int id = myRs.getInt("hotel_id");
                String hotel_name = myRs.getString("hotel_name");
                String address = myRs.getString("address");
                String phone = myRs.getString("phone");
                String email = myRs.getString("email");

                Hotel hotel = new Hotel(id, hotel_name, address, phone,email);

                students.add(hotel);
            }
            return students;
        }
        finally {
            close(myConn, myStmt, myRs);
        }
    }

    public List<Hotel> searchHotelsByName(String hotelName) throws Exception {
        List<Hotel> hotelList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM hotels WHERE hotel_name LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + hotelName + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("hotel_id");
                String name = rs.getString("hotel_name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                Hotel hotel = new Hotel(id, name, address, phone, email);
                hotelList.add(hotel);
            }
        } finally {
            close(conn, stmt, rs);
        }

        return hotelList;
    }

    public List<Booking> getBooking(int bookingId) throws Exception {
        List<Booking> bookings = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Get a database connection
            connection = dataSource.getConnection();

            // Create SQL query to retrieve booking details
            String sql = "SELECT * FROM bookings WHERE booking_id = ?";
            statement = connection.prepareStatement(sql);

            // Set the parameter values
            statement.setInt(1, bookingId);

            // Execute the query
            resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("booking_id");
                String status = resultSet.getString("status");

                // Create a Booking object
                Booking booking = new Booking(id, status);

                // Add the booking to the list
                bookings.add(booking);
            }

        } finally {
            // Close the database resources
            close(connection, statement, resultSet);
        }

        return bookings;
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try{
            if (myRs != null){
                myRs.close();
            }

            if (myStmt != null){
                myStmt.close();
            }

            if (myConn != null){
                myConn.close();
            }
        }catch (Exception exc){
            exc.printStackTrace();
        }
    }

    public Hotel getHotelById(int hotelId) throws SQLException, ClassNotFoundException {
        Hotel hotel = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Load driver
            Class.forName("com.mysql.jdbc.Driver");

            // Thiết lập thông tin kết nối
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";

            // Kết nối tới cơ sở dữ liệu
            connection = DriverManager.getConnection(url, username, password);

            // Chuẩn bị câu truy vấn SQL
            String sql = "SELECT * FROM hotels WHERE hotel_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, hotelId);

            // Thực hiện truy vấn
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String hotelName = resultSet.getString("hotel_name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");

                hotel = new Hotel(hotelId, hotelName, address, phone, email);
            }
        } finally {
            // Đảm bảo đóng tất cả các tài nguyên (connection, statement, resultSet)
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return hotel;
    }

    public List<Cart> getCartItems(String userId) throws SQLException {
        List<Cart> cartItems = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();

            String sql = "SELECT c.id, c.user_id, c.room_id, r.room_number, r.price, r.availability, h.hotel_id "
                    + "FROM cart c "
                    + "JOIN users u ON c.user_id = u.user_id "
                    + "JOIN rooms r ON c.room_id = r.room_id "
                    + "JOIN hotel h ON r.hotel_id = h.hotel_id "
                    + "WHERE c.user_id = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int roomId = rs.getInt("room_id");
                int roomNumber = rs.getInt("room_number");
                double price = rs.getDouble("price");
                byte availability = rs.getByte("availability");
                int hotelId = rs.getInt("hotel_id");

                Room room = new Room(roomId, roomNumber, price, availability);
                Hotel hotel = new Hotel(hotelId); // Tạo đối tượng Hotel với hotelId

                Cart cartItem = new Cart(id, userId, roomId);
                cartItem.setRoom(room);
                cartItem.setHotel(hotel);

                cartItems.add(cartItem);
            }
        } finally {
            close(conn, stmt, rs);
        }

        return cartItems;
    }




    public void saveRoomToCart(String userId, int roomId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Get database connection
            // Load driver
            Class.forName("com.mysql.jdbc.Driver");

            // Thiết lập thông tin kết nối
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";

            connection = DriverManager.getConnection(url, username, password);

            // Create SQL statement
            String sql = "INSERT INTO cart (user_id, room_id) VALUES (?, ?)";

            // Prepare statement
            statement = connection.prepareStatement(sql);

            // Set parameters
            statement.setString(1, userId);
            statement.setInt(2, roomId);

            // Execute statement
            statement.executeUpdate();
        } finally {
            // Close database resources
            close(connection, statement, null);
        }
    }

    public RoomType getRoomTypeById(int typeID) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        RoomType roomType = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM room_types WHERE room_type_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, typeID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int roomTypeId = rs.getInt("room_type_id");
                String roomTypeName = rs.getString("room_type_name");
                String description = rs.getString("description");

                roomType = new RoomType(roomTypeId, roomTypeName, description);
            }
        } finally {
            close(conn, stmt, rs);
        }

        return roomType;
    }


    public List<Room> getRoomsByHotelId(int hotelId) throws Exception {
        List<Room> roomList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT r.*, rt.room_type_name, rt.description FROM rooms r " +
                    "INNER JOIN room_types rt ON r.room_type_id = rt.room_type_id " +
                    "WHERE r.hotel_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, hotelId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int roomId = rs.getInt("room_id");
                int roomTypeId = rs.getInt("room_type_id");
                int hotel_id = rs.getInt("hotel_id");
                int roomNumber = rs.getInt("room_number");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                byte availability = rs.getByte("availability");
                String roomTypeName = rs.getString("room_type_name");

                RoomType roomType = new RoomType(roomTypeId, roomTypeName, description);
                Room room = new Room(roomId, roomType.getRoom_type_id(), hotel_id, roomNumber, price, description, availability);
                roomList.add(room);
            }
        } finally {
            close(conn, stmt, rs);
        }

        return roomList;
    }


    public Hotel hotelDetails(int hotelId) throws Exception {
        Hotel hotel = null;
        List<Room> roomList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rsHotel = null;
        ResultSet rsRooms = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, username, password);

            // Lấy thông tin về khách sạn từ bảng hotels
            String hotelSql = "SELECT * FROM hotels WHERE hotel_id = ?";
            stmt = conn.prepareStatement(hotelSql);
            stmt.setInt(1, hotelId);
            rsHotel = stmt.executeQuery();

            if (rsHotel.next()) {
                int id = rsHotel.getInt("hotel_id");
                String name = rsHotel.getString("hotel_name");
                String address = rsHotel.getString("address");
                String phone = rsHotel.getString("phone");
                String email = rsHotel.getString("email");

                // Tạo đối tượng Hotel
                hotel = new Hotel(id, name, address, phone, email);
            }

            // Lấy danh sách phòng từ bảng rooms dựa trên hotel_id
            String roomSql = "SELECT * FROM rooms WHERE hotel_id = ?";
            stmt = conn.prepareStatement(roomSql);
            stmt.setInt(1, hotelId);
            rsRooms = stmt.executeQuery();

            while (rsRooms.next()) {
                int roomId = rsRooms.getInt("room_id");
                int roomTypeId = rsRooms.getInt("room_type_id");
                int hotel_id = rsRooms.getInt("hotel_id");
                int roomNumber = rsRooms.getInt("room_number");
                double price = rsRooms.getDouble("price");
                String description = rsRooms.getString("description");
                byte availability = rsRooms.getByte("availability");

                // Tạo đối tượng Room
                Room room = new Room(roomId, roomTypeId, hotel_id, roomNumber, price, description, availability);
                roomList.add(room);
            }

            // Gán danh sách phòng cho đối tượng Hotel
            if (hotel != null) {
                hotel.setRooms(roomList);
            }
        } finally {
            close(conn, stmt, rsHotel);
            close(null, null, rsRooms);
        }

        return hotel;
    }


    public void bookingRoomInHotel(Hotel hotel) throws Exception {

    }



    public void updateBooking(Booking theBooking) throws Exception { //update cho admin
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(url, username, password);

            String sql = "UPDATE bookings SET status = ? WHERE id=?";

            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, theBooking.getStatus());
            myStmt.setInt(2, theBooking.getId());
            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }
    }

    public void deleteBooking(String theStudentId) throws Exception{
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            int studentId = Integer.parseInt(theStudentId);

            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(url, username, password);

            String sql = "delete from bookings where id=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, studentId);

            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }
    }


}