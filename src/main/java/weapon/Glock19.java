package weapon;

import item.Weapon;

public class Glock19 extends Weapon {

    public Glock19() {
        super("Glock 19",
                "Increases attack damage by 30 while equipped.", 30);
    }

    // Flexible constructor if custom attributes are needed
    public Glock19(String name, String description, int damage) {
        super(name, description, damage);
    }

}