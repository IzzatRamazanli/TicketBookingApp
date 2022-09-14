package booking.commands;

import booking.console.Console;
import booking.console.IOConsole;
import booking.controller.FlightController;
import booking.model.Flight;

import java.util.List;

public class ViewFlight {
    private final FlightController controller;
    private static final Console c = new IOConsole();

    public ViewFlight(FlightController controller) {
        this.controller = controller;
    }

    public void viewingAllFlights() {
        c.print("\nTIME TABLE FOR AVAILABLE FLIGHTS\n");
        controller.getAllFlights().forEach(System.out::println);
    }

    public List<Flight> searchFlight() {
        c.print("Where are you flying from: ");
        String cityFrom = c.readLn();
        c.print("Where do you want to flight: ");
        String cityTo = c.readLn();
        c.print("Available flights for entered data: \n");
        return controller.getAvailableFlights(cityFrom, cityTo);
    }

    public void getFlight() {
        List<Flight> flights = searchFlight();
        if (flights.size() == 0) {
            c.print("Flight to corresponded destination is not exist\n");
        } else {
            flights.forEach(System.out::println);
        }
    }
}
