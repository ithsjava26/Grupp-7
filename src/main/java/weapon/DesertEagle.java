package weapon;

import item.Weapon;

public class DesertEagle extends Weapon {

    // Creates a DesertEagle with 60 base damage
    public DesertEagle() {
        super("DesertEagle", "Deals damage based on hit area", 60);
    }

    // Flexible constructor if custom attributes are needed
    public DesertEagle(String name, String description, int damage) {
        super(name, description, damage);
    }



}
