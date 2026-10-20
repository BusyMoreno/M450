# Erkenntnisse aus den Code Reviews (Aufgabe 1 & 2)

### Aufgabe 1: Feature «Neues Recipe hinzufügen»
- **Schnittstellen-Diskrepanzen frühzeitig erkennen:** Das Review deckte auf, dass Feldnamen im Frontend (`quantity` als String) nicht zum Backend (`amount` als Integer) passten. Ohne Review wäre dieser stille Fehler erst zur Laufzeit aufgefallen.
- **Architektur & DRY:** Das Auslagern von `axios`-Calls in eine zentrale `recipeApi.js` verhindert duplizierte Codezeilen und erleichtert künftige URL-Anpassungen.
- **Konstruktive Kritik:** Review-Kommentare sollten lösungsorientiert und als Vorschlag formuliert werden.

### Aufgabe 2: Feature «Existierendes Recipe editieren»
- **PR-Grösse & Reviewbarkeit:** Das versehentliche Committen von Build-Artefakten (`target/`, generierte Klassen) liess den PR auf über 21'000 Zeilen anwachsen. Eine saubere `.gitignore` und PR-Grössen von 200–400 Zeilen sind essenziell, damit Reviews übersichtlich bleiben.
- **Single Responsibility Principle (SRP):** Das Auslagern von Formularlogik in eigene Routen/Komponenten (`EditRecipe` bzw. `RecipeForm`) hält Anzeigekomponenten wie `Recipe.js` schlank und trennt Verantwortlichkeiten.
- **REST-Konsistenz:** Die Vereinheitlichung von URL-Pfaden (`/api/recipes/recipe/{id}`) und HTTP-Statuscodes (`404 NOT FOUND` statt `200` mit `null`) sorgt für ein stabiles Zusammenspiel zwischen Client und Server.
- **Scope Creep vermeiden:** Gefundene Altlasten ausserhalb des eigentlichen Features wurden protokolliert, aber nicht im Feature-PR vermischt.
