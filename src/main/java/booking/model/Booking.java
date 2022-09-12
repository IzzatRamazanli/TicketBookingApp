package booking.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record Booking(int id, User user, Flight flight, List<Passenger> passengers) implements Serializable {
    private static final long serialVersionUID = 1L;
    private static String bookTime;
    public Booking {
        bookTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        user.addBooking(this);
    }

    public int getId() {
        return id;
    }

    public static String getBookTime() {
        return bookTime;
    }

    @Override
    public String toString() {
        return "RESERVATION ID: %d \nFLIGHT --> %s\nPASSENGERS:\n%s\nBOOK TIME: %s"
                .formatted(id, flight, getPrettyPassengers(), bookTime);
    }

    private String getPrettyPassengers() {
        final StringBuilder[] sb = {new StringBuilder("")};
        flight.getPassengers().forEach(passenger ->
                sb[0] = sb[0].append("PASSENGER %d --> FIRSTNAME: %s | LASTNAME: %s\n"
                        .formatted(passenger.getId(), passenger.firstName(), passenger.lastName())));
        return sb[0].toString();
    }
}
