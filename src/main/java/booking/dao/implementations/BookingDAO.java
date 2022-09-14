package booking.dao.implementations;

import booking.dao.DAO;
import booking.dao.Searcher;
import booking.database.FileBase;
import booking.model.Booking;

import java.util.List;
import java.util.Optional;

public class BookingDAO implements DAO<Booking>, Searcher<Booking> {

    private final FileBase fb;

    public BookingDAO(FileBase fb) {
        this.fb = fb;
    }

    @Override
    public List<Booking> getAll() {
        return fb.getBookings();
    }

    @Override
    public Booking get(int id) {
        return findById(id).orElse(null);
    }

    @Override
    public boolean create(Booking booking) {
        if (booking == null || findByReference(booking).isPresent()) return false;
        else fb.getBookings().add(booking);
        return true;
    }

    @Override
    public boolean delete(Booking booking) {
        if (findByReference(booking).isPresent()) {
            fb.getBookings().remove(booking);
            return true;
        } else return false;
    }

    @Override
    public boolean delete(int id) {
        if (findById(id).isEmpty()) return false;
        return fb.getBookings().removeIf(x -> id == x.id());
    }

    @Override
    public Optional<Booking> findById(int id) {
        return fb.getBookings().stream()
                .filter(booking -> id == booking.id())
                .findFirst();
    }

    @Override
    public Optional<Booking> findByReference(Booking booking) {
        return fb.getBookings().stream().filter(booking::equals).findFirst();
    }
}
