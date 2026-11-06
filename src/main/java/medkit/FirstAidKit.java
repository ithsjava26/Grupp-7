package medkit;

import item.Medkit;

public class FirstAidKit extends Medkit {

    // Default constructor providing 20 HP healing
    public FirstAidKit() {
        super("FirstAidKit ", "Restores 20HP ", 20);
    }

    // Flexible constructor if custom attributes are needed
    public FirstAidKit(String name, String description, int healAmount) {
        super(name, description, healAmount);
    }

}
