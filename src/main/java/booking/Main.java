package booking;


public class Main {
    public static void main(String[] args) {
        BookingApp app = new BookingApp();
        while (app.start()) {
            if (!app.start()) break;
        }
    }
}
