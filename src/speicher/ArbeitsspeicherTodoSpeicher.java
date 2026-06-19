package speicher;

import daten.TodoElement;

import java.util.ArrayList;

public class ArbeitsspeicherTodoSpeicher implements TodoSpeicher {
    private ArrayList<TodoElement> todoElements;

    public ArbeitsspeicherTodoSpeicher() {
        todoElements = new ArrayList<>();
    }

    @Override
    public boolean istWertGueltig(int index) {
        boolean istWertGueltig = false;
        if(index >= 0 && index < todoElements.size()) {
            istWertGueltig = true;
        }

        return istWertGueltig;
    }

    @Override
    public void speichereToDo(TodoElement todoElement) {
        todoElements.add(todoElement);
    }

    @Override
    public void loescheToDo(int index) {
        if(istWertGueltig(index)) {
            todoElements.remove(index);
        } else {
            System.out.println("Der angegebene Wert ist außerhalb des gültigen Wertebereichs.");
        }
    }

    @Override
    public TodoElement holeToDo(int index) {
        TodoElement gefundenesToDo = null;
        if(istWertGueltig(index)) {
            gefundenesToDo = todoElements.get(index);
        } else {
            System.out.println("Der angegebene Wert ist außerhalb des gültigen Wertebereichs.");
        }

        return gefundenesToDo;
    }

    @Override
    public ArrayList<TodoElement> holeAlleToDos() {
        return todoElements;
    }
}
