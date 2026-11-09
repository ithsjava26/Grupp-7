package medkit;

import item.Medkit;

public class Antiseptic extends Medkit {

    // Default constructor providing 20 HP healing
    public Antiseptic() {
        super("Antiseptic", "Restores 20HP ", 20);
    }

    // Flexible constructor if custom attributes are needed
    public Antiseptic(String name, String description, int healAmount) {
        super(name, description, healAmount);
    }

}
