public class PreisberechnungTest {

    public static double calculatePrice(double baseprice, double specialprice, double extraprice, int extras, double discount) {
        double addon_discount;
        double result;
        
        if (extras >= 3) 
            addon_discount = 10;
        else if (extras >= 5) 
            addon_discount = 15;
        else 
            addon_discount = 0;
        
        if (discount > addon_discount) 
            addon_discount = discount;
        
        result = baseprice / 100.0 * (100 - discount) + specialprice 
                + extraprice / 100.0 * (100 - addon_discount);
        
        return result;
    }

    public static boolean test_calculate_price() {
        boolean test_ok = true;
        
        double result1 = calculatePrice(20000, 1000, 500, 0, 10);
        if (Math.abs(result1 - 19450.0) > 0.001) {
            System.out.println("Testfall 1 fehlgeschlagen! Ist: " + result1 + ", Soll: 19450.0");
            test_ok = false;
        } else {
            System.out.println("Testfall 1 erfolgreich.");
        }

        double result2 = calculatePrice(20000, 1000, 1000, 3, 0);
        if (Math.abs(result2 - 21900.0) > 0.001) {
            System.out.println("Testfall 2 fehlgeschlagen! Ist: " + result2 + ", Soll: 21900.0");
            test_ok = false;
        } else {
            System.out.println("Testfall 2 erfolgreich.");
        }

        // Testfall 3: 5 Extras (15% Zubehörrabatt, 0% Händlerrabatt)
        // Erwartet: 20'000 + 1'000 + 2'000 * 0.85 = 22'700.0
        double result3 = calculatePrice(20000, 1000, 2000, 5, 0);
        if (Math.abs(result3 - 22700.0) > 0.001) {
            System.out.println("Testfall 3 fehlgeschlagen! Ist: " + result3 + ", Soll: 22700.0");
            test_ok = false;
        } else {
            System.out.println("Testfall 3 erfolgreich.");
        }

        return test_ok;
    }

    public static void main(String[] args) {
        System.out.println("=== Starte Testtreiber ===");
        boolean success = test_calculate_price();
        System.out.println("Gesamtergebnis: " + (success ? "ALLE TESTS BESTANDEN" : "FEHLER IM CODE GEFUNDEN"));
    }
}