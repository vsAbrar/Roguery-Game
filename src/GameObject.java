public abstract class GameObject implements Interactable {
    protected int location_x, location_y;
    protected String name;

    public GameObject(int location_x, int location_y, String name) {
        this.location_x = location_x;
        this.location_y = location_y;
        this.name = name;
    }

    public int getLocation_x() {
        return location_x;
    }

    public int getLocation_y() {
        return location_y;
    }

    public String toString() {
        return ("X Location: " + location_x + ". Y Location: " + location_y + ". Name: " + name + ".");
    }
}
