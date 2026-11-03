package Item;

public class Armor extends Item {
    private int defenseBonus;

    public Armor(String name, String description, int requiredLevel, int defenseBonus) {
        super(name, description, requiredLevel);
        this.defenseBonus = defenseBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public String toString() {
        return super.toString() + " [Defense: +" + defenseBonus + "]";
    }
}