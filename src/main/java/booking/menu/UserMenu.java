package booking.menu;

import booking.console.Console;
import booking.console.IOConsole;

public class UserMenu {
    private static final Console c = new IOConsole();

    public static void display() {
        StringBuilder m = new StringBuilder();
        m
                .append("\n       FLIGHT BOOKING APP      \n")
                .append("================================\n")
                .append(">  1. View All Flights.\n")
                .append(">  2. Make Booking.\n")
                .append(">  3. View Bookings.\n")
                .append(">  4. Cancel Booking.\n")
                .append(">  5. Log out.\n")
                .append("================================\n");

        c.print(m.toString());
    }
}
