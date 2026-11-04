package Medkit;

import Item.Medkit;

public class PainKillers extends Medkit {

    // Default constructor providing 20 HP healing
    public PainKillers() {
        super("PainKillers", "Restores 20 HP up to maximum health", 1, 20);
    }

    // Flexible constructor if custom attributes are needed
    public PainKillers(String name, String description, int requiredLevel, int healAmount) {
        super(name, description, requiredLevel, healAmount);
    }


}
