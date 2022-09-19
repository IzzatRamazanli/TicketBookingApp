package booking.dao.implementations;

import booking.database.FileBase;
import booking.model.Airline;
import booking.model.Cities;
import booking.model.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightDAOTest {
    private Flight flight;
    private Flight flight1;
    private FlightDAO dao;

    @BeforeEach
    void setUp() {
        FileBase fb = new FileBase();
        fb.setUp();
        dao = new FlightDAO(fb);
        flight = fb.getFlights().get(0);
        flight1 = new Flight(75, Airline.AZAL, Cities.BAKU, Cities.ISTANBUL, LocalDate.now(), LocalTime.now());
    }

    @Test
    void testGetAll() {
        assertEquals(50, dao.getAll().size());
    }

    @Test
    void testGetById() {
        assertEquals(flight, dao.get(flight.id()));
    }

    @Test
    void testCreate() {
        dao.create(flight1);
        assertEquals(51, dao.getAll().size());
    }

    @Test
    void testDeletingByReference() {
        dao.create(flight1);
        dao.delete(flight1);
        assertEquals(50, dao.getAll().size());
    }

    @Test
    void testDeletingById() {
        dao.create(flight1);
        dao.delete(75);
        assertEquals(50, dao.getAll().size());
    }

}