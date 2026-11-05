package medkit;

import item.Medkit;

public class Bandage extends Medkit {

    // Default constructor providing 20 HP healing
    public Bandage() {
        super("Bandage", "Restores 20 HP up to maximum health", 20);
    }

    // Flexible constructor if custom attributes are needed
    public Bandage(String name, String description, int healAmount) {
        super(name, description, healAmount);
    }

}
