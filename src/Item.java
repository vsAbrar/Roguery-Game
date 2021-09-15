public abstract class Item implements Comparable<Item> {
    private final String name;
    private final int sellValue;

    public Item(String name, int sellValue) {
        this.name = name;
        this.sellValue = sellValue;
    }

    public int getSellValue() {
        return sellValue;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return ("Item Name: " + name + ". Value: " + sellValue);
    }

    public int compareTo(Item i) {
        return ((this.name).compareTo(i.getName()));
    }
}
