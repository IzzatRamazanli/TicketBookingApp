package booking.dao.implementations;

import booking.database.FileBase;
import booking.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private UserDAO dao;
    private User user;
    private User user1;

    @BeforeEach
    void before() {
        FileBase fb = new FileBase();
        dao = new UserDAO(fb);
        fb.setUp();
        user = fb.getUsers().get(0);
        user1 = new User(3, "ali", "3456");
    }

    @Test
    void testGetAll() {
        dao.create(user1);
        assertEquals(3, dao.getAll().size());

    }

    @Test
    void testGetById() {
        assertEquals(user, dao.get(1));
    }

    @Test
    void testCreate() {
        dao.create(user1);
        assertEquals(user1, dao.get(3));
    }

    @Test
    void testDeletingByReference() {
        dao.create(user1);
        dao.delete(user1);
        assertEquals(2, dao.getAll().size());
    }

    @Test
    void testDeletingById() {
        dao.create(user1);
        assertTrue(dao.delete(3));
    }
}