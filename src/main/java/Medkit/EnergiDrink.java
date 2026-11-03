package Medkit;

import Item.Medkit;

public class EnergiDrink extends Medkit {

    // Default constructor providing 10 HP healing
    public EnergiDrink() {
        super("EnergiDrink", "Restores 10 HP up to maximum health", 1, 10);
    }

    // Flexible constructor if custom attributes are needed
    public EnergiDrink(String name, String description, int requiredLevel, int healAmount) {
        super(name, description, requiredLevel, healAmount);

    }

}