package medkit;

import item.Medkit;

public class PainKillers extends Medkit {

    // Default constructor providing 20 HP healing
    public PainKillers() {
        super("Painkillers", "Restores 5 HP and permanently increases maximum HP by 5.", 5);
    }

    // Flexible constructor if custom attributes are needed
    public PainKillers(String name, String description, int healAmount) {
        super(name, description, healAmount);
    }

}
