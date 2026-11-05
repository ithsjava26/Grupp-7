package Weapon;

import Item.Weapon;

public class DesertEagle extends Weapon {

    // Creates a DesertEagle with 60 base damage
    public DesertEagle() {
        super("DesertEagle", "Deals damage based on hit area", 1, 60);
    }

    // Flexible constructor if custom attributes are needed
    public DesertEagle(String name, String description, int requiredLevel, int damage) {
        super(name, description, requiredLevel, damage);
    }

    public int calculateDamage(HitArea hitArea) {
        return getDamageBonus() * hitArea.getMultiplier();
    }


}
