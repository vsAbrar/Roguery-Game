import java.util.ArrayList;
import java.util.Random;

public class Game {
    private final String playerName = "Player";
    private final ArrayList<Item> initialInventory = new ArrayList<>();
    private Player user;
    private Map gameMap;
    private final Random rand = new Random();
    private final int MAP_SPACE = 6; //can be any number above 3, less than 10.
    private static final int DEF_BOSS_HEALTH = 200;


    public Game() {
        Map base = new Map(MAP_SPACE);
        ArrayList<Item> starter = new ArrayList<>(10);
        starter.add(new Potion("Potion", 50, 100));
        Player user = new Player(rand.nextInt(base.getSpace()), rand.nextInt(base.getSpace()), "Alpha", 0,
                100, 10, 15, starter, new Weapon());
        Stage one = new Stage(user, base, 3);

        System.out.println("\n" + "You have cleared the first stage! You found treasure!" + "\n");
        one.getUser().addToInventory(new Bomb());
        one.getUser().addToInventory(new Potion());
        one.getUser().setMoney(user.getMoney() + 50);
        System.out.println("With a threatening aura, the stage's boss approaches...");

        Fight bossOne = new Fight(false, true, new Enemy("Dragon", DEF_BOSS_HEALTH, 30));
        //bossOne.startFight(one.getUser());
        System.out.println(bossOne.getE().toString());

        Map base2 = new Map(8);
        Stage two = new Stage(bossOne.startFight(one.getUser()), base2, 7);
        System.out.println("\n" + "You have cleared the stage! You found treasure!" + "\n");
        two.getUser().addToInventory(new Potion());
        two.getUser().addToInventory(new Weapon("Dragon Breather", 100, 50));
        two.getUser().setMoney(user.getMoney() + 75);
        System.out.println("With a menacing aura, the stage's boss approaches...");

        Fight bossTwo = new Fight(false, true, new Enemy("Dragon", DEF_BOSS_HEALTH*2, 40));
        System.out.println(bossTwo.getE().toString());

        Map base3 = new Map(10);
        Stage three = new Stage(bossTwo.startFight(two.getUser()), base3, 10);
        System.out.println("\nYou have cleared the final stage!\n");
        System.out.println("The final boss approaches with a terrifying aura!");

        Fight bossFinal = new Fight(false, true, new Enemy("Dragon", DEF_BOSS_HEALTH*3, 40));
        System.out.println(bossFinal.getE().toString());
        bossFinal.startFight(three.getUser());
        Game.gameVictory();
    }

    public Player getUser() {
        return this.user;
    }

    public Map getGameMap() {
        return gameMap;
    }

    public static void gameOver() {
        //end entire game, show game over screen.
        System.out.println("\nYou Lost, Game Over!! Don't give up yet...");
        System.exit(0);
    }

    public static void gameVictory() {
        //end entire game, show game victory screen.
        System.out.println("\nCongratulations!! You've won the game! Thanks for playing!");
        System.exit(0);
    }

    public static void main(String[] args) {
        new Game();
        /* Map a = new Map();
        ArrayList<Item> arr = new ArrayList<>(10);
        arr.add(new Potion("Potion", 50, 100));
        Player user = new Player(3, 3, "Alpha", 0, 100, 10,
                15, arr, new Weapon("Fists", 0, 0));
        a.addToMap(user, 3, 3);
        Item[] booty = {new Potion(), new Weapon("Gun", 20, 30), new Potion()};
        Item[] booty2 = {new Potion(), new Weapon("Scythe", 15, 23)};
        Location Cave = new Location(2, 2, "Cave", 5, booty);
        Location Wave = new Location(5, 5, "Wave", 5, booty2);
        Outpost home1 = new Outpost(0, 0,"Home");
        Outpost home2 = new Outpost(6, 6,"Home");
        Store store1 = new Store(1, 2, "Store", new ArrayList<>(10));
        Store store2 = new Store(2, 1, "Store", new ArrayList<>(10));
        store1.getSupply().add(new Weapon("Bazooka", 100, 40));
        store1.getSupply().add(new Potion());
        store2.getSupply().add(new Weapon("Sniper", 25, 25));
        store2.getSupply().add(new Weapon("Bat", 15, 10));
        a.addToMap(home2, 6, 6);
        a.addToMap(home1, 0, 0);
        a.addToMap(Cave, 2, 2);
        a.addToMap(Wave, 5, 5);
        a.addToMap(store1, 1, 2);
        a.addToMap(store2, 2, 1);
        System.out.println(a.toString());
        a.moving(); */
    }
}
