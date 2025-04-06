package main.najah.test;

import main.najah.code.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Service Simple Tests")
public class UserServiceSimpleTest {
    UserService service;

    @BeforeEach
    void setup() {
        service = new UserService();
    }

   
    @ParameterizedTest
    @ValueSource(strings = {
        "user@example.com", "test.user@domain.co", "hello@site.org"
    })
    @DisplayName("Test valid emails")
    void testValidEmails(String email) {
        assertTrue(service.isValidEmail(email));
    }

    @Test
    @DisplayName("Test invalid emails")
    void testInvalidEmails() {
        assertAll(
            () -> assertFalse(service.isValidEmail(null)),
            () -> assertFalse(service.isValidEmail("missingatsign.com")),
            () -> assertFalse(service.isValidEmail("no.dot@domain"))
        );
    }

    @Test
    @DisplayName("Test authentication with correct credentials")
    void testValidAuthentication() {
        assertTrue(service.authenticate("admin", "1234"));
    }

    @Test
    @DisplayName("Test authentication with wrong credentials")
    void testInvalidAuthentication() {
        assertAll(
            () -> assertFalse(service.authenticate("admin", "wrong")),
            () -> assertFalse(service.authenticate("user", "1234"))
        );
    }

    @Test
    @Timeout(1)
    @DisplayName("Timeout test for valid email")
    void testTimeout() {
        assertTrue(service.isValidEmail("hello@site.org"));
    }

}
