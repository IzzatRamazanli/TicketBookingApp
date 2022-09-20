package booking.controller;

import booking.model.Booking;
import booking.model.User;
import booking.service.BookingService;
import java.util.List;

public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    public List<Booking> getAllBookings() {
        return service.getAllBookings();
    }

    public boolean doReservation(Booking booking) {
        return service.doReservation(booking);
    }

    public Booking getBooking(int id) {
        return service.getBooking(id);
    }

    public List<Booking> getBookingsByUser(User user) {
        return service.getBookingsByUser(user);
    }

    public boolean cancelBooking(Booking b, User user) {
        return service.cancelBooking(b, user);
    }
}
