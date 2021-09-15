import java.util.ArrayList;
import java.util.Random;

public class Stage {
    private final int lightLevel; //difficulty level
    private final Player user;
    private final Map base;
    private final Random rand = new Random();

    public Stage(Player user, Map base, int lightLevel) { //Player user, Map first
        // think about randomizing size of map too!!
        //(rand.nextInt(2) + lightLevel, base));
        this.user = user;
        this.base = base;
        this.lightLevel = lightLevel;

        base.addToMap(user, user.getLocation_x(), user.getLocation_y());
        base.addToMap(Location.randomLocation(lightLevel, base));
        base.addToMap(Location.randomLocation(lightLevel, base));
        base.addToMap(Location.randomLocation(lightLevel, base));
        base.addToMap(Outpost.randomOutpost(base));
        base.addToMap(Outpost.randomOutpost(base));
        base.addToMap(Store.randomStore(rand.nextInt(4), base));
        base.addToMap(Store.randomStore(rand.nextInt(4), base));
        System.out.println(base.toString());
        base.moving();
    }

    public int getLightLevel() {
        return lightLevel;
    }

    public Map getBase() {
        return base;
    }

    public Player getUser() {
        return user;
    }

}
