package booking.controller;

import booking.model.Flight;
import booking.service.FlightService;

import java.util.List;

public class FlightController {
    private final FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    public List<Flight> getAllFlights() {
        return service.getAllFlights();
    }

    public boolean createDestination(Flight flight) {
        return service.createDestination(flight);
    }

    public Flight getFlight(int id) {
        return service.getFlight(id);
    }

    public List<Flight> getAvailableFlights(String cityFrom, String cityTo) {
        return service.getAvailableFlights(cityFrom, cityTo);
    }

    public boolean deleteFlight(int id) {
        return service.deleteFlight(id);
    }
}
