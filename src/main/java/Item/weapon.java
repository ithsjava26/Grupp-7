package Item;

public class weapon extends Item {
    private int damageBonus;

    public weapon(String name, String description, int requiredLevel, int damageBonus) {
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