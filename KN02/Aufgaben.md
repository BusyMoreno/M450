# Aufgabe 1

### Abstrakte Testfälle

| Bedingung    | Erwartet | Typ      |
| ------------ | -------- | -------- |
| Preis < 0    | Ungültig | Ungültig |
| Preis > 0 & Preis < 15K | 0.0 % | Gültig |
| Preis >= 15K & Preis <= 20K | 5.0 % | Gültig |
| P > 20K & Preis < 25K | 7.0 % | Gültig |
| P >= 25K | 8.5 % | Gültig |

### Konkrete Testfälle

| Kaufpreis CHF | Erwartet | Erwarteter Endpreis | Testfokus |
|---|---|---|---|
| 100 | Ungültig | - | Negativer Grenzwert |
| 0 | Ungültig | - | Nullwert |
| 14999 | 0% | 14999 | Kaufpreis < 15'000 |
| 15000 | 5% | 14250 | Exakte Grenze 5% |
| 20000 | 5% | 19000 | Obere Grenze 5% |
| 20001 | 7% | 18600 | Untere Grenze 7% |
| 24999 | 7% | 23249 | Obere Grenze 7% |
| 25000 | 8.5% | 22875 | Exakte Schwelle 8.5% |
| 50000 | 8.5% | 45750 | Standardwert obere Stufe |


# Aufgabe 2

| **Beschreibung** | **Erwartetes Resultat** | **Effektives Resultat** | **Status** |
|---|---|---|---|
| Eingabe von `ZRH` oder `Zürich` im Abholfeld. | Dropdown schlägt Stationen vor. Nach Auswahl und Datumswahl lädt die Fahrzeugliste. | Dropdown filtert passend | Erfolgreich |
| Versuch, das Rückgabedatum zeitlich vor dem Abholdatum zu setzen. | Kalender deaktiviert vergangene Tage/Zeiten oder schiebt das Rückgabedatum automatisch min. 1 Stunde nach vorne | Kalender-Widget verhindert Auswahl früherer Tage | Erfolgreich |
| Auswahl einer Kategorie wie Premium (z. B. BMW 3er Serie) mit Fahreralter unter 21 Jahren. | Fahrzeug wird in der Liste nicht angezeigt. Wenn Alter auf 21 gesetzt wird taucht es wieder auf. | Fahrzeug verschwindet automatisch aus der Auswahl sobald das Mindestalter auf < 21 Jahre gesetzt wird | Erfolgreich |
| Auswahl des Tarifs „Später zahlen (Flexi)“ vs. „Sofort online zahlen“. | Bei Flexi wird der Preis ohne sofortige Kartenbelastung reserviert (kostenlose Stornierung bis Abholung); bei Sofortzahlung wird die Kartenzahlungsmaske verlangt. | Zahlungsformular passt sich dynamisch an; Flexi verlangt Zahlungsdaten nur zur Garantie, Prepaid löst sofortigen Secure-Flow aus. | Erfolgreich |

# Aufgabe 3

| Testfall / Aktion | Eingabewerte / Vorbedingung | Erwartetes Resultat |
|---|---|---|
| **Kontoerstellung** | Name: „Max Muster“, Startguthaben: 100CHF | Konto wird angelegt, eindeutige Kontonummer generiert, Kontostand beträgt 100CHF. |
| **Gültige Einzahlung** | Bestehendes Konto, Betrag: 50CHF | Kontostand erhöht sich exakt um 50CHF, Transaktion wird protokolliert. |
| **Ungültige Einzahlung (Negativbetrag)** | Betrag: -20.00CHF | System verweigert Buchung mit Fehlermeldung, Saldo bleibt unverändert. |
| **Gültige Überweisung** | Senderkonto: Saldo 500CHF, Zielkonto, Betrag: 200CHF | Sender: 300CHF, Empfänger: +200CHF. |
| **Überweisung mit Überziehung** | Senderkonto: Saldo 50CHF, Betrag: 100CHF | Überweisung schlägt fehl, Saldo beider Konten bleibt unverändert. |

### White-Box Testfälle

* `Account.deposit(double amount)`:
  * Grenzwerttests auf positive Werte, 0, und negative Werte.
  * Verifikation der Zustandsänderung des Saldos.
* `Account.withdraw(double amount)`:
  * Verzweigungstests: `amount <= balance` vs. `amount > balance`.
  * Verhalten bei Überschreiten des Überziehungslimits.
* `BankService.transfer(String fromAcc, String toAcc, double amount)`:
  * Prüfung auf atomare Ausführung
  * Null-Pointer-Checks für nicht existierende Kontonummern.

### Code Verbesserungen und Best Practices

* **Datentypen für Geldbeträge:**
  * Keine `double`- oder `float` für Währungsbeträge verwenden (Gefahr von Rundungsfehlern). Stattdessen `java.math.BigDecimal` oder eine geeignete `Money` Klasse nutzen.
* **Robustes Error Handling:**
  * Eigene Exceptions z. B. `InsufficientFundsException`, `AccountNotFoundException` erstellen, statt generische `RuntimeExceptions` zu verwenden.
* **Validierung & Unveränderlichkeit:**
  * Filter zu Beginn von Methoden implementieren (z. B. `amount > 0`).