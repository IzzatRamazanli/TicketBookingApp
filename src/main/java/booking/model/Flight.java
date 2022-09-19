package booking.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record Flight(int id, Airline airline, Cities cityFrom, Cities cityTo, LocalDate date,
                     LocalTime time) implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int seats;

    public String getFlightTime() {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getFlightDate() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setSeats(int seats) {
        Flight.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return "\nID: %3d || %5s (%s) ---> %s (%s)  || %s  %s || %s (%s)"
                .formatted(id, cityFrom, cityFrom.getAirportCode(), cityTo, cityTo.getAirportCode(),
                        getFlightDate(), getFlightTime(), airline, airline.getCode()) + "\n" +
                "_________________________________________________________________________________________";
    }

}
