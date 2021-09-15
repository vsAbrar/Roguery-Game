import java.util.ArrayList;
import java.util.Random;

public class Location extends GameObject {
    private final int light; //light refers to difficulty or level of cave, higher -> harder.
    private static final Random rand = new Random();
    private static final String[] locationNames = {"Cave", "Valley", "Forest", "Desert", "Swamp", "Clearing",
            "Mountain", "Tundra", "Volcano", "Tower", "Ocean"}; //Ocean,
    private final Item[] treasure;

    public Location(int location_x, int location_y, String name, int light, Item[] treasure) {
        super(location_x, location_y, name);
        this.light = light;
        this.treasure = treasure;
    }

    public static Location randomLocation(int light, Map map) {
        int randomNameIndex = rand.nextInt(locationNames.length);
        String rName = locationNames[randomNameIndex];
        int x = map.getSpace() - 1;
        int y = map.getSpace() - 1;
        while (!(map.isFree(x, y))) {
            x = rand.nextInt(map.getSpace());
            y = rand.nextInt(map.getSpace());
        }
        int roll = rand.nextInt(2);
        Item[] relay;
        if (light <= 3) {
            Item[] treasure;
            if (roll == 0) {
                treasure = new Item[]{new Potion(), new Potion()};
            } else {
                treasure = new Item[]{Weapon.randomWeapon(light), new Potion()};
            }
            relay = treasure;
        } else if (light <= 7) {
            Item[] treasure;
            if (roll == 0) {
                treasure = new Item[]{Weapon.randomWeapon(light), new Potion(), new Bomb()};
            } else {
                treasure = new Item[]{Weapon.randomWeapon(light), Weapon.randomWeapon(light)};
            }
            relay = treasure;
        } else {
            Item[] treasure;
            if (roll == 0) {
                treasure = new Item[]{Weapon.randomWeapon(light), Weapon.randomWeapon(light), new Potion()};
            } else {
                treasure = new Item[]{Weapon.randomWeapon(light), new Potion(), new Potion(), new Bomb()};
            }
            relay = treasure;
        }
        return new Location(x, y, rName, light, relay);
    }

    public String toString() {
        return (super.toString() + " light: " + light);
    }

    @Override
    public Player explore(Player user) {
        //do something that exploring would do; enters Player into location,:
        //Starts Sentence, Fight, and Treasure sequences.
        System.out.println("You begin exploring the " + name + ".");
        System.out.println("An enemy approaches!");
        Fight fight = new Fight(false, true, light);
        user = fight.startFight(user);
        System.out.println("You found treasure!");
        for (Item i : treasure) {
            System.out.println("You obtained a " + i.getName() + "! You put it in the bag.");
            user.addToInventory(i);
        }
        user.setMoney(user.getMoney() + (light * 10));
        System.out.println("You found " + (light * 10) + " coins! Current money: " + user.getMoney() + " coins.");
        System.out.println("You cleared the location!");
        return user;
    }

    /*
    public static void main(String[] args) {
        ArrayList<Item> arr = new ArrayList<>(10);
        arr.add(new Potion("Potion", 50, 100));
        Player user = new Player(3, 3, "Alpha", 0, 100, 10,
                15, arr, new Weapon("Fists", 0, 0));
        Item[] booty = {new Potion(), new Weapon("Gun", 20, 30), new Potion()};
        Location Cave = new Location(2, 2, "Cave", 5, booty);
        user = Cave.explore(user);
    }
     */
}
