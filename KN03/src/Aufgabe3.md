# Aufgabe 3 - Banken Simulation Dokumentation

Dieses Dokument dokumentiert die Struktur, die Funktionsweise und die Zusammenhänge der Banken-Simulation auf Basis des Klassendiagramms und der Implementierung[cite: 18].

---

## 1. Übersicht der Klassen & Verantwortlichkeiten

### Customer
* Entität für die Stammdaten eines Kunden[cite: 18].
* Kernregistration: Hält die `customerNumber` (z. B. "C-100") sowie den Namen[cite: 18].
* Validierung: Kundennummer und Name dürfen weder `null` noch leer sein[cite: 18].
* Identität: Implementiert `equals()` und `hashCode()` basierend auf der `customerNumber`[cite: 18].

### BankAccount
* Repräsentiert das Bankkonto mit einer eindeutigen `IBAN` (z. B. "CH00 0000 ...")[cite: 18].
* Geldwerte: Nutzt `java.math.BigDecimal`, um Rundungsfehler bei Fremdwährungen und Cent-Beträgen exakt zu verhindern[cite: 18].
* Kontostand: Startet immer mit `BigDecimal.ZERO`[cite: 18].
* Operationen:
  * deposit(amount): Prüft auf positiven Betrag und addiert den Wert zum Saldo[cite: 18].
  * withdraw(amount): Prüft auf positiven Betrag und verhindert Überziehungen (wirft `IllegalStateException`, wenn der Betrag den Saldo übersteigt)[cite: 18].

### Bank
* Die zentrale Verwaltungsklasse (Aggregat) der Bank[cite: 18].
* Datenspeicher: Verwaltet Kunden und Konten in `LinkedHashMap`s mit dem Schlüssel Kundennummer bzw. IBAN[cite: 18].
* Geschäftslogik:
  * addCustomer(Customer): Registriert einen neuen Kunden[cite: 18]. Duplikate dürfen nicht vorkommen[cite: 18].
  * openAccount(Customer, BankAccount): Eröffnet ein Konto[cite: 18]. Der Kunde muss zuvor registriert sein, und die IBAN darf noch nicht existieren[cite: 18].
* Abfragen:
  * findCustomer(), findAccount() sowie getCustomers() und getAccounts() als unveränderliche Maps via `Collections.unmodifiableMap`[cite: 18].

---

## 2. Zusammenhänge und Interaktion
1. Bank hält jeweils 0 bis n Customers und 0 bis n BankAccounts[cite: 18].
2. Ein Kunde kann erst ein Konto eröffnen, wenn er in der Bank eingetragen ist (Vermeidung inkonsistenter Zustände)[cite: 18].
3. Alle Transaktionen werden durch explizite Validierungen geschützt (Null-Checks, Leerstring-Prüfungen, positive Beträge, kein Überziehen des Kontostands)[cite: 18].