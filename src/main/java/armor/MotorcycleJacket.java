package armor;

import item.Armor;

public class MotorcycleJacket extends Armor {

    // Default constructor providing 50 Defense Armor
    public MotorcycleJacket() {
        super("Motorcycle Jacket", "Provides 50 Defense", 1, 50);
    }

    // Flexible constructor if custom attributes are needed
    public MotorcycleJacket(String name, String description, int requiredLevel, int defense) {
        super(name, description, requiredLevel, defense);
    }

}
