package Medkit;

import Item.Medkit;

public class Antibiotics extends Medkit {

    // Default constructor providing 30 HP healing
    public Antibiotics() {
        super("Antibiotics", "Restores 30 HP up to maximum health", 1, 30);
    }

    // Flexible constructor if custom attributes are needed
    public Antibiotics(String name, String description, int requiredLevel, int healAmount) {
        super(name, description, requiredLevel, healAmount);
    }


}


