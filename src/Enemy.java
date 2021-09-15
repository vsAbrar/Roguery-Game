import java.util.Random;

public class Enemy {
    //extended by zombie, skeleton, etc. Like pokemon fights;
    //***NOT extended, rather, those are just instances of Enemy.
    //more difficult areas should produce more difficult enemies.
    //Enemies should drop coins that allow user to buy upgrades -> increase atk, defense, etc.
    private final Random rand = new Random();
    private final String[] enemyNames = {"Zombie", "Skeleton", "Goblin", "Golem", "Witch", "Troll", "Vampire",
            "Ghost", "Serpent", "Ogre", "Werewolf"};
    private final String name;
    private int enemyHealth;
    private int enemyAttack;

    public Enemy(String name, int enemyHealth, int enemyAttack) {
        this.name = name;
        this.enemyHealth = enemyHealth;
        this.enemyAttack = enemyAttack;
    }

    public int getEnemyAttack() {
        return enemyAttack;
    }

    public void setEnemyAttack(int enemyAttack) {
        this.enemyAttack = enemyAttack;
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

    public Enemy randomEnemy(int lightLevel) {
        int randomNameIndex = rand.nextInt(enemyNames.length);
        String rName = enemyNames[randomNameIndex];
        int ranHealth = rand.nextInt(1 + lightLevel * 10) + 10 + (lightLevel * 19);
        int ranAttack = rand.nextInt(11) + (lightLevel * 2);
        return new Enemy(rName, ranHealth, ranAttack);
    }

    public String toString() {
        return "Name: " + name + " Health: " + enemyHealth + " Attack: " + enemyAttack;
    }

    /*
    public static void main(String[] args) {
        Enemy m = new Enemy("a", 2, 2);
        System.out.println(m.enemyNames.length);
    }
     */
}
