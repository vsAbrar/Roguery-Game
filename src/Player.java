import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player extends GameObject {
    private int money;
    private int health;
    private int attackStat;
    private int hydration;
    private final ArrayList<Item> inventory;
    private Weapon equipped;

    public Player(int location_x, int location_y, String name, int money, int health, int attackStat,
                  int hydration, ArrayList<Item> inventory, Weapon equipped) {
        super(location_x, location_y, name);
        this.money = money;
        this.health = health;
        this.attackStat = attackStat;
        this.hydration = hydration;
        this.inventory = inventory;
        this.equipped = equipped;

    }

    public Player explore(Player user) {
        System.out.println("Meditating...");
        return user;
    }

    public void setEquipped(Weapon updatedWeapon) {
        this.attackStat = attackStat - this.equipped.getDamage();
        this.equipped = updatedWeapon;
        this.attackStat = attackStat + updatedWeapon.getDamage();
    }

    public ArrayList<Weapon> currentWeapons() {
        ArrayList<Weapon> weaponList = new ArrayList<>(10);
        for (Item i : this.getInventory()) {
            if (i instanceof Weapon) {
                weaponList.add((Weapon) i);
            }
        }
        return weaponList;
    }

    public Weapon getEquipped() {
        return equipped;
    }

    public int getHydration() {
        return hydration;
    }

    public void setHydration(int hydration) {
        this.hydration = hydration;
    }

    public void decrementHyd() {
        this.hydration = this.hydration - 1;
        if (this.hydration == 0) {
            System.out.println("You have run out of water! You collapse and faint...");
            Game.gameOver();
        } else if (this.hydration <= 5) {
            System.out.println("You are low on water! Replenish hydration at an Outpost or buy water at the Shop!");
        }
    }

    public void addToInventory(Item i) {
        //for treasure chests, loop through array and add each element of array to this array with method.
        inventory.add(i);
        Collections.sort(inventory);
    }

    public void removeFromInventory(Item i) {
        inventory.remove(i);
        Collections.sort(inventory);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public String inventoryToSellString() {
        String s = "Item Name: Sell Value\n";
        for (Item i : inventory) {
            s += i.getName() + ": " + i.getSellValue() + "\n";
        }
        return s;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setAttackStat(int attackStat) {
        this.attackStat = attackStat;
    }

    public int getAttackStat() {
        return attackStat;
    }

    public void setLocation(int locationX, int locationY) {
        this.location_x = locationX;
        this.location_y = locationY;
    }

    @Override
    public int getLocation_x() {
        return super.getLocation_x();
    }

    @Override
    public int getLocation_y() {
        return super.getLocation_y();
    }

    public String toString() {
        String s = "";
        if (inventory == null) {
            s = "empty";
        } else {
            for (Item i : inventory) {
                s += i.getName() + ". "; //instead of toString for each item, use name.
            }
        }
        return (super.toString() + " Money: " + money + ". Health: " + health + ". Attack: " + attackStat
                + ". Hydration: " + hydration + ". Inventory: " + s);
    }
}
