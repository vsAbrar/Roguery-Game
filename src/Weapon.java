import java.util.Random;

public class Weapon extends Item {
    public int damage;
    private static final Random rand = new Random();
    private static final String[] weaponNames = {"Scythe", "Sword", "Revolver", "Sniper", "Spear", "Dagger", "Bazooka",
            "Flamethrower", "Bow", "Club", "Rifle"};
    private static final int UPPER_RANDOM_BOUND = 20;
    private static final int LIGHT_WEAPON_MULTIPLIER = 3;
    private static final double RANDOM_DAMAGE_DIVISOR = 1.3;

    public Weapon(String name, int sellValue, int damage) {
        super(name, sellValue);
        this.damage = damage;
    }

    public Weapon() {
        this("Fist", 0, 0);
    } //default weapon.

    public String toString() {
        return (super.toString() + ". Damage: " + damage);
    }

    public int getDamage() {
        return damage;
    }

    public static Weapon randomWeapon(int light) {
        int randomNameIndex = rand.nextInt(weaponNames.length);
        String rName = weaponNames[randomNameIndex];
        int ranDamage = light * LIGHT_WEAPON_MULTIPLIER + rand.nextInt(UPPER_RANDOM_BOUND);
        int ranValue = ((int) (ranDamage / RANDOM_DAMAGE_DIVISOR)) + rand.nextInt(UPPER_RANDOM_BOUND + 10);
        return new Weapon(rName, ranValue, ranDamage);
    }

    public boolean equals(Item i) {
        if (!(i instanceof Weapon)) {
            return false;
        }
        Weapon c = (Weapon) i;
        return (c.getName().equals(this.getName()) && (c.getSellValue() == this.getSellValue())
                && (c.getDamage() == this.getDamage()));
    }
}
