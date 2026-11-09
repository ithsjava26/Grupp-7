package armor;

import item.Armor;

public class MotorcycleJacket extends Armor {

    // Default constructor providing 10 Defense Armor
    public MotorcycleJacket() {
        super("Motorcycle Jacket",
                "Increases defence by 10 while equipped.", 10);
    }

    // Flexible constructor if custom attributes are needed
    public MotorcycleJacket(String name, String description, int defense) {
        super(name, description, defense);
    }

}
