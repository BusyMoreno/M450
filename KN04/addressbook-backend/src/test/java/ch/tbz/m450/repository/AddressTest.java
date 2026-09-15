package ch.tbz.m450.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private Address address;
    private Date registrationDate;

    @BeforeEach
    void setUp() {
        registrationDate = new Date();
        address = new Address(1L, "Max", "Muster", "Musterstrasse 1", "0791234567", registrationDate);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1L, address.getId());
        assertEquals("Max", address.getFirstname());
        assertEquals("Muster", address.getLastname());
        assertEquals("Musterstrasse 1", address.getStreet());
        assertEquals("0791234567", address.getPhonenumber());
        assertEquals(registrationDate, address.getRegistrationDate());
    }

    @Test
    void testSetters() {
        address.setFirstname("Anna");
        assertEquals("Anna", address.getFirstname());
    }

    @Test
    void testNoArgsConstructor() {
        Address empty = new Address();
        assertNull(empty.getId());
    }
}