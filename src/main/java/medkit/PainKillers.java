package medkit;

import item.Medkit;

public class PainKillers extends Medkit {

    // Default constructor providing 20 HP healing
    public PainKillers() {
        super("PainKillers", "Restores 20HP ", 20);
    }

    // Flexible constructor if custom attributes are needed
    public PainKillers(String name, String description, int healAmount) {
        super(name, description, healAmount);
    }

}
