package booking.service;

import booking.dao.implementations.BookingDAO;
import booking.database.FileBase;
import booking.model.Booking;
import booking.model.Flight;
import booking.model.Passenger;
import booking.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {
    private BookingService service;
    private User user;
    private Booking booking;

    @BeforeEach
    void before() {
        FileBase fb = new FileBase();
        BookingDAO dao = new BookingDAO(fb);
        service = new BookingService(dao);
        fb.setUp();
        user = fb.getUsers().get(0);
        Flight flight = fb.getFlights().get(0);
        List<Passenger> passengers =
                new ArrayList<>(List.of(new Passenger(1, "Izzat", "Ramazanli")));
        booking = new Booking(3, user, flight, passengers);
        user.addBooking(booking);
    }

    @Test
    void testGetAllBookings() {
        service.doReservation(booking);
        assertEquals(1, service.getAllBookings().size());
    }

    @Test
    void testDoReservation() {
        assertTrue(service.doReservation(booking));
    }

    @Test
    void testGetBookingById() {
        service.doReservation(booking);
        assertEquals(booking, service.getBooking(3));
    }

    @Test
    void testGetBookingByUser() {
        service.doReservation(booking);
        assertEquals(service.getBookingsByUser(user), user.getBookings());
    }

    @Test
    void testCancelBooking() {
        service.doReservation(booking);
        service.cancelBooking(booking, user);
        assertEquals(0, service.getAllBookings().size());
    }

}