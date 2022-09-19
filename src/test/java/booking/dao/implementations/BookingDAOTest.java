package booking.dao.implementations;

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

class BookingDAOTest {
    private BookingDAO dao;
    private User user;
    private Flight flight;
    private List<Passenger> passengers;

    @BeforeEach
    void setUp() {
        FileBase fb = new FileBase();
        fb.setUp();
        dao = new BookingDAO(fb);
        passengers = new ArrayList<>(List.of(new Passenger(1, "Izzat", "Ramazanli")));
        user = fb.getUsers().get(0);
        flight = fb.getFlights().get(0);
    }

    @Test
    void testGetAll() {
        Booking booking = new Booking(1, user, flight, passengers);
        dao.create(booking);
        assertEquals(1, dao.getAll().size());
    }

    @Test
    void testGetById() {
        Booking booking = new Booking(1, user, flight, passengers);
        dao.create(booking);
        assertEquals(booking, dao.get(1));
    }

    @Test
    void createTest() {
        Booking booking = new Booking(1, user, flight, passengers);
        dao.create(booking);
        assertEquals(1, dao.getAll().size());
    }

    @Test
    void testDeletingByReference() {
        Booking booking = new Booking(1, user, flight, passengers);
        dao.create(booking);
        dao.delete(booking);
        assertEquals(0, dao.getAll().size());
    }

    @Test
    void testDeletingById() {
        Booking booking = new Booking(1, user, flight, passengers);
        dao.create(booking);
        dao.delete(1);
        assertEquals(0, dao.getAll().size());
    }

}