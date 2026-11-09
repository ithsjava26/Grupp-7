package item;

public abstract class Item {
    private final String name;
    private final String description;
    private final int requiredLevel;

    public Item(String name, String description) {
        this(name, description, 1);
    }

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
