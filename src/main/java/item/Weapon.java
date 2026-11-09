package item;

public class Weapon extends Item {
    private final int damageBonus;

    public Weapon(String name, String description, int damageBonus) {
        this(name, description, 1, damageBonus);
    }

    public Weapon(String name, String description, int requiredLevel, int damageBonus) {
        super(name, description, requiredLevel);
        this.damageBonus = damageBonus;
    }

    public int getDamageBonus() {
        return damageBonus;
    }

    @Override
    public String toString() {
        return super.toString() + " [Damage: +" + damageBonus + "]";
    }
}