package weapon;

import item.Weapon;

public class Fireaxe extends Weapon {

    public Fireaxe() {
        super("Fire Axe",
                "Increases attack damage by 15 while equipped.", 15);
    }

    // Flexible constructor if custom attributes are needed
    public Fireaxe(String name, String description, int damage) {
        super(name, description, damage);
    }

}
