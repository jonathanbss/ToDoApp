import java.util.ArrayList;
import java.util.Scanner;

public class ToDoAppLogik {

    private ArrayList<TodoElement> toDoListe;
    private Scanner scanner;

    public ToDoAppLogik() {
        toDoListe = new ArrayList<TodoElement>();
        scanner = new Scanner(System.in);
    }

    private String gebeMenueAusUndLeseEingabe() {
        System.out.println("----------To-Do-App----------");
        System.out.println("1. Alle To-Dos anzeigen");
        System.out.println("2. To-Do hinzufügen");
        System.out.println("3. To-Do löschen");
        System.out.println("4. To-Do als erledigt markieren");
        System.out.println("5. To-Do-App beenden");
        System.out.println("-----------------------------");
        System.out.println("Bitte wählen Sie eine Option:");

        String eingabe = scanner.nextLine();

        return eingabe;
    }

    private void gebeTodosAus() {
        System.out.println("-----------------------------");
        int anzahlTodos = toDoListe.size();
        if(anzahlTodos > 0) {
            System.out.println("To-Dos:");
            for(int zaehler = 0; zaehler < anzahlTodos; zaehler++) {
                TodoElement aktuellesTodo = toDoListe.get(zaehler);
                String status = "";
                if(aktuellesTodo.getErledigt()) {
                    status = "Erledigt";
                } else {
                    status = "Offen";
                }
                System.out.println(String.format("%d. %s - %s", zaehler + 1, aktuellesTodo.getName(), status));
            }
        } else {
            System.out.println("Es sind keine To-Dos vorhanden.");
        }
        System.out.println("-----------------------------");
    }

    private TodoElement erstelleToDo() {
        System.out.println("-----------------------------");
        System.out.println("Bitte geben Sie ein To-Do ein:");
        String eingabe = scanner.nextLine();
        TodoElement todo = new TodoElement(eingabe);
        return todo;
    }

    private void loescheToDo() {
        System.out.println("-----------------------------");
        System.out.println("Bitte geben Sie die Nummer des zu löschenden To-Dos ein:");

        int eingegebeneZahl = this.leseZahl();
        eingegebeneZahl = eingegebeneZahl - 1;
        if(eingegebeneZahl >= 0 && eingegebeneZahl <= toDoListe.size()-1) {
            TodoElement aktuellesTodo = toDoListe.get(eingegebeneZahl);
            System.out.println(String.format("Möchten Sie das To-Do mit dem Namen '%s' wirklich löschen? J/N", aktuellesTodo.getName()));
            String antwort = scanner.nextLine();
            switch(antwort) {
                case "j":
                case "J":
                    toDoListe.remove(eingegebeneZahl);
                    System.out.println("Das To-Do wurde gelöscht.");
                    break;
                case "n":
                case "N":
                    System.out.println("Der Löschvorgang wurde abgebrochen.");
                    break;
                default:
                    System.out.println("Ihre Antwort war ungültig.");
                    break;
            }
        } else {
            System.out.println("Es gibt kein To-Do mit dieser Nummer.");
        }
    }

    private void erledigeToDo() {
        System.out.println("-----------------------------");
        System.out.println("Bitte geben Sie die Nummer des To-Dos ein, das als erledigt markiert werden soll:");
        int eingegebeneZahl = this.leseZahl();

        eingegebeneZahl = eingegebeneZahl - 1;
        if(eingegebeneZahl >= 0 && eingegebeneZahl <= toDoListe.size()-1) {
            TodoElement gefundenesTodo = toDoListe.get(eingegebeneZahl);
            gefundenesTodo.alsErledigtMarkieren();
        } else {
            System.out.println("Es gibt kein To-Do mit dieser Nummer.");
        }
    }

    public void starteToDoApp() {
        boolean sollLaufen = true;

        while(sollLaufen) {
            String eingeleseneDaten = this.gebeMenueAusUndLeseEingabe();
            if(eingeleseneDaten.equals("1")) {
                this.gebeTodosAus();
            } else if(eingeleseneDaten.equals("2")) {
                toDoListe.add(this.erstelleToDo());
            } else if(eingeleseneDaten.equals("3")) {
                this.loescheToDo();
            } else if(eingeleseneDaten.equals("4")) {
                this.erledigeToDo();
            } else if(eingeleseneDaten.equals("5")) {
                sollLaufen = false;
            } else {
                System.out.println("Es wurde eine ungültige Option gewählt!");
            }
        }
    }

    private int leseZahl() {
        int eingabe = 0;
        boolean eingabeGueltig = false;

        do {
            try {
                eingabe = Integer.parseInt(scanner.nextLine());
                eingabeGueltig = true;
            } catch (NumberFormatException e) {
                System.out.println("Die Eingabe ist keine Zahl.");
                eingabeGueltig = false;
            }
        } while (!eingabeGueltig);
        return eingabe;
    }
}
