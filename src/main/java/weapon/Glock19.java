package weapon;

import item.Weapon;

public class Glock19 extends Weapon {

    // Creates a Glock19 with 45 base damage
    public Glock19() {
        super("Glock 19", "Increases attack damage by 45 while equipped.", 45);
    }

    // Flexible constructor if custom attributes are needed
    public Glock19(String name, String description, int damage) {
        super(name, description, damage);
    }

}




