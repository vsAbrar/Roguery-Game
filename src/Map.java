import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

//X and Y are inverted!!! origin is top left;
public class Map {
    //An ArrayList of GameObjects (type). This array, when printed, will be
    //the base map of the game.
    private final GameObject[][] base;
    Random rand = new Random();
    private final Scanner scanMove = new Scanner(System.in);
    private final Scanner scanEquip = new Scanner(System.in);
    private final int space;

    public Map(int space) {
        this.space = space;
        base = new GameObject[space][space];
        //this.base = base;
    }

    public boolean isFree(int x, int y) {
        return base[x][y] == null;
    }

    public void moving() {
        int counter;
        while (!(base[current_player_x()][current_player_y()] instanceof Location)) {
            counter = 0;
            for (GameObject[] innerArr : base) {
                for (GameObject loc : innerArr) {
                    if (loc instanceof Location) {
                        counter++;
                    }
                }
            }
            if (counter > 1) {
                System.out.println("There are " + counter + " Locations remaining!");
            } else if (counter == 1) {
                System.out.println("Only " + counter + " Location remains!");
            } else {
                System.out.println("You have cleared all of the Locations!");
                //System.out.println("Congratulations, you have cleared the stage!");
            }

            if (counter == 0) {
                //Game.gameOver(); //ENDS GAME.
                break;
            }
            System.out.println("Use wasd to move. Type p for player status, and e to equip your weapon.\n" +
                    "Type l for legend, m to reload the map, q to quit.");
            String moveHere = scanMove.nextLine();
            if (moveHere.equals("w")) {
                moveUp();
            } else if (moveHere.equals("a")) {
                moveLeft();
            } else if (moveHere.equals("s")) {
                moveDown();
            } else if (moveHere.equals("d")) {
                moveRight();
            } else if (moveHere.equals("l")) {
                System.out.println("\nLegend:\n" + "A: Player, O: Outpost, S: Temporary Shop. Other letters are location names.\n" +
                        "Notice! Some location names may appear as Outposts or Shops... make sure you're prepared!\n");
            } else if (moveHere.equals("m")) {
                System.out.println();
                System.out.println(this);
            } else if (moveHere.equals("p")) {
                System.out.println("\nPlayer Information:");
                System.out.println(base[current_player_x()][current_player_y()].toString());
                System.out.println();
            } else if (moveHere.equals("e")) {
                System.out.println();
                if (((Player) (base[current_player_x()][current_player_y()])).currentWeapons().size() >= 1) {
                    System.out.println("Weapon currently equipped: "
                            + ((Player) (base[current_player_x()][current_player_y()])).getEquipped().getName());
                    System.out.println("Current Weapons: ");
                    System.out.println(((Player) (base[current_player_x()][current_player_y()])).currentWeapons().toString());
                    boolean breaker = false;
                    String equipper;
                    while (!breaker) {
                        System.out.println("Type the name of the weapon you wish to equip, or q to quit.");
                        equipper = scanEquip.nextLine();
                        for (Weapon w : ((Player) (base[current_player_x()][current_player_y()])).currentWeapons()) {
                            if (equipper.equals(w.getName())) {
                                ((Player) (base[current_player_x()][current_player_y()])).setEquipped(w);
                                System.out.println("You have equipped: " + w.getName());
                                System.out.println("Current attack: "
                                        + ((Player) (base[current_player_x()][current_player_y()])).getAttackStat() + "\n");
                                breaker = true;
                                break;
                            }
                        }
                        if (breaker) {
                            break;
                        }
                        if (equipper.equals("q")) {
                            break;
                        }
                        System.out.println("Please enter a valid weapon in your list.");
                    }
                } else {
                    System.out.println("You currently have no weapons.");
                }
            } else if (moveHere.equals("q")) {
                Game.gameOver(); //end game instantly, quit all other methods and execute final command.
            } else {
                System.out.println("Please enter a valid option.");
            }
        }
    }

    public void addToMap(GameObject o, int xLocation, int yLocation) {
        base[xLocation][yLocation] = o;
    }

    public void addToMap(GameObject o) {
        base[o.getLocation_x()][o.getLocation_y()] = o;
    }

    public String toString() {
        String s = "";
        for (int row = 0; row < base.length; row++) {
            s += "|";
            for (int col = 0; col < base[row].length; col++) {
                if (base[row][col] == null) {
                    s += "#|";
                } else {
                    s += base[row][col].name.toUpperCase(Locale.ROOT).charAt(0);// first letter, fully capped, from name.
                    s += "|";
                }
            }
            s += "\n";
        }
        return s;
    }

    public int getSpace() {
        return space;
    }

    public int current_player_x() {
        for (int row = 0; row < base.length; row++) {
            for (int col = 0; col < base[row].length; col++) {
                if (base[row][col] instanceof Player) {
                    return base[row][col].location_x;
                }
            }
        }
        return -1;
    }

