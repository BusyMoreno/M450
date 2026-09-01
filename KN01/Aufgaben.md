# Aufgabe 1

#### Welche Formen von Tests kennen Sie aus der Informatik?
- Unit tests
- Smoke tests
- Blackbox testing 
- Whitebox testing
- Integrationtests
- Security test
- Stress test
#### Erläutern Sie mind. drei Beispiele, die Sie aus der Praxis kennen.
- Blackbox testing, man testet die App aber ohne zu wissen wie der code aussieht. So testet man mit jemandem der nicht unbedingt weiss wie die app funktioniert und reagiert.
- Security testing, man testet die Sicherheit. Z.B. Login formulare oder ob man auf sachen kommt die eigentlich gesperrt sind wie ein admin panel.
- Unit tests, man testet einzelne Komponenten oder Klassen isoliert.
#### Wie werden die Tests durchgeführt?
- Tester bedient das UI wie ein echter user ohne den code zu kennen.
- Prüfen, ob die App bei bestimmten Eingaben die erwarteten Ausgaben liefert.
- Tester versuchen durch unübliche Klicks oder fehlerhafte Formulareingaben Systemabstürze auszulösen.
- Tools prüfen das Produkt auf bekannte Sicherheitslücken und veraltete Bibliotheken.
- Mit einem Experten simuliert man gezielte Hackerangriffe auf die Infrastruktur und Anwendung.
- Eingabefelder (wie Logins) werden mit Schadcode befüllt, um Datenzugriffe zu erzwingen.
- Man versucht durch Manipulation von URLs oder Session-Tokens auf das Adminpanel zu gelangen.
- Entwickler schreiben Testcode mithilfe von Werkzeugen wie JUnit, NUnit oder Jest.
- Externe Abhängigkeiten wie Datenbanken oder APIs werden durch künstliche Platzhalter ersetzt.
- Die Tests laufen vollautomatisch bei jeder Code-Änderung in der Deployment-Pipeline. Kann z.B. auf Github Workflows gemacht werden.
- Der Code einer einzelnen Methode wird gezielt mit null oder leeren Eingaben ausgefüllt.


----

# Aufgabe 2

#### Nennen Sie ein Beispiel eines SW-Fehlers und eines SW-Mangels. 
Fehler: Wenn ich an einem Dokument arbeite und ich es speichern will, klicke ich auf "Save". Ich sehe zwar eine visuelle bestätigung und habe das Gefühl das es gespeichert wurde. Dabei habe ich das dokument nun verloren weil es nicht gespeichert wurde.

Mangel: Wenn ich an einem Dokument arbeite und ich es speichern will, klicke ich auf "Save". Ich sehe keine visuelle bestätigung und weiss nicht ob es gespeichert wurde. Dies führt zur verwirrung.
#### Nennen Sie ein Beispiel für einen hohen Schaden bei einem SW-Fehler.
In einem Online shop wird bei der Berechnung eines gutschein codes statt 10%, 100% rabatt angewendet.
Da es ein elektronik shop ist welcher waren im wert von bis zu 2500Fr. verkauft, wird der schaden sehr gross. Der Verlust von vielen elektronischen geräten wird sehr hoch und die Kunden zahlen nichts.

----

# Aufgabe 3

#### Was ist ein Testtreiber und wozu dient er?
Ein Testtreiber ist ein Hilfsprogramm, das entwickelt wird, um eine Methode oder Komponente auf der untersten Teststufe isoliert aufzurufen. Er übergibt gezielte Testdaten an die Schnittstelle, fängt das Resultat ab und vergleicht das Ist-Ergebnis mit dem erwarteten Soll-Ergebnis.

#### Umsetzung des Testtreibers in Java

[Testfile](Tests.java)

