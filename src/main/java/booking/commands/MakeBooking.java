package booking.commands;

import booking.console.IOConsole;
import booking.controller.BookingController;
import booking.controller.FlightController;
import booking.model.Booking;
import booking.model.Flight;
import booking.model.Passenger;
import booking.model.User;

import java.util.List;
import java.util.Scanner;

public class MakeBooking {
    private final BookingController controllerB;
    private final ViewFlight flight;
    private final FlightController controllerF;

    public MakeBooking(BookingController controllerB, ViewFlight flight, FlightController controller) {
        this.controllerB = controllerB;
        this.flight = flight;
        this.controllerF = controller;
    }

    private static final IOConsole c = new IOConsole();


    public void makeBooking(User user) {
        int counter = 0;
        Flight flight = getFlight();
        if (flight != null) {
            addPassengers(flight);
            Booking booking = new Booking(++counter, user, flight, flight.getPassengers());
            controllerB.doReservation(booking);
        } else {
            c.print("Flight to corresponded destination is not exist\n");
        }
    }

    private void addPassengers(Flight flight) {
        c.print("Enter passengers count for reserving seats: ");
        int seats = getNumber();
        if (seats > 0) {
            flight.setSeats(seats);
            for (int i = 1; i <= flight.getSeats(); i++) {
                Passenger p = passengerInfo(flight, i);
                flight.addPassenger(p);
            }
        }
    }

    private Passenger passengerInfo(Flight flight, int i) {
        String fn = getName(i);
        String sn = getSurname(i);
        return new Passenger(i - 1, fn, sn);
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

    public Flight getFlight() {
        List<Flight> flights = this.flight.searchFlight();
        flights.forEach(System.out::println);
        if (flights.size() > 0) {
            c.print("\nSelect flight from menu(Enter flight ID): ");
            int id = getNumber();
            if (id > 0) {
                return controllerF.getFlight(id);
            }
        }
        return null;
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
