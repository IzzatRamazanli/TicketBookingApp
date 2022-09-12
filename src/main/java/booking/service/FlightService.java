package booking.service;

import booking.dao.implementations.FlightDAO;
import booking.model.Flight;

import java.util.List;

public class FlightService {
    private final FlightDAO dao;

    public FlightService(FlightDAO dao) {
        this.dao = dao;
    }

    public List<Flight> getAllFlights() {
        return dao.getAll();
    }

    public boolean createDestination(Flight flight) {
        return dao.create(flight);
    }

    public Flight getFlight(int id) {
        return dao.get(id);
    }

    public List<Flight> getAvailableFlights(String cityFrom, String cityTo) {
        return dao.getAll().stream()
                .filter(flight -> flight.cityFrom().name().equalsIgnoreCase(cityFrom)
                        && flight.cityTo().name().equalsIgnoreCase(cityTo))
                .toList();
    }

    public boolean deleteFlight(int id) {
        return dao.delete(id);
    }
}
