import java.security.PublicKey;
import java.util.Random;

public class Outpost extends GameObject {
    private static final Random rand = new Random();

    public Outpost(int location_x, int location_y, String name) {
        super(location_x, location_y, name);
    }

    public static Outpost randomOutpost(Map map) {
        int x = map.getSpace() - 1;
        int y = map.getSpace() - 1;
        while (!(map.isFree(x, y))) {
            x = rand.nextInt(map.getSpace());
            y = rand.nextInt(map.getSpace());
        }
        return new Outpost(x, y, "Outpost");
    }

    public Player explore(Player user) {
        System.out.println("You found an Outpost!");
        if (user.getHydration() < 15) {
            user.setHydration(15);
            System.out.println("Hydration replenished.");
        } else {
            System.out.println("You're already sufficiently Hydrated! Better save this water for later...");
        }
        if (user.getHealth() < 100) {
            user.setHealth(100);
            System.out.println("Health replenished.");
        } else {
            System.out.println("Your Health is sufficiently high. Couldn't heal...");
        }
        return user;
    }

    public static void main(String[] args) {
        //
    }
}
