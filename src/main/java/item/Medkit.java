package item;

public class Medkit extends Item {
    private int healAmount;

    public Medkit(String name, String description, int healAmount) {
        this(name, description, 1, healAmount);
    }

    public Medkit(String name, String description, int requiredLevel, int healAmount) {
        super(name, description, requiredLevel);
        this.healAmount = healAmount;
    }

    public int getHealAmount() {
        return this.healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public String toString() {
        return super.toString() + " Heal Amount: " + this.healAmount;
    }
}
