package weapon;

import item.Weapon;

public class Fireaxe extends Weapon {

    // Creates a FireAxe with 20 base damage
    public Fireaxe() {
        super("FireAxe", "Deals damage based on hit area", 20);
    }

    // Flexible constructor if custom attributes are needed
    public Fireaxe(String name, String description, int damage) {
        super(name, description, damage);
    }


}
