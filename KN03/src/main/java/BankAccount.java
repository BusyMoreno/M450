import java.math.BigDecimal;
import java.util.Objects;

public class BankAccount {
    private final String iban;
    private BigDecimal balance;

    public BankAccount(String iban) {
        if (iban == null || iban.isBlank()) {
            throw new IllegalArgumentException("Die IBAN darf nicht leer sein.");
        }
        this.iban = iban;
        this.balance = BigDecimal.ZERO;
    }

    public String getIban() {
        return iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        validatePositiveAmount(amount);
        if (amount.compareTo(this.balance) > 0) {
            throw new IllegalStateException("Das Guthaben reicht nicht aus.");
        }
        this.balance = this.balance.subtract(amount);
    }

    private void validatePositiveAmount(BigDecimal amount) {
        Objects.requireNonNull(amount, "Der Betrag darf nicht null sein.");
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Der Betrag muss positiv sein.");
        }
    }
}

