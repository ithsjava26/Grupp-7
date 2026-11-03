package Medkit;

import Item.Medkit;

public class Antiseptic extends Medkit {

    // Default constructor providing 15 HP healing
    public Antiseptic() {
        super("Antiseptic", "Restores 15 HP up to maximum health", 1, 15);
    }

    // Flexible constructor if custom attributes are needed
    public Antiseptic(String name, String description, int requiredLevel, int healAmount) {
        super(name, description, requiredLevel, healAmount);
    }


}
