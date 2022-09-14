package booking.commands;

import booking.console.IOConsole;
import booking.controller.BookingController;
import booking.model.Passenger;
import booking.model.User;

import java.util.Iterator;

public class CancelBooking {
    private final BookingController controller;
    private final IOConsole c = new IOConsole();

    public CancelBooking(BookingController controller) {
        this.controller = controller;
    }

    public void cancelBooking(User user) {
        c.print("Your bookings: \n");
        user.getBookings().forEach(System.out::println);
        c.print("\nEnter reservation ID to cancellation: ");
        int id = getId();
        if (id > 0) {
            if (controller.cancelBooking(controller.getBooking(id), user)) {
                c.print("\nReservation successfully canceled!\n");
            } else c.print("\nSomething went wrong\n");
        }
    }

    private int getId() {
        int number = -1;
        boolean isNumber;
        do {
            if (c.getIn().hasNextInt()) {
                number = c.readInt();
                isNumber = true;
            } else {
                c.print("\nNot proper input\n");
                isNumber = false;
                c.getIn().next();
            }
        } while (!isNumber);

        return number;
    }


}
