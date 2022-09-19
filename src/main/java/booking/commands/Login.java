package booking.commands;

import booking.console.Console;
import booking.console.IOConsole;
import booking.controller.UserController;
import booking.model.User;

import java.util.Iterator;

public class Login {
    private final UserController controller;
    private final Console c = new IOConsole();

    public Login(UserController controller) {
        this.controller = controller;
    }

    public User login() {
        User u = null;
        c.print("USERNAME: ");
        String userName = c.readLn();
        c.print("PASSWORD: ");
        String password = c.readLn();
        if (!controller.login(userName, password)) {
            c.print("Wrong password or username, try again!\n");
            return login();
        } else {
            Iterator<User> iterator = controller.getAllUsers().iterator();
            while (iterator.hasNext()) {
                u = iterator.next();
                if (u.userName().equals(userName) && u.password().equals(password)) {
                    c.print("\nWELCOME " + userName.toUpperCase() + "\n");
                    return u;
                }
            }
        }
        return u;
    }


}
