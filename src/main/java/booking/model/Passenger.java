package booking.model;

import java.io.Serializable;

public record Passenger(int id, String firstName, String lastName) implements Serializable {
    private static final long serialVersionUID = 1L;

    public Passenger {
        id++;
    }

    @Override
    public String toString() {
        return "PASSENGER --> FIRSTNAME: %s  LASTNAME: %s\n".formatted(firstName, lastName);
    }
}
