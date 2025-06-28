package entity;

import enums.RoomType;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Booking {

    public User user;
    public Room room;
    public Date checkIn;
    public Date checkOut;
    public int totalPrice;
    public int nights;
    public RoomType roomTypeWhenBooked;
    public int pricePerNightWhenBooked;


    public Booking(User user, Room room, Date checkIn, Date checkOut) {

        this.user = user;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;

        long diffMs = checkOut.getTime() - checkIn.getTime();
        this.nights = (int) (diffMs / (1000 * 60 * 60 * 24));
        this.totalPrice = nights * room.pricePerNight;

        this.roomTypeWhenBooked = room.roomType;
        this.pricePerNightWhenBooked = room.pricePerNight;

    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Booking: User " + user.userId + " -> Room " + room.roomNumber +
                " (" + dateFormat.format(checkIn) + " to " + dateFormat.format(checkOut) +
                ", " + nights + " nights, $" + totalPrice + ")";
    }
}