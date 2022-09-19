package booking.commands;

import booking.console.IOConsole;
import booking.controller.BookingController;
import booking.model.User;


public class CancelBooking {
    private final BookingController controller;
    private final IOConsole c = new IOConsole();

    public CancelBooking(BookingController controller) {
        this.controller = controller;
    }

    public void cancelBooking(User user, MakeBooking booking) {
        c.print("Your bookings: \n");
        user.getBookings().forEach(System.out::println);
        c.print("\nEnter reservation ID to cancellation: ");
        int id = getId();
        if (id > 0 && user.getBookings().size() + 1 > id) {
            if (controller.cancelBooking(controller.getBooking(id), user)) {
                booking.setBookingID(1);
                c.print("\nReservation successfully canceled!\n");
            } else c.print("\nSomething went wrong\n");
        } else {
            c.print("Entered ID is not correct!\n");
            cancelBooking(user, booking);
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
