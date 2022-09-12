package booking.service;

import booking.dao.implementations.UserDAO;
import booking.model.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public List<User> getAllUsers() {
        return dao.getAll();
    }

    public boolean registration(User user) {
        Optional<User> find = dao.getAll().stream()
                .filter(x -> x.userName().equals(user.userName()))
                .findFirst();
        if (find.isPresent()) return false;
        return dao.create(user);
    }

    public boolean login(String userName, String password) {
        Optional<User> find = dao.getAll().stream()
                .filter(user -> user.userName().equals(userName) && user.password().equals(password))
                .findFirst();
        return find.isPresent();
    }

    public User getUser(int id) {
        return dao.get(id);
    }

    public boolean deleteRegistration(int id) {
        return dao.delete(id);
    }

    public boolean deleteRegistration(User u) {
        return dao.delete(u);
    }

}
