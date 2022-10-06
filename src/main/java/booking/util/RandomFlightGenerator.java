package booking.util;

import booking.model.Airline;
import booking.model.Cities;
import booking.model.Flight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class RandomFlightGenerator {
    private static final Random random = new Random();
    private int flightID = 1;

    public static Airline randomAirline() {
        return Airline.values()[random.nextInt(Airline.values().length)];
    }

    public static Cities randomCity() {
        return Cities.values()[random.nextInt(Cities.values().length)];
    }

    public LocalDate randomDate() {
        return LocalDate.now().plusDays(random.nextInt(30));
    }

    public LocalTime randomTime() {
        int randomHour = random.nextInt(23) + 1;
        int randomMinute = random.nextInt(4) * 10;
        if (randomHour < 10) {
            randomHour = Integer.parseInt("0" + randomHour);
        }
        return LocalTime.of(randomHour, randomMinute);
    }

    private Flight generator() {
        Airline airline = randomAirline();
        Cities cityFrom = randomCity();
        Cities cityTo = randomCity();
        LocalDate date = randomDate();
        LocalTime time = randomTime();
        return new Flight(flightID++, airline, cityFrom, cityTo, date, time);
    }

    public List<Flight> randomFlights(int count) {
        Set<Flight> flights = new HashSet<>();
        Flight flight;
        for (int i = 0; i < count; i++) {
            flight = generator();
            flights.add(flight);
        }
        return new ArrayList<>(flights);
    }

    public List<Flight> sortedFlight(int count) {
        List<Flight> flights = randomFlights(count);
        flights.sort(sortByDate);
        return new ArrayList<>(flights);
    }

    private final Comparator<Flight> sortByDate = Comparator.comparing(Flight::date);


}
