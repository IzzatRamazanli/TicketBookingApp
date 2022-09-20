package booking.menu;

import booking.console.Console;
import booking.console.IOConsole;

public class MainMenu {
    private static final Console c = new IOConsole();

    public static void display() {
        String m = """

                       FLIGHT BOOKING APP     \s
                ================================
                >  1. Register.
                >  2. Login.
                >  3. View All Flights.
                >  4. Search flight.
                >  5. Exit.
                ================================
                """;

        c.print(m);
    }

}
