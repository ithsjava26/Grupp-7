package Item;

public abstract class Item {
    private String name;
    private String description;
    private int requiredLevel;

    public Item(String name, String description, int requiredLevel) {
        this.name = name;
        this.description = description;
        this.requiredLevel = requiredLevel;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    @Override
    public String toString() {
        return name + " (Level " + requiredLevel + ") - " + description;
    }
}
