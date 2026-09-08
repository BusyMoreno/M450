import java.util.Objects;

public class Customer {
    private final String customerNumber;
    private final String name;

    public Customer(String customerNumber, String name) {
        if (customerNumber == null || customerNumber.isBlank()) {
            throw new IllegalArgumentException("Die Kundennummer darf nicht leer sein.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Der Name darf nicht leer sein.");
        }
        this.customerNumber = customerNumber;
        this.name = name;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerNumber, customer.customerNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerNumber);
    }
}

