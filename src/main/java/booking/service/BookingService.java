package booking.service;

import booking.dao.implementations.BookingDAO;
import booking.model.Booking;
import booking.model.Passenger;
import booking.model.User;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class BookingService {
    private final BookingDAO dao;

    public BookingService(BookingDAO dao) {
        this.dao = dao;
    }

    public List<Booking> getAllBookings() {
        return dao.getAll();
    }

    public boolean doReservation(Booking booking) {
        return dao.create(booking);
    }

    public Booking getBooking(int id) {
        return dao.get(id);
    }

    public List<Booking> getBookingsByUser(User user) {
        return dao.getAll().stream()
                .filter(booking -> booking.user().equals(user))
                .toList();
    }

    public boolean cancelBooking(Booking b, User user) {
        Optional<Booking> find = getBookingsByUser(user).stream().filter(x -> x.equals(b)).findFirst();
        if (find.isPresent()) {
            user.getBookings().remove(b);
        }
        return dao.delete(b);
    }

}
