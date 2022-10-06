package booking;

import booking.commands.*;
import booking.console.IOConsole;
import booking.controller.BookingController;
import booking.controller.FlightController;
import booking.controller.UserController;
import booking.dao.implementations.BookingDAO;
import booking.dao.implementations.FlightDAO;
import booking.dao.implementations.UserDAO;
import booking.database.FileBase;
import booking.menu.MainMenu;
import booking.menu.UserMenu;
import booking.model.Booking;
import booking.model.User;
import booking.service.BookingService;
import booking.service.FlightService;
import booking.service.UserService;

import java.util.List;

public class BookingApp {
    private final FileBase fb = new FileBase();
    private final BookingController controllerB = configB(fb);
    private final UserController controllerU = configU(fb);
    private final FlightController controllerF = configF(fb);
    private final IOConsole console = new IOConsole();
    private final Login login = new Login(controllerU);
    private final Registration register = new Registration(controllerU);
    private final ViewFlight flights = new ViewFlight(controllerF);
    private final MakeBooking booking = new MakeBooking(controllerB, flights, controllerF);
    private final CancelBooking cancelBooking = new CancelBooking(controllerB);

    {
        fb.setUp();
    }

    public boolean start() {
        MainMenu.display();
        console.print("\nEnter command to run -> ");
        String command = console.readLn();
        switch (command) {
            case "1" -> register.register();
            case "2" -> {
                User u = login.login();
                if (u != null) user(u);
            }
            case "3" -> flights.viewingAllFlights();
            case "4" -> flights.getFlight();
            case "5" -> {
                console.print("\nApplication closed...");
                fb.save();
                return false;
            }
            default -> console.print("\nEntered command doesn't exist");
        }
        return true;
    }

    private boolean userStart(User user) {
        UserMenu.display();
        console.print("\nEnter command to run -> ");
        String command = console.readLn();
        switch (command) {
            case "1" -> flights.viewingAllFlights();
            case "2" -> booking.makeBooking(user);
            case "3" -> {
                List<Booking> bookings = getAuthBookings(user);
                if (bookings.size() > 0) {
                    bookings.forEach(System.out::println);
                } else console.print("\nYou haven't bookings yet\n");
            }
            case "4" -> {
                List<Booking> bookings = getAuthBookings(user);
                if (bookings.size() > 0) {
                    cancelBooking.cancelBooking(user);
                } else console.print("\nNo any booking for cancelling\n");
            }
            case "5" -> {
                fb.save();
                System.out.println("\nLogged out...");
                return false;
            }
            default -> console.print("\nEntered command doesn't exist");
        }
        return true;
    }

    @SuppressWarnings("all")
    private void user(User user) {
        while (userStart(user));
    }

    private List<Booking> getAuthBookings(User user) {
        return controllerB.getAllBookings().stream()
                .filter(x -> x.user().userName().equals(user.userName())
                        && x.user().password().equals(user.password())).toList();
    }

    private UserController configU(FileBase fb) {
        UserDAO daoU = new UserDAO(fb);
        UserService serviceU = new UserService(daoU);
        return new UserController(serviceU);
    }

    private FlightController configF(FileBase fb) {
        FlightDAO daoF = new FlightDAO(fb);
        FlightService serviceF = new FlightService(daoF);
        return new FlightController(serviceF);
    }

    private BookingController configB(FileBase fb) {
        BookingDAO daoB = new BookingDAO(fb);
        BookingService serviceB = new BookingService(daoB);
        return new BookingController(serviceB);
    }


}
