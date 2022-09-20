package booking.commands;

import booking.console.IOConsole;
import booking.controller.BookingController;
import booking.controller.FlightController;
import booking.model.Booking;
import booking.model.Flight;
import booking.model.Passenger;
import booking.model.User;

import java.util.*;

public class MakeBooking {
    private final BookingController controllerB;
    private final ViewFlight flight;
    private final FlightController controllerF;

    private int bookingID = 1;


    public MakeBooking(BookingController controllerB, ViewFlight flight, FlightController controller) {
        this.controllerB = controllerB;
        this.flight = flight;
        this.controllerF = controller;
    }

    private static final IOConsole c = new IOConsole();

    public void makeBooking(User user) {
        Flight flight = getFlight(user);
        if (flight != null) {
            Booking booking = new Booking(bookingID++, user, flight, addPassengers(flight));
            if (controllerB.doReservation(booking)) {
                user.addBooking(booking);
                c.print("\nReservation completed, have a good journey");
            }
        } else {
            c.print("Flight to corresponded destination is not exist\n");
            makeBooking(user);
        }
    }

    private List<Passenger> addPassengers(Flight flight) {
        List<Passenger> list = new ArrayList<>();
        c.print("Enter passengers count for reserving seats: ");
        int seats = getNumber();
        if (seats > 0) {
            flight.setSeats(seats);
            for (int i = 1; i <= flight.getSeats(); i++) {
                Passenger p = passengerInfo(i);
                list.add(p);
            }
        }
        return list;
    }

    private Passenger passengerInfo(int i) {
        String fn = getName(i);
        String sn = getSurname(i);
        return new Passenger(i, fn, sn);
    }

    private String getName(int i) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name of passenger " + i + ": ");
        return sc.nextLine();
    }

    private String getSurname(int i) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter surname of passenger " + i + ": ");
        return sc.nextLine();

    }

    public Flight getFlight(User user) {
        List<Flight> flights = this.flight.searchFlight();
        flights.forEach(System.out::println);
        if (flights.size() > 0) {
            Flight f = selectFlight(flights, user);
            if (f == null) {
                return getFlight(user);
            } else {
                return f;
            }
        }
        return null;
    }

    public Flight selectFlight(List<Flight> flights, User user) {
        c.print("\nSelect flight from menu(Enter flight ID): ");
        int id = getNumber();
        Optional<Flight> find = flights.stream().filter(x -> x.id() == id).findFirst();
        if (id > 0 && find.isPresent()) {
            Optional<Booking> find2 = user.getBookings().stream().filter(x -> x.flight().id() == id).findAny();
            if (find2.isPresent()) {
                c.print("Reservation for this flight already exist!\n");
                return null;
            }
            return controllerF.getFlight(id);
        } else {
            c.print("Entered ID is not exist on menu, try again!\n");
            return null;
        }
    }

    private int getNumber() {
        int number = -1;
        boolean isNumber;
        do {
            if (c.getIn().hasNextInt()) {
                number = c.readInt();
                isNumber = true;
            } else {
                c.print("Not proper input, try again!\n");
                isNumber = false;
                c.getIn().next();
            }
        } while (!isNumber);

        return number;
    }


}
