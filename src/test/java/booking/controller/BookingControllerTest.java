package booking.controller;

import booking.dao.implementations.BookingDAO;
import booking.database.FileBase;
import booking.model.Booking;
import booking.model.Flight;
import booking.model.Passenger;
import booking.model.User;
import booking.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingControllerTest {
    private BookingController controller;
    private Booking booking;
    private User user;

    @BeforeEach
    void before() {
        FileBase fb = new FileBase();
        BookingDAO dao = new BookingDAO(fb);
        BookingService service = new BookingService(dao);
        controller = new BookingController(service);
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
        controller.doReservation(booking);
        assertEquals(1, controller.getAllBookings().size());
    }

    @Test
    void testGetBookingById() {
        controller.doReservation(booking);
        assertEquals(booking, controller.getBooking(3));
    }

    @Test
    void testDoReservation() {
        assertTrue(controller.doReservation(booking));
    }

    @Test
    void testGetBookingByUser() {
        controller.doReservation(booking);
        assertEquals(controller.getAllBookings(), user.getBookings());
    }

    @Test
    void testCancelBooking() {
        controller.doReservation(booking);
        assertTrue(controller.cancelBooking(booking, user));
    }


}