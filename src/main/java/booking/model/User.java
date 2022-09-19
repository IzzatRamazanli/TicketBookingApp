package booking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record User(int id, String userName, String password) implements Serializable {
    private static final long serialVersionUID = 1L;
    private static List<Booking> bookings;

    public User {
        bookings = new ArrayList<>();
    }

    public void addBooking(Booking b) {
        if (find(b).isPresent()) {
            System.out.println("This reservation already exist");
        } else {
            bookings.add(b);
        }
    }

    private Optional<Booking> find(Booking b) {
        if (b == null) return Optional.empty();
        return bookings.stream()
                .filter(x -> x.equals(b))
                .findFirst();
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    @Override
    public String toString() {
        return "User{id='%d' userName='%s', password='%s', bookings='%s'}"
                .formatted(id, userName, password, bookings);
    }
}
