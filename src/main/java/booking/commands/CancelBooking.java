package booking.commands;

import booking.console.IOConsole;
import booking.controller.BookingController;
import booking.model.Booking;
import booking.model.User;

import java.util.List;
import java.util.Scanner;


public class CancelBooking {
    private final BookingController controller;
    private final IOConsole c = new IOConsole();

    public CancelBooking(BookingController controller) {
        this.controller = controller;
    }

    public void cancelBooking(User user) {
        c.print("Your bookings: \n");
        List<Booking> bookings = controller.getAllBookings().stream()
                .filter(x -> x.user().userName().equals(user.userName())
                        && x.user().password().equals(user.password())).toList();
        bookings.forEach(System.out::println);
        c.print("\nEnter reservation ID to cancellation: ");
        int id = getId();
        if (askConfirm()) {
            if (id > 0 && bookings.stream().anyMatch(x -> x.id() == id)) {
                if (controller.cancelBooking(controller.getBooking(id), user)) {
                    c.print("\nReservation successfully canceled!\n");
                } else c.print("\nSomething went wrong\n");
            } else {
                c.print("Entered ID is not correct!\n");
                cancelBooking(user);
            }
        } else {
            c.print("\nReservation cancelling operation denied.");
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

    private boolean askConfirm() {
        Scanner sc = new Scanner(System.in);
        c.print("\nAre you sure about deleting this reservation? 1.Yes 2.No: ");
        String query = sc.nextLine();
        return query.equals("1");
    }


}
