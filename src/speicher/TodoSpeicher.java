package speicher;

import daten.TodoElement;

import java.util.ArrayList;

public interface TodoSpeicher {
    boolean istWertGueltig(int index);
    void speichereToDo(TodoElement todoElement);
    void loescheToDo(int index);
    TodoElement holeToDo(int index);
    ArrayList<TodoElement> holeAlleToDos();
}
