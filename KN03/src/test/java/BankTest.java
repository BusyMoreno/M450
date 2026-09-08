import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    private Bank bank;
    private Customer customer;
    private BankAccount account;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        customer = new Customer("C-100", "Ada Lovelace");
        account = new BankAccount("CH00 0000 0000 0000 0000 0");
        bank.addCustomer(customer);
        bank.openAccount(customer, account);
    }

    @Test
    void storesAndFindsCustomerAndAccount() {
        assertSame(customer, bank.findCustomer(customer.getCustomerNumber()));
        assertSame(account, bank.findAccount(account.getIban()));
        assertEquals(1, bank.getCustomers().size());
        assertEquals(1, bank.getAccounts().size());
    }

    @Test
    void depositsAndWithdrawsMoney() {
        account.deposit(new BigDecimal("100.00"));
        account.withdraw(new BigDecimal("35.50"));
        assertEquals(new BigDecimal("64.50"), account.getBalance());
    }

    @Test
    void rejectsInvalidMoneyOperations() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(new BigDecimal("-1")));
        assertThrows(IllegalStateException.class, () -> account.withdraw(BigDecimal.ONE));
    }

    @Test
    void rejectsDuplicateCustomersAndAccounts() {
        assertThrows(IllegalArgumentException.class, () -> bank.addCustomer(new Customer("C-100", "Grace Hopper")));
        assertThrows(IllegalArgumentException.class, () -> bank.openAccount(customer, new BankAccount("CH00 0000 0000 0000 0000 0")));
    }

    @Test
    void requiresRegisteredCustomer() {
        Customer unknown = new Customer("C-200", "Grace Hopper");
        assertThrows(IllegalArgumentException.class, () -> bank.openAccount(unknown, new BankAccount("CH00 0000 0000 0000 0000 1")));
        assertTrue(bank.getAccounts().containsValue(account));
    }
}

