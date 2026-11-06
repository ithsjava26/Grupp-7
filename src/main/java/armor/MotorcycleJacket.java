package armor;

import item.Armor;

public class MotorcycleJacket extends Armor {

    // Default constructor providing 50 Defense Armor
    public MotorcycleJacket() {

        super("Motorcycle Jacket", "Increases defence by 50 while equipped.", 50);
    }

    // Flexible constructor if custom attributes are needed
    public MotorcycleJacket(String name, String description, int defense) {
        super(name, description, defense);
    }

}
