package item;

public class Weapon extends Item {
    private int damageBonus;

    public Weapon(String name, String description, int damageBonus) {
        super(name, description);
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