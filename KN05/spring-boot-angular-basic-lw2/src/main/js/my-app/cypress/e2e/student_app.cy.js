describe('Angular Frontend E2E Test', () => {
  it('lädt die Studentenliste unter /students', () => {
    cy.visit('http://localhost:4200/students');

    cy.contains('Patrick').should('be.visible');
  });

  it('lädt das Eingabeformular unter /addstudents', () => {
    cy.visit('http://localhost:4200/addstudents');

    cy.get('input').should('exist');
  });
});