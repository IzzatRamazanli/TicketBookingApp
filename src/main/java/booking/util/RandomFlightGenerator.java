package booking.util;

import booking.model.Airline;
import booking.model.Cities;
import booking.model.Flight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFlightGenerator {
    private static final Random random = new Random();


    public static Airline randomAirline() {
        return Airline.values()[random.nextInt(Airline.values().length)];
    }

    public static Cities randomCity() {
        return Cities.values()[random.nextInt(Cities.values().length)];
    }

    public LocalDate randomDate() {
        LocalDate start = LocalDate.now();
        return LocalDate.of(2022, start.getMonth().getValue() + random.nextInt(3), random.nextInt(25) + 1);
    }

    public LocalTime randomTime() {
        int randomHour = random.nextInt(23) + 1;
        int randomMinute = random.nextInt(4) * 10;
        if (randomHour < 10) {
            randomHour = Integer.parseInt("0" + randomHour);
        }
        return LocalTime.of(randomHour, randomMinute);
    }
    private Flight generator(int count) {
        Airline airline = randomAirline();
        Cities cityFrom = randomCity();
        Cities cityTo = randomCity();
        LocalDate date = randomDate();
        LocalTime time = randomTime();
        int id = random.nextInt(count) + 1;
        return new Flight(id, airline, cityFrom, cityTo, date, time);
    }

    public List<Flight> randomFlights(int count) {
        List<Flight> flights = new ArrayList<>();
        Flight flight;
        for (int i = 0; i < count; i++) {
            flight = generator(count);
            flights.add(flight);
        }
        return flights;
    }


}
