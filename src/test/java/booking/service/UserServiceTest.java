package booking.service;

import booking.dao.implementations.UserDAO;
import booking.database.FileBase;
import booking.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService service;
    private User user;
    private User user1;

    @BeforeEach
    void before() {
        FileBase fb = new FileBase();
        UserDAO dao = new UserDAO(fb);
        service = new UserService(dao);
        fb.setUp();
        user = fb.getUsers().get(0);
        user1 = new User(3, "ali", "3456");
    }

    @Test
    void testGetAllUsers() {
        service.registration(user1);
        assertEquals(3, service.getAllUsers().size());
    }

    @Test
    void testGetById() {
        assertEquals(user, service.getUser(1));
    }

    @Test
    void testRegistration() {
        assertTrue(service.registration(user1));
    }

    @Test
    void testRegistrationFalse() {
        assertFalse(service.registration(user));
    }

    @Test
    void testLogin() {
        service.registration(user1);
        assertTrue(service.login("ali", "3456"));
    }

    @Test
    void testFalseLogin() {
        assertFalse(service.login("ali", "3456"));
    }

    @Test
    void testDeletingUserById() {
        service.registration(user1);
        assertTrue(service.deleteRegistration(3));
    }

    @Test
    void testDeletingUserByReference() {
        service.registration(user1);
        assertTrue(service.deleteRegistration(user1));
    }

}