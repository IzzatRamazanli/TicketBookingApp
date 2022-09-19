package booking.controller;

import booking.dao.implementations.FlightDAO;
import booking.database.FileBase;
import booking.model.Airline;
import booking.model.Cities;
import booking.model.Flight;
import booking.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightControllerTest {
    private FlightController controller;
    private Flight flight;
    private Flight flight1;

    @BeforeEach
    void before() {
        FileBase fb = new FileBase();
        FlightDAO dao = new FlightDAO(fb);
        FlightService service = new FlightService(dao);
        controller = new FlightController(service);
        fb.setUp();
        flight = fb.getFlights().get(0);
        flight1 = new Flight(75, Airline.AZAL, Cities.BAKU, Cities.ISTANBUL, LocalDate.now(), LocalTime.now());
    }

    @Test
    void testGetAllFlights() {
        controller.createDestination(flight1);
        assertEquals(51, controller.getAllFlights().size());
    }

    @Test
    void testGetFlight() {
        controller.createDestination(flight1);
        assertEquals(flight1, controller.getFlight(75));
    }

    @Test
    void testCreateDestination() {
        assertTrue(controller.createDestination(flight1));
    }

    @Test
    void testFalseCreateDestination() {
        assertFalse(controller.createDestination(flight));
    }

    @Test
    void testDeletingFlight() {
        controller.createDestination(flight1);
        assertTrue(controller.deleteFlight(75));
    }

    @Test
    void testFalseDeletingFlight() {
        assertFalse(controller.deleteFlight(88));
    }

}