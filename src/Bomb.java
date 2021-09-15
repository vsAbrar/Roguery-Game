public class Bomb extends Item {
    private int damageAmount = 150; //all bombs do 150 damage
    static int currDam = 150;

    public Bomb(String name, int sellValue, int damageAmount) {
        super(name, sellValue);
        this.damageAmount = damageAmount;
    }

    public Bomb() {
        this("Bomb", 50, 150);
    }

    public String toString() {
        return (super.toString() + ". Damage Amount: " + this.damageAmount);
    }

    public static void main(String[] args) {
        //
    }
}