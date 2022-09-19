package booking.dao.implementations;

import booking.dao.DAO;
import booking.dao.Searcher;
import booking.database.FileBase;
import booking.model.User;

import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User>, Searcher<User> {
    private final FileBase fb;

    public UserDAO(FileBase fb) {
        this.fb = fb;
    }

    @Override
    public List<User> getAll() {
        return fb.getUsers();
    }

    @Override
    public User get(int id) {
        return findById(id).orElse(null);
    }

    @Override
    public boolean create(User user) {
        if (user == null || findByReference(user).isPresent()) return false;
        fb.getUsers().add(user);
        return true;
    }

    @Override
    public boolean delete(User user) {
        if (findByReference(user).isPresent()) {
            fb.getUsers().remove(user);
            return true;
        } else return false;
    }

    @Override
    public boolean delete(int id) {
        if (findById(id).isEmpty()) return false;
        return fb.getUsers().removeIf(x -> id == x.id());
    }

    @Override
    public Optional<User> findById(int id) {
        return fb.getUsers().stream()
                .filter(user -> id == user.id())
                .findFirst();
    }

    @Override
    public Optional<User> findByReference(User user) {
        return fb.getUsers().stream().filter(user::equals).findFirst();
    }
}
