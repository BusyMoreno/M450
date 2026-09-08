import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests für Customer")
class CustomerTest {

    @Test
    @DisplayName("Erfolgreiche Erstellung eines Kunden")
    void testCreateCustomer() {
        Customer customer = new Customer("C-1", "Max Muster");
        assertEquals("C-1", customer.getCustomerNumber());
        assertEquals("Max Muster", customer.getName());
    }

    @Test
    @DisplayName("Verwirft Exception bei leeren Eingaben")
    void testInvalidCustomerInputs() {
        assertThrows(IllegalArgumentException.class, () -> new Customer(null, "Max"));
        assertThrows(IllegalArgumentException.class, () -> new Customer("   ", "Max"));
        assertThrows(IllegalArgumentException.class, () -> new Customer("C-1", null));
        assertThrows(IllegalArgumentException.class, () -> new Customer("C-1", "   "));
    }

    @Test
    @DisplayName("Equals und HashCode basieren auf der Kundennummer")
    void testEqualsAndHashCode() {
        Customer a1 = new Customer("C-100", "Ada Lovelace");
        Customer a2 = new Customer("C-100", "Ada NeuerName");
        Customer b1 = new Customer("C-200", "Ada Lovelace");

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
        assertNotEquals(a1, b1);
        assertNotEquals(a1, null);
        assertNotEquals(a1, "StringObject");
    }
}