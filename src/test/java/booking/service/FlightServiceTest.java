package booking.service;

import booking.dao.implementations.FlightDAO;
import booking.database.FileBase;
import booking.model.Airline;
import booking.model.Cities;
import booking.model.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {
    private FlightService service;
    private Flight flight;
    private Flight flight1;

    @BeforeEach
    void before() {
        FileBase fb = new FileBase();
        FlightDAO dao = new FlightDAO(fb);
        service = new FlightService(dao);
        fb.setUp();
        flight = fb.getFlights().get(0);
        flight1 = new Flight(75, Airline.AZAL, Cities.BAKU, Cities.ISTANBUL, LocalDate.now(), LocalTime.now());
    }

    @Test
    void testGetAllFlights() {
        assertEquals(50, service.getAllFlights().size());
    }

    @Test
    void testCreateDestination() {
        assertTrue(service.createDestination(flight1));
    }

    @Test
    void testCreateDestinationFalse() {
        assertFalse(service.createDestination(flight));
    }

    @Test
    void testGetById() {
        service.createDestination(flight1);
        assertEquals(flight1, service.getFlight(75));
    }

    @Test
    void testDeletingFlight() {
        service.createDestination(flight1);
        assertTrue(service.deleteFlight(75));
    }


}