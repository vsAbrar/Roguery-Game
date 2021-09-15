public class Potion extends Item {
    private int healAmount = 100; //for now, all pots do fullHeals

    public Potion() {
        this("Potion", 50, 100);
    }

    public Potion(String name, int sellValue, int healAmount) {
        super(name, sellValue);
        this.healAmount = healAmount;
    }

    public String toString() {
        return (super.toString() + ". Heal Amount: " + healAmount);
    }
}
