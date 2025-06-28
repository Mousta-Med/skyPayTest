package entity;

import enums.RoomType;

public class Room {

    public int roomNumber;
    public RoomType roomType;
    public int pricePerNight;

    public Room(int roomNumber, RoomType roomType, int pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }

    public String toString() {
        return "Room " + roomNumber + " (" + roomType + ", $" + pricePerNight + "/night)";
    }

}
