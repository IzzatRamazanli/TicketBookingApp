package booking.dao.implementations;

import booking.dao.DAO;
import booking.dao.Searcher;
import booking.database.FileBase;
import booking.model.Flight;

import java.util.List;
import java.util.Optional;

public class FlightDAO implements DAO<Flight>, Searcher<Flight> {

    private final FileBase fb ;

    public FlightDAO(FileBase fb) {
        this.fb = fb;
    }

    @Override
    public List<Flight> getAll() {
        return fb.getFlights();
    }

    @Override
    public Flight get(int id) {
        return findById(id).orElse(null);
    }

    @Override
    public boolean create(Flight flight) {
        if (flight == null || findByReference(flight).isPresent()) return false;
        else fb.getFlights().add(flight);
        return true;
    }

    @Override
    public boolean delete(Flight flight) {
        if (findByReference(flight).isPresent()) {
            fb.getFlights().remove(flight);
            return true;
        } else return false;
    }

    @Override
    public boolean delete(int id) {
        if (findById(id).isEmpty()) return false;
        return fb.getFlights().removeIf(x -> id == x.getId());
    }

    @Override
    public Optional<Flight> findById(int id) {
        return fb.getFlights().stream()
                .filter(flight -> id == flight.getId())
                .findFirst();
    }

    @Override
    public Optional<Flight> findByReference(Flight flight) {
        return fb.getFlights().stream().filter(flight::equals).findFirst();
    }
}
