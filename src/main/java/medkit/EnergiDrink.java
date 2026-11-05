package medkit;

import item.Medkit;

public class EnergiDrink extends Medkit {

    // Default constructor providing 20 HP healing
    public EnergiDrink() {
        super("EnergiDrink", "Restores 20 HP ", 20);
    }

    // Flexible constructor if custom attributes are needed
    public EnergiDrink(String name, String description, int healAmount) {
        super(name, description, healAmount);
    }

}
