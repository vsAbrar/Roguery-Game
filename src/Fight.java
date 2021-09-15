import java.util.ArrayList;
import java.util.Scanner;

public class Fight {
    private boolean fightEnd = false;
    private boolean playerTurn = true;
    private int lightLevel;
    private final int DEFAULT_HEALTH = 10;
    private final int DEFAULT_ATTACK = 10;
    private Enemy e = new Enemy("Zombie", DEFAULT_HEALTH, DEFAULT_ATTACK); //default
    private final Scanner fightScan = new Scanner(System.in);
    private boolean finishScan = false;

    public Fight(boolean fightEnd, boolean playerTurn, int lightLevel) {
        this.fightEnd = fightEnd;
        this.playerTurn = playerTurn;
        e = e.randomEnemy(lightLevel);
        System.out.println(e);
    }

    public Fight(boolean fightEnd, boolean playerTurn, Enemy enemy) {
        this.fightEnd = fightEnd;
        this.playerTurn = playerTurn;
        this.e = enemy;
    }

    public Enemy getE() {
        return e;
    }

    public Player startFight(Player a) { //you can only call this on a fight object, it'll have enemy.
        while (!(this.fightEnd)) {
            if (playerTurn) {
                finishScan = false;
                //fight end if enemy health is 0.
                //prompt user with scanner, attack, heal
                while (!(finishScan)) {
                    System.out.println("Current Health: " + a.getHealth());
                    System.out.println("What do you want to do? (Attack, Heal, Bomb)"); //Run
                    String doThis = fightScan.nextLine();
                    if (doThis.equals("Attack")) {
                        finishScan = true;
                        e.setEnemyHealth(e.getEnemyHealth() - a.getAttackStat());
                        if (e.getEnemyHealth() <= 0) {
                            fightEnd = true;
                        }
                    } else if (doThis.equals("Heal")) {
                        boolean found = false;
                        for (Item i : a.getInventory()) {
                            if (i.getName().startsWith("Potion")) { //starts with PotionSmall, PotionMedium, etc.
                                a.removeFromInventory(i);
                                found = true;
                                break;
                            }
                        }
                        if (found) {
                            System.out.println("Healed.");
                            a.setHealth(a.getHealth() + 100);
                            finishScan = true; //only accept this as a turn if heal checks out
                        } else {
                            System.out.println("You have no Potions, please select another action.");
                            finishScan = false;
                        }

                    } else if (doThis.equals("Bomb")) {
                        boolean found = false;
                        for (Item i : a.getInventory()) {
                            if (i.getName().startsWith("Bomb")) { //starts with PotionSmall, PotionMedium, etc.
                                a.removeFromInventory(i);
                                found = true;
                                break;
                            }
                        }
                        if (found) {
                            System.out.println("You used a Bomb! You did " + Bomb.currDam + " damage!");
                            e.setEnemyHealth(e.getEnemyHealth() - Bomb.currDam);
                            if (e.getEnemyHealth() <= 0) {
                                fightEnd = true;
                            }
                            finishScan = true;
                        } else {
                            System.out.println("You have no Bombs, please select another action.");
                            finishScan = false;
                        }
                    } else {
                        finishScan = false;
                        System.out.println("Please enter a valid action.");
                    }
                    playerTurn = false;
                }

            } else { //enemy turn
                a.setHealth(a.getHealth() - e.getEnemyAttack());
                if (a.getHealth() <= 0) {
                    this.fightEnd = true;
                    System.out.println("You have been defeated by the enemy! You lost the game...");
                    Game.gameOver();
                } else {
                    System.out.println(e);
                    playerTurn = true;
                }
            }
        }
        System.out.println("Enemy Defeated! Current Health: " + a.getHealth());
        a.setAttackStat(a.getAttackStat() + e.getEnemyAttack() / 13);
        System.out.println("Attack increased by " + (e.getEnemyAttack() / 13) + "!");
        System.out.println("Current Attack: " + a.getAttackStat());
        return a;
    }

    /*
    public static void main(String[] args) {
        System.out.println("Fight's main method.");
        Fight b = new Fight(false, true, 10);
        //Fight c = new Fight(false, true, 10);
        ArrayList<Item> arr = new ArrayList<>(10);
        arr.add(new Potion("Potion", 50, 100));
        Player user = new Player(3, 3, "Alpha", 0, 100, 10,
                15, arr, new Weapon("Fists", 0, 0));
        //new ArrayList<Item>(10))
        user = b.startFight(user);
        //user = c.startFight(user);
    }
     */
}
