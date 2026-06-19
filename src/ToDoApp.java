import java.util.ArrayList;
import java.util.Scanner;

public class ToDoApp {

    public static void main(String[] args) {
        ArrayList<TodoElement> toDoListe = new ArrayList<>();
        boolean sollLaufen = true;

        while(sollLaufen) {
            String eingeleseneDaten = ToDoApp.gebeMenueAusUndLeseEingabe();
            if(eingeleseneDaten.equals("1")) {
                ToDoApp.gebeTodosAus(toDoListe);
            } else if(eingeleseneDaten.equals("2")) {
                toDoListe.add(ToDoApp.erstelleToDo());
            } else if(eingeleseneDaten.equals("3")) {
                System.out.println("Es wurde 3 eingegeben");
            } else if(eingeleseneDaten.equals("4")) {
                System.out.println("Es wurde 4 eingegeben");
            } else if(eingeleseneDaten.equals("5")) {
                sollLaufen = false;
            } else {
                System.out.println("Es wurde eine ungültige Option gewählt!");
            }
        }
    }

    public static String gebeMenueAusUndLeseEingabe() {
        System.out.println("----------To-Do-App----------");
        System.out.println("1. Alle To-Dos anzeigen");
        System.out.println("2. To-Do hinzufügen");
        System.out.println("3. To-Do löschen");
        System.out.println("4. To-Do als erledigt markieren");
        System.out.println("5. To-Do-App beenden");
        System.out.println("-----------------------------");
        System.out.println("Bitte wählen Sie eine Option:");

        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.nextLine();

        return eingabe;
    }

    public static void gebeTodosAus(ArrayList<TodoElement> todos) {
        System.out.println("-----------------------------");
        int anzahlTodos = todos.size();
        if(anzahlTodos > 0) {
            System.out.println("To-Dos:");
            for(int zaehler = 0; zaehler < anzahlTodos; zaehler++) {
                TodoElement aktuellesTodo = todos.get(zaehler);
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

    public static TodoElement erstelleToDo() {
        System.out.println("-----------------------------");
        System.out.println("Bitte geben Sie ein To-Do ein:");
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.nextLine();
        TodoElement todo = new TodoElement(eingabe);
        return todo;
    }
}
