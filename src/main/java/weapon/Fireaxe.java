package weapon;

import item.Weapon;

public class Fireaxe extends Weapon {

    // Creates a FireAxe with 20 base damage
    public Fireaxe() {
        super("Fire Axe", "Increases attack damage by 20 while equipped.", 20);
    }

    // Flexible constructor if custom attributes are needed
    public Fireaxe(String name, String description, int damage) {
        super(name, description, damage);
    }


}
