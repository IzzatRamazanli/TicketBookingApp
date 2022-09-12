package booking.controller;

import booking.model.User;
import booking.service.UserService;

import java.util.List;

public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    public boolean registration(User user) {
        return service.registration(user);
    }

    public boolean login(String userName, String password) {
        return service.login(userName, password);
    }

    public User getUser(int id) {
        return service.getUser(id);
    }

    public boolean deleteRegistration(int id) {
        return service.deleteRegistration(id);
    }

    public boolean deleteRegistration(User u) {
        return service.deleteRegistration(u);
    }

}
