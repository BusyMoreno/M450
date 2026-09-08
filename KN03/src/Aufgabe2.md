# Aufgabe 2 - JUnit 5 Zusammenfassung

Dieses Dokument fasst die wichtigsten Features von JUnit 5 zusammen, welche für das Unit-Testing von Java-Applikationen verwendet werden.

---

## 1. Lifecycle-Annotationen

JUnit 5 bietet eindeutige Annotationen zur Steuerung der Testausführung und zur Vorbereitung der Testumgebung.

| Annotation | Ausführung | Zweck / Anwendungsfall |
|------------|------------|------------------------|
| `@Test` | Einzelfall | Markiert eine Methode als ausführbaren Testfall. |
| `@BeforeEach` | Vor jedem `@Test` | Erstellen frischer Testobjekte, um Isolation zu sichern. |
| `@AfterEach` | Nach jedem `@Test` | Aufräumarbeiten (Mock-Reset, temporäre Dateien löschen). |
| `@BeforeAll` | Einmal vor allen Tests (`static`) | Ressourcenintensives Setup (z. B. In-Memory-Datenbank starten). |
| `@AfterAll` | Einmal nach allen Tests (`static`) | Globale Ressourcen freigeben (z. B. DB-Verbindung trennen). |

---

## 2. Wichtige Assertions (`org.junit.jupiter.api.Assertions`)

Assertions prüfen, ob das erwartete Ergebnis eingetreten ist.

* `assertEquals(expected, actual, [delta])`: Vergleicht Soll- und Ist-Wert. Bei Fließkommazahlen (`double`) muss ein Delta (z. B. `0.0001`) angegeben werden.
* `assertTrue(condition)` / `assertFalse(condition)`: Prüft auf boolesche Bedingungen.
* `assertSame(object1, object2)`: Prüft, ob beide Referenzen auf exakt dasselbe Objekt im Speicher zeigen.
* `assertThrows(ExceptionClass.class, executable)`: Prüft, ob eine bestimmte Exception geworfen wird (über ein Lambda-Executable).
* `assertAll(...)`: Führt mehrere Assertions gebündelt aus, selbst wenn eine davon fehlschlägt.

---

## 3. Parametrisierte Tests (`@ParameterizedTest`)

Anwendungsfall: Eine Methode soll mit mehreren verschiedenen Eingaben getestet werden, ohne den Testcode mehrfach zu duplizieren.

```java
@ParameterizedTest
@CsvSource({
    "1.0, 2.0, 3.0",
    "-10.0, 5.0, -5.0",
    "0.0, 0.0, 0.0"
})
void addTest(double a, double b, double expected) {
    assertEquals(expected, calculator.add(a, b), 0.0001);
}
```

---

## 4. Strukturierung & Verständlichkeit

* `@DisplayName("...")`: Beschreibt den Testfall in lesbarem Klartext für die Testberichte der IDE.
* `@Disabled("Begründung")`: Deaktiviert Testfälle temporär (z. B. wenn ein Feature noch in Arbeit ist).
* `@Nested`: Ermöglicht eine hierarchische Gruppierung von inneren Testklassen für mehr Übersichtlichkeit.

---

## 5. Referenzen & Links

* [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
* [Vogella JUnit 5 Tutorial](https://www.vogella.com/tutorials/JUnit/article.html)