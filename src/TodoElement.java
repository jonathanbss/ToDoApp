public class TodoElement {
    private String name;
    private boolean erledigt;

    public TodoElement(String name) {
        erledigt = false;
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String neuerName) {
        name = neuerName;
    }

    public boolean getErledigt() {
        return erledigt;
    }

    public void setErledigt(boolean istErledigt) {
        erledigt = istErledigt;
    }

    public void alsErledigtMarkieren() {
        erledigt = true;
    }
}
