package booking.menu;

import booking.console.Console;
import booking.console.IOConsole;

public class MainMenu {
    private static final Console c = new IOConsole();

    public static void display() {
        StringBuilder m = new StringBuilder();
        m
                .append("\n       FLIGHT BOOKING APP      \n")
                .append("================================\n")
                .append(">  1. Register.\n")
                .append(">  2. Login.\n")
                .append(">  3. View All Flights.\n")
                .append(">  4. Search flight.\n")
                .append(">  5. Exit.\n")
                .append("================================\n");

        c.print(m.toString());
    }

}
