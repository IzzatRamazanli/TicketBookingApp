package booking.database;

import booking.model.Booking;
import booking.model.Flight;
import booking.model.User;
import booking.util.RandomFlightGenerator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileBase {
    private final File fileF = new File("flights.bin");
    private final File fileU = new File("users.bin");
    private final File fileB = new File("bookings.bin");

    private final List<Flight> flights = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();

    public List<Flight> getFlights() {
        return flights;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    private void saveData(File file, List list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData(File file, List list) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            list.addAll((List) in.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setUp() {
        RandomFlightGenerator fg = new RandomFlightGenerator();
        if (fileF.exists()) {
            loadData(fileF, flights);
        } else {
            flights.addAll(fg.sortedFlight(50));
            saveData(fileF, flights);
        }
        if (fileU.exists()) {
            loadData(fileU, users);
        }
        if (fileB.exists()) {
            loadData(fileB, bookings);
        }
    }

    public void save() {
        saveData(fileU, users);
        saveData(fileB, bookings);
        saveData(fileF, flights);
    }

}
