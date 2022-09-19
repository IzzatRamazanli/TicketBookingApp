package booking.controller;

import booking.dao.implementations.UserDAO;
import booking.database.FileBase;
import booking.model.User;
import booking.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private UserController controller;
    private User user;
    private User user1;

    @BeforeEach
    void before() {
        FileBase fb = new FileBase();
        UserDAO dao = new UserDAO(fb);
        UserService service = new UserService(dao);
        controller = new UserController(service);
        fb.setUp();
        user = fb.getUsers().get(0);
        user1 = new User(3, "ali", "3456");
    }

    @Test
    void testGetAllUsers() {
        assertEquals(2, controller.getAllUsers().size());
    }

    @Test
    void testGetUserById() {
        controller.registration(user1);
        assertEquals(user1, controller.getUser(3));
    }

    @Test
    void testRegistration() {
        assertTrue(controller.registration(user1));
    }

    @Test
    void testFalseRegistration() {
        assertFalse(controller.registration(user));
    }

    @Test
    void testLogin() {
        controller.registration(user1);
        assertTrue(controller.login("ali", "3456"));
    }

    @Test
    void testFalseLogin() {
        assertFalse(controller.login("ali", "3456"));
    }

    @Test
    void testDeletingRegistrationById() {
        controller.registration(user1);
        assertTrue(controller.deleteRegistration(3));
    }

    @Test
    void testFalseDeletingRegistrationById() {
        assertFalse(controller.deleteRegistration(3));
    }

    @Test
    void testDeletingRegistrationByReference() {
        controller.registration(user1);
        assertTrue(controller.deleteRegistration(user1));
    }

    @Test
    void testFalseDeletingRegistrationByReference() {
        assertFalse(controller.deleteRegistration(user1));
    }

}