import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("addiert zwei Zahlen")
    void addsTwoNumbers() {
        assertEquals(5.0, calculator.add(2.0, 3.0));
    }

    @Test
    @DisplayName("subtrahiert zwei Zahlen")
    void subtractsTwoNumbers() {
        assertEquals(2.0, calculator.subtract(5.0, 3.0));
    }

    @Test
    @DisplayName("multipliziert zwei Zahlen")
    void multipliesTwoNumbers() {
        assertEquals(6.0, calculator.multiply(2.0, 3.0));
    }

    @Test
    @DisplayName("dividiert zwei Zahlen")
    void dividesTwoNumbers() {
        assertEquals(2.0, calculator.divide(6.0, 3.0));
    }

    @Test
    @DisplayName("verhindert Division durch null")
    void rejectsDivisionByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(10.0, 0.0));
    }
}