    public int current_player_y() {
        for (int row = 0; row < base.length; row++) {
            for (int col = 0; col < base[row].length; col++) {
                if (base[row][col] instanceof Player) {
                    return base[row][col].location_y;
                }
            }
        }
        return -1;
    }

    public void moveRight() {
        if ((current_player_y() + 1) < base[0].length) {
            //((Player) base[current_player_x()][current_player_y()]).location_y = (current_player_y() + 1);
            Player placeholder = (Player) base[current_player_x()][current_player_y()];
            //**before
            //we try to access current player stuff when we just deleted it...
            if (base[current_player_x()][current_player_y() + 1] == null) {
                base[current_player_x()][current_player_y()] = null; //current loc is null;
                placeholder.decrementHyd();
                addToMap(placeholder, placeholder.location_x, placeholder.location_y + 1);
                placeholder.location_y = placeholder.location_y + 1;
                System.out.println(placeholder);
                System.out.println(this);
            } else {
                //trigger location event; and then add Player.
                base[current_player_x()][current_player_y() + 1].explore(placeholder);
                base[current_player_x()][current_player_y()] = null; //current loc is null;
                placeholder.decrementHyd();
                addToMap(placeholder, placeholder.location_x, placeholder.location_y + 1);
                placeholder.location_y = placeholder.location_y + 1;
                System.out.println(placeholder);
                System.out.println(this);
            }
        } else {
            System.out.println("A mist keeps you at bay. You cannot advance.");
        }
    }

    public void moveLeft() {
        if ((current_player_y() - 1) > -1) {
            //((Player) base[current_player_x()][current_player_y()]).location_y = (current_player_y() + 1);
            Player placeholder = (Player) base[current_player_x()][current_player_y()];
            //**before
            //we try to access current player stuff when we just deleted it...
            if (base[current_player_x()][current_player_y() - 1] == null) {
                base[current_player_x()][current_player_y()] = null; //current loc is null;
                placeholder.decrementHyd();
                addToMap(placeholder, placeholder.location_x, placeholder.location_y - 1);
                placeholder.location_y = placeholder.location_y - 1;
                System.out.println(placeholder);
                System.out.println(this);
            } else {
                //trigger location event; and then add Player.
                base[current_player_x()][current_player_y() - 1].explore(placeholder);
                base[current_player_x()][current_player_y()] = null; //current loc is null;
                placeholder.decrementHyd();
                addToMap(placeholder, placeholder.location_x, placeholder.location_y - 1);
                placeholder.location_y = placeholder.location_y - 1;
                System.out.println(placeholder);
                System.out.println(this);
            }
        } else {
            System.out.println("A mist keeps you at bay. You cannot advance.");
        }
    }

    public void moveUp() {
        if ((current_player_x() - 1) > -1) {
            //((Player) base[current_player_x()][current_player_y()]).location_y = (current_player_y() + 1);
            Player placeholder = (Player) base[current_player_x()][current_player_y()];
            //**before
            //we try to access current player stuff when we just deleted it...
            if (base[current_player_x() - 1][current_player_y()] == null) {
                base[current_player_x()][current_player_y()] = null; //current loc is null;
                placeholder.decrementHyd();
                addToMap(placeholder, placeholder.location_x - 1, placeholder.location_y);
                placeholder.location_x = placeholder.location_x - 1;
                System.out.println(placeholder);
                System.out.println(this);
            } else {
                //trigger location event; and then add Player.
                base[current_player_x() - 1][current_player_y()].explore(placeholder);
                base[current_player_x()][current_player_y()] = null; //current loc is null;
                placeholder.decrementHyd();
                addToMap(placeholder, placeholder.location_x - 1, placeholder.location_y);
                placeholder.location_x = placeholder.location_x - 1;
                System.out.println(placeholder);
                System.out.println(this);
            }
        } else {
            System.out.println("A mist keeps you at bay. You cannot advance in this direction.");
        }
    }

    public void moveDown() {
        if ((current_player_x() + 1) < base.length) {
            //((Player) base[current_player_x()][current_player_y()]).location_y = (current_player_y() + 1);
            Player placeholder = (Player) base[current_player_x()][current_player_y()];
            //**before
            //we try to access current player stuff when we just deleted it...
            if (base[current_player_x() + 1][current_player_y()] == null) {
                base[current_player_x()][current_player_y()] = null; //current loc is null;
                placeholder.decrementHyd();
                addToMap(placeholder, placeholder.location_x + 1, placeholder.location_y);
                placeholder.location_x = placeholder.location_x + 1;
                System.out.println(placeholder);
                System.out.println(this);
            } else {
                //trigger location event; and then add Player.
                base[current_player_x() + 1][current_player_y()].explore(placeholder);
                base[current_player_x()][current_player_y()] = null; //current loc is null;
                placeholder.decrementHyd();
                addToMap(placeholder, placeholder.location_x + 1, placeholder.location_y);
                placeholder.location_x = placeholder.location_x + 1;
                System.out.println(placeholder);
                System.out.println(this);
            }

        } else {
            System.out.println("A mist keeps you at bay. You cannot advance.");
        }
    }
}
