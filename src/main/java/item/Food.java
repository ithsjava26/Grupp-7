package item;

public class Food extends Item {

    private final int healAmount;

    public Food(String name, String description, int healAmount) {
        super(name, description);

        if (healAmount < 0) {
            throw new IllegalArgumentException("Healing cannot be negative");
        }

        this.healAmount = healAmount;
    }

    public int getHealAmount() {
        return healAmount;
    }
}