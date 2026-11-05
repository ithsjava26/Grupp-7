package Weapon;

import Item.Weapon;

public class Glock19 extends Weapon {

    // Creates a Glock19 with 45 base damage
    public Glock19() {
        super("Glock19", "Deals damage based on hit area", 1, 45);
    }

    // Flexible constructor if custom attributes are needed
    public Glock19(String name, String description, int requiredLevel, int damage) {
        super(name, description, requiredLevel, damage);
    }

    public int calculateDamage(HitArea hitArea) {
        return getDamageBonus() * hitArea.getMultiplier();
    }


}

