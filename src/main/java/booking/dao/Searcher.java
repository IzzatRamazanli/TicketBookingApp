package booking.dao;

import java.util.Optional;

public interface Searcher<T> {
    Optional<T> findById(int id);

    Optional<T> findByReference(T t);
}
