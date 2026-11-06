package medkit;

import item.Medkit;

public class PainKillers extends Medkit {

    // Default constructor providing 20 HP healing
    public PainKillers() {
        super("Painkillers", "Increases maximum HP and restores 5 HP", 5);
    }

    // Flexible constructor if custom attributes are needed
    public PainKillers(String name, String description, int healAmount) {
        super(name, description, healAmount);
    }

}
