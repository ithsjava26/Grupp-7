package Medkit;

import Item.Medkit;

public class Bandage extends Medkit {

    // Default constructor providing 65 HP healing
    public Bandage() {
        super("Bandage", "Restores 65 HP up to maximum health", 1, 65);
    }

    // Flexible constructor if custom attributes are needed
    public Bandage(String name, String description, int requiredLevel, int healAmount) {
        super(name, description, requiredLevel, healAmount);
    }


}
