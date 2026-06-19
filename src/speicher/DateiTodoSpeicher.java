package speicher;

import daten.TodoElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class DateiTodoSpeicher implements TodoSpeicher {
    private Path todoDatei;

    public DateiTodoSpeicher() {
        todoDatei = Paths.get(System.getProperty("user.home"), "todos.td");
        if(!Files.exists(todoDatei)) {
            try {
                Files.createFile(todoDatei);
            } catch (IOException e) {
                System.out.println("Die Datei konnte nicht erstellt werden.");
            }
        }
    }

    private String todoElementZuString(TodoElement todoElement) {
        return String.format("%s|%b", todoElement.getName(), todoElement.getErledigt());
    }

    private TodoElement stringZuTodoElement(String string) {
        String[] geschnittenerString = string.split("\\|");
        TodoElement todoElement = new TodoElement(geschnittenerString[0]);
        if(Boolean.parseBoolean(geschnittenerString[1])) {
            todoElement.alsErledigtMarkieren();
        }
        return todoElement;
    }

    @Override
    public void speichereToDo(TodoElement todoElement) {
        try {
            String inhalt = String.format("%s%s", todoElementZuString(todoElement), System.lineSeparator());
            Files.writeString(todoDatei, inhalt, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Das To-Do konnte nicht gespeichert werden.");
        }
    }

    @Override
    public ArrayList<TodoElement> holeAlleToDos() {
        ArrayList<TodoElement> todos = new ArrayList<>();
        try {
            List<String> zeilen = Files.readAllLines(todoDatei);
            for(String zeile : zeilen) {
                todos.add(stringZuTodoElement(zeile));
            }
        } catch (IOException e) {
            System.out.println("Die Datei konnte nicht gelesen werden");
        }
        return todos;
    }

    @Override
    public boolean istWertGueltig(int index) {
        ArrayList<TodoElement> todos = holeAlleToDos();
        boolean istWertGueltig = false;
        if(index >= 0 && index < todos.size()) {
            istWertGueltig = true;
        }
        return istWertGueltig;
    }

    @Override
    public TodoElement holeToDo(int index) {
        TodoElement gefundenesToDo = null;
        if(istWertGueltig(index)) {
            gefundenesToDo = holeAlleToDos().get(index);
        } else {
            System.out.println("Der angegebene Wert ist außerhalb des gültigen Wertebereichs.");
        }
        return gefundenesToDo;
    }

    @Override
    public void loescheToDo(int index) {
        if(istWertGueltig(index)) {
            ArrayList<TodoElement> todos = holeAlleToDos();
            todos.remove(index);
            try {
                Files.writeString(todoDatei, "", StandardOpenOption.TRUNCATE_EXISTING);
                for (TodoElement todo : todos) {
                    speichereToDo(todo);
                }
            } catch (IOException e) {
                System.out.println("Das To-Do konnte nicht gelöscht werden.");
            }
        } else {
            System.out.println("Der angegebene Wert ist außerhalb des gültigen Wertebereichs.");
        }
    }

    @Override
    public void aktualisiereToDo(int index, TodoElement todoElement) {
        if(istWertGueltig(index)) {
            ArrayList<TodoElement> todos = holeAlleToDos();
            todos.set(index, todoElement);
            try {
                Files.writeString(todoDatei, "", StandardOpenOption.TRUNCATE_EXISTING);
                for (TodoElement todo : todos) {
                    speichereToDo(todo);
                }
            } catch (IOException e) {
                System.out.println("Das To-Do konnte nicht aktualisiert werden.");
            }
        } else {
            System.out.println("Der angegebene Wert ist außerhalb des gültigen Wertebereichs.");
        }
    }
}
