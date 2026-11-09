package medkit;

import item.Medkit;

public class EnergiDrink extends Medkit {

    // Default constructor
    public EnergiDrink() {
        super("Energy Drink",
                "Doubles the damage of your next successful attack. Can be consumed before or during combat.",
                0);
    }

    // Flexible constructor if custom attributes are needed
    public EnergiDrink(String name, String description, int healAmount) {
        super(name, description, healAmount);
    }
}