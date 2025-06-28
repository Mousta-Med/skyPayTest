import enums.RoomType;
import service.Service;

import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("SIMPLE HOTEL RESERVATION SYSTEM");
        System.out.println("=" .repeat(40));

        Service hotel = new Service();

        System.out.println("\nStep 1: Creating rooms...");
        hotel.setRoom(1, RoomType.STANDARD, 1000);
        hotel.setRoom(2, RoomType.JUNIOR_SUITE, 2000);
        hotel.setRoom(3, RoomType.MASTER_SUITE, 3000);

        System.out.println("\nStep 2: Creating users...");
        hotel.setUser(1, 5000);   // User 1 with $5000
        hotel.setUser(2, 10000);  // User 2 with $10000

        System.out.println("\nStep 3: Making bookings...");
        Calendar cal = Calendar.getInstance();

        cal.set(2026, Calendar.JUNE, 30);
        Date checkIn1 = cal.getTime();
        cal.set(2026, Calendar.JULY, 7);
        Date checkOut1 = cal.getTime();
        hotel.bookRoom(1, 2, checkIn1, checkOut1);

        cal.set(2026, Calendar.JULY, 7);
        Date badCheckIn = cal.getTime();
        cal.set(2026, Calendar.JUNE, 30);
        Date badCheckOut = cal.getTime();
        hotel.bookRoom(1, 2, badCheckIn, badCheckOut);

        cal.set(2026, Calendar.JULY, 7);
        Date checkIn3 = cal.getTime();
        cal.set(2026, Calendar.JULY, 8);
        Date checkOut3 = cal.getTime();
        hotel.bookRoom(1, 1, checkIn3, checkOut3);

        cal.set(2026, Calendar.JULY, 7);
        Date checkIn4 = cal.getTime();
        cal.set(2026, Calendar.JULY, 9);
        Date checkOut4 = cal.getTime();
        hotel.bookRoom(2, 1, checkIn4, checkOut4);

        cal.set(2026, Calendar.JULY, 7);
        Date checkIn5 = cal.getTime();
        cal.set(2026, Calendar.JULY, 8);
        Date checkOut5 = cal.getTime();
        hotel.bookRoom(2, 3, checkIn5, checkOut5);

        System.out.println("\nStep 4: Updating Room 1...");
        hotel.setRoom(1, RoomType.MASTER_SUITE, 10000);

        System.out.println("\nStep 5: Final Results...");
        hotel.printAll();
        hotel.printAllUsers();

        System.out.println("\nTest completed!");
    }
}