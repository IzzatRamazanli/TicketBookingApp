package booking.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> getAll();

    T get(int id);

    boolean create(T t);

    boolean delete(T t);

    boolean delete(int id);


}
