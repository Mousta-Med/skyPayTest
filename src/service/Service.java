package service;

import entity.Booking;
import entity.Room;
import entity.User;
import enums.RoomType;

import java.util.ArrayList;
import java.util.Date;

public class Service {

    public ArrayList<Room> rooms = new ArrayList<>();
    public ArrayList<User> users = new ArrayList<>();
    public ArrayList<Booking> bookings = new ArrayList<>();

    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        Room existingRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber) {
                existingRoom = room;
                break;
            }
        }

        if (existingRoom != null) {
            existingRoom.roomType = roomType;
            existingRoom.pricePerNight = roomPricePerNight;
            System.out.println("Updated: " + existingRoom);
        } else {
            Room newRoom = new Room(roomNumber, roomType, roomPricePerNight);
            rooms.add(newRoom);
            System.out.println("Created: " + newRoom);
        }
    }

    public void setUser(int userId, int balance) {
        User existingUser = null;
        for (User user : users) {
            if (user.userId == userId) {
                existingUser = user;
                break;
            }
        }
        if (existingUser != null) {
            existingUser.balance = balance;
            System.out.println("Updated: " + existingUser);
        } else {
            User newUser = new User(userId, balance);
            users.add(newUser);
            System.out.println("Created: " + newUser);
        }
    }

    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        System.out.println("\n--- Trying to book Room " + roomNumber + " for User " + userId + " ---");
        User user = null;
        for (User u : users) {
            if (u.userId == userId) {
                user = u;
                break;
            }
        }
        if (user == null) {
            System.out.println("ERROR: User " + userId + " not found!");
            return;
        }

        Room room = null;
        for (Room r : rooms) {
            if (r.roomNumber == roomNumber) {
                room = r;
                break;
            }
        }
        if (room == null) {
            System.out.println("ERROR: Room " + roomNumber + " not found!");
            return;
        }

        if (checkIn.compareTo(checkOut) >= 0) {
            System.out.println("ERROR: Check-in date must be before check-out date!");
            return;
        }

        for (Booking booking : bookings) {
            if (booking.room.roomNumber == roomNumber) {
                if (!(checkOut.compareTo(booking.checkIn) <= 0 || checkIn.compareTo(booking.checkOut) >= 0)) {
                    System.out.println("ERROR: Room " + roomNumber + " is already booked for those dates!");
                    return;
                }
            }
        }

        long diffMs = checkOut.getTime() - checkIn.getTime();
        int nights = (int) (diffMs / (1000 * 60 * 60 * 24));
        int totalCost = nights * room.pricePerNight;

        if (user.balance < totalCost) {
            System.out.println("ERROR: Not enough money! Need $" + totalCost + ", but user has $" + user.balance);
            return;
        }

        user.balance -= totalCost;
        Booking newBooking = new Booking(user, room, checkIn, checkOut);
        bookings.add(newBooking);

        System.out.println("SUCCESS: " + newBooking);
        System.out.println("   User balance after booking: $" + user.balance);
    }

    public void printAll() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ALL DATA (Newest to Oldest)");
        System.out.println("=".repeat(50));

        System.out.println("\nROOMS:");
        if (rooms.isEmpty()) {
            System.out.println("No rooms created yet.");
        } else {
            for (int i = rooms.size() - 1; i >= 0; i--) {
                System.out.println("  " + rooms.get(i));
            }
        }

        System.out.println("\nBOOKINGS:");
        if (bookings.isEmpty()) {
            System.out.println("No bookings made yet.");
        } else {
            for (int i = bookings.size() - 1; i >= 0; i--) {
                Booking b = bookings.get(i);
                System.out.println("  " + b);
                System.out.println("    -> Room was: " + b.roomTypeWhenBooked + " at $" + b.pricePerNightWhenBooked + "/night");
            }
        }
    }

    public void printAllUsers() {
        System.out.println("\n" + "=".repeat(30));
        System.out.println("👥 ALL USERS (Newest to Oldest)");
        System.out.println("=".repeat(30));

        if (users.isEmpty()) {
            System.out.println("No users created yet.");
        } else {
            for (int i = users.size() - 1; i >= 0; i--) {
                System.out.println("  " + users.get(i));
            }
        }
    }
}