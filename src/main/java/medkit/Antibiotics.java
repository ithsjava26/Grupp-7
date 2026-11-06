package medkit;

import item.Medkit;

public class Antibiotics extends Medkit {

    // Default constructor providing 20 HP healing
    public Antibiotics() {
        super("Antibiotics", "Restores 20 HP ", 20);
    }

    // Flexible constructor if custom attributes are needed
    public Antibiotics(String name, String description, int healAmount) {
        super(name, description, healAmount);
    }


}


