package booking.commands;

import booking.console.Console;
import booking.console.IOConsole;
import booking.controller.UserController;
import booking.model.User;

public class Registration {
    private final UserController controller;
    private int userID = 1;

    public Registration(UserController controller) {
        this.controller = controller;
    }

    private static final Console c = new IOConsole();

    public void register() {
        if (controller.registration(getNewUser())) {
            c.print("\nRegistration successfully completed");
        } else c.print("\nRegistration failed!");
    }

    private User getNewUser() {
        String userName = getUserName();
        String password = getPassword();
        return new User(userID++, userName, password);
    }

    private String getUserName() {
        c.print("Enter username (at least 4 character): ");
        String userName = c.readLn();
        if (userName.length() > 3) {
            return userName;
        } else return getUserName();
    }

    private String getPassword() {
        c.print("Enter password (at least 4 character): ");
        String password = c.readLn();
        if (password.length() > 3) {
            return password;
        } else return getPassword();
    }


}
