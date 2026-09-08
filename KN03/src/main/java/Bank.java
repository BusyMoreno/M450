import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Bank {
    private final Map<String, Customer> customers = new LinkedHashMap<>();
    private final Map<String, BankAccount> accounts = new LinkedHashMap<>();

    public void addCustomer(Customer customer) {
        Objects.requireNonNull(customer, "Der Kunde darf nicht null sein.");
        if (customers.putIfAbsent(customer.getCustomerNumber(), customer) != null) {
            throw new IllegalArgumentException("Die Kundennummer existiert bereits.");
        }
    }

    public void openAccount(Customer customer, BankAccount account) {
        Objects.requireNonNull(customer, "Der Kunde darf nicht null sein.");
        Objects.requireNonNull(account, "Das Konto darf nicht null sein.");
        if (!customers.containsKey(customer.getCustomerNumber())) {
            throw new IllegalArgumentException("Der Kunde ist nicht registriert.");
        }
        if (accounts.putIfAbsent(account.getIban(), account) != null) {
            throw new IllegalArgumentException("Die IBAN existiert bereits.");
        }
    }

    public Customer findCustomer(String customerNumber) {
        return customers.get(customerNumber);
    }

    public BankAccount findAccount(String iban) {
        return accounts.get(iban);
    }

    public Map<String, Customer> getCustomers() {
        return Collections.unmodifiableMap(customers);
    }

    public Map<String, BankAccount> getAccounts() {
        return Collections.unmodifiableMap(accounts);
    }
}

