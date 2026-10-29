package combat;

/**
 * Represents an abstract game character with attributes for health,
 * attack, and defense as well as functionalities to manage those attributes.
 * Subclasses can extend this class to create specific types of game characters.
 */
public abstract class GameCharacter {
        private final String name;
        private int health;
        private final int maxHealth;
        private final int attack;
        private final int defense;
        private AttackStrategy attackStrategy;

    protected GameCharacter(String name, int health, int maxHealth, int attack, int defense, AttackStrategy attackStrategy) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can not be null or empty");
        }

        if (maxHealth <= 0) {
            throw new IllegalArgumentException("maxHealth must be greater than 0");
        }
        if (attackStrategy == null) {
            throw new IllegalArgumentException("atatckStrategy can not be null");
        }
        this.name = name;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.health = Math.max(0, Math.min(health, maxHealth));
        this.attackStrategy = attackStrategy;
    }

    public  String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
    public boolean isAlive() {
        return health > 0;
    }


    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        if (attackStrategy == null) {
            throw new IllegalArgumentException("attackStrategy can not be null");
        }
        this.attackStrategy = attackStrategy;
    }
    public void takeDamage(int amount) {
     if (amount < 0) {
         throw new IllegalArgumentException("Damage can not be negative");
     }
     health = Math.max(0, health - amount);
    }
    public void heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Healing amount can not be negative");
        }
        health = (int) Math.min((long) maxHealth, (long) health + amount);
    }
}