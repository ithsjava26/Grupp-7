package Medkit;

import Item.Medkit;

public class FirstAidKit extends Medkit {

    // Default constructor providing 90 HP healing
    public FirstAidKit() {
        super("FirstAidKit", "Restores 90 HP up to maximum health", 1, 90);
    }

    // Flexible constructor if custom attributes are needed
    public FirstAidKit(String name, String description, int requiredLevel, int healAmount) {
        super(name, description, requiredLevel, healAmount);
    }


}
