package ch.tbz.m450.comparator;

import ch.tbz.m450.repository.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class AddressComparatorTest {

    private AddressComparator comparator;

    @BeforeEach
    void setUp() {
        comparator = new AddressComparator();
    }

    @Test
    void testDifferentLastname() {
        Address a1 = new Address(1L, "Hans", "Aebischer", "Dorfstrasse 1", "123", new Date());
        Address a2 = new Address(2L, "Hans", "Zehnder", "Dorfstrasse 1", "123", new Date());
        assertTrue(comparator.compare(a1, a2) < 0);
    }

    @Test
    void testSameLastnameDifferentFirstname() {
        Address a1 = new Address(1L, "Albert", "Muster", "Ahornweg 2", "123", new Date());
        Address a2 = new Address(2L, "Beat", "Muster", "Ahornweg 2", "123", new Date());
        assertTrue(comparator.compare(a1, a2) < 0);
    }

    @Test
    void testSameNameDifferentStreet() {
        Address a1 = new Address(1L, "Max", "Muster", "Ahornweg 2", "123", new Date());
        Address a2 = new Address(2L, "Max", "Muster", "Zelgweg 9", "123", new Date());
        assertTrue(comparator.compare(a1, a2) < 0);
    }

    @Test
    void testNullValues() {
        Address a1 = new Address(1L, "Max", "Muster", "Gasse", null, null);
        assertTrue(comparator.compare(null, a1) < 0);
        assertTrue(comparator.compare(a1, null) > 0);
        assertEquals(0, comparator.compare(null, null));
    }
}