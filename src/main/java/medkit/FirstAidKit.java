package medkit;

import item.Medkit;

public class FirstAidKit extends Medkit {

    // Default constructor providing 20 HP healing
    public FirstAidKit() {
        super("First Aid Kit", "Restores 40 HP. Can be used outside or during combat.", 20);
    }

    // Flexible constructor if custom attributes are needed
    public FirstAidKit(String name, String description, int healAmount) {
        super(name, description, healAmount);
    }

}
