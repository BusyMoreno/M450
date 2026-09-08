import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests für BankAccount")
class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("CH00 1111 2222 3333 4000 0");
    }

    @Test
    @DisplayName("Initialisierung mit IBPN und Saldo 0")
    void testInitialization() {
        assertEquals("CH00 1111 2222 3333 4000 0", account.getIban());
        assertEquals(BigDecimal.ZERO, account.getBalance());
    }

    @Test
    @DisplayName("IBAN darf nicht null oder leer sein")
    void testInvalidIban() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(null));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("   "));
    }

    @Test
    @DisplayName("Einzahlung von null wirft Exception")
    void testDepositNull() {
        assertThrows(NullPointerException.class, () -> account.deposit(null));
    }

    @Test
    @DisplayName("Auszahlung unter Saldo dürfen nicht angenommen werden")
    void testWithdrawExceedingBalance() {
        account.deposit(new BigDecimal("50.00"));
        assertThrows(IllegalStateException.class, () -> account.withdraw(new BigDecimal("50.01")));
    }
}

