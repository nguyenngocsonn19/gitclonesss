package hotel;

import hotel.Room;

public class Cart {
    private int id;
    private String userId;
    private int roomId;
    private Room room;
    private Hotel hotel;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Cart(int id, String userId, int roomId) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }



}
