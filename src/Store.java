import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Store extends GameObject {
    private final Scanner storeScan = new Scanner(System.in);
    private final Scanner sellScan = new Scanner(System.in);
    private final Scanner buyScan = new Scanner(System.in);
    private ArrayList<Item> supply = new ArrayList<>(10);
    private static final Random rand = new Random();

    public Store(int location_x, int location_y, String name, ArrayList<Item> supply) {
        super(location_x, location_y, name);
        this.supply = supply;
    }

    public static Store randomStore(int light, Map map) {
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
                treasure = new Item[]{Weapon.randomWeapon(light), Weapon.randomWeapon(light), new Bomb()};
            } else {
                treasure = new Item[]{Weapon.randomWeapon(light), new Potion(), new Bomb(), new Potion()};
            }
            relay = treasure;
        } else if (light <= 7) {
            Item[] treasure;
            if (roll == 0) {
                treasure = new Item[]{Weapon.randomWeapon(light), new Potion(), new Potion(), new Bomb()};
            } else {
                treasure = new Item[]{new Potion(), new Potion(), new Bomb(), Weapon.randomWeapon(light)};
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
        ArrayList<Item> input = new ArrayList<>();
        for (Item i : relay) {
            input.add(i);
        }
        return new Store(x, y, "Store", input);
    }

    public ArrayList<Item> getSupply() {
        return supply;
    }

    public String storeToString() {
        String s = "Item Information: Buy/Sell Value\n";
        for (Item i : supply) {
            if (i instanceof Weapon) {
                s += i.toString();
            } else if (i instanceof Potion) {
                s += i.toString();
            } else {
                s += i.toString();
            }
            s += "\n";
        }
        return s;
    }

    public Player explore(Player user) {
        System.out.println("You have found a Temporary Store! After this session, the store will demolish itself.");
        boolean breaker = false;
        String storeOptions;
        String sellSelect = "";
        String buySelect;
        while (!breaker) {
            boolean breaker2 = false;
            boolean breaker3 = false;
            //boolean breaker4 = false;
            System.out.println("Type s to Sell, b to Buy items, w to buy 5 steps of water for 50 coins, m for current money, q to Quit.");
            storeOptions = storeScan.nextLine();
            if (storeOptions.equals("s")) {
                System.out.println("Current money: " + user.getMoney());
                if (user.getInventory().size() <= 0) {
                    System.out.println("You have no items to sell!");
                    continue;
                }
                System.out.println(user.inventoryToSellString());
                while (!breaker2) {
                    System.out.println("Type the Name of the Item you wish to sell, or q to quit.");
                    sellSelect = sellScan.nextLine();
                    if (sellSelect.equals("q")) {
                        break;
                    }
                    for (Item i : user.getInventory()) {
                        if (i.getName().equals(sellSelect)) {
                            user.setMoney(user.getMoney() + i.getSellValue());
                            System.out.println("You sold " + i.getName() + " for " + i.getSellValue());
                            System.out.println("Current Money: " + user.getMoney());
                            if (user.getEquipped().equals(i)) {
                                user.setEquipped(new Weapon("Fists", 0, 0));
                                System.out.println("Notice: You have sold your equipped weapon!");
                            }
                            user.removeFromInventory(i);
                            breaker2 = true;
                            break;
                        }
                    }
                    if (!breaker2) {
                        System.out.println("Please enter a valid Item Name or q to Quit.");
                    }
                }
            } else if (storeOptions.equals("b")) {
                System.out.println("Current money: " + user.getMoney());
                if (this.supply.size() <= 0) {
                    System.out.println("There are no more items left to buy!");
                    continue;
                }
                System.out.println(this.storeToString());
                while (!breaker3) {
                    System.out.println("Type the Name of the Item you wish to buy, or q to quit.");
                    buySelect = buyScan.nextLine();
                    if (buySelect.equals("q")) {
                        break;
                    }
                    for (Item i : this.supply) {
                        if (i.getName().equals(buySelect)) {
                            if (user.getMoney() >= i.getSellValue()) {
                                System.out.println("You have bought the " + i.getName() + " for " + i.getSellValue() + ".");
                                user.addToInventory(i);
                                user.setMoney(user.getMoney() - i.getSellValue());
                                this.supply.remove(i);
                                breaker3 = true;
                                break;
                            } else {
                                System.out.println("You do not have enough money to buy this item.");
                                break;
                            }
                        }
                    }
                    if (!breaker3) {
                        System.out.println("Please enter a valid Item Name or q to Quit.");
                    }
                }
            } else if (storeOptions.equals("w")) {
                if (user.getMoney() >= 50) {
                    user.setMoney(user.getMoney() - 50);
                    user.setHydration(user.getHydration() + 5);
                    System.out.println("You have bought enough water for 5 steps.");
                    System.out.println("Current hydration: " + user.getHydration() + ". Current money: "
                            + user.getMoney());
                } else {
                    System.out.println("You do not have enough money to buy water.");
                }
            } else if (storeOptions.equals("m")) {
                System.out.println("Current money: " + user.getMoney());
            } else if (storeOptions.equals("q")) {
                break;
            } else {
                System.out.println("Please enter a valid action.");
            }
        }
        return user;
    }
}
