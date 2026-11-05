package inventory;

import combat.GameCharacter;
import item.Food;
import item.Item;
import item.Medkit;

public class ItemUsage {

    private final Inventory inventory;

    public ItemUsage(Inventory inventory) {
        if (inventory == null) {
            throw new IllegalArgumentException("Inventory cannot be null");
        }

        this.inventory = inventory;
    }

    public boolean pickUpItem(Item item) {
        return inventory.addItem(item);
    }

    public String useItem(int index, GameCharacter character) {
        if (character == null) {
            return "No character selected.";
        }

        if (index < 0 || index >= inventory.getSize()) {
            return "Invalid inventory selection.";
        }

        Item item = inventory.getItem(index);

        int healAmount;

        if (item instanceof Medkit) {
            healAmount = 20;
        } else if (item instanceof Food) {
            healAmount = 10;
        } else {
            return "This item cannot be consumed.";
        }


        if (!character.isAlive()) {
            return "You cannot use this item while dead.";
        }


        int healthBefore = character.getHealth();

        character.heal(healAmount);

        int restoredHealth = character.getHealth() - healthBefore;

        if (restoredHealth <= 0) {
            return "The item had no effect.";
        }

        inventory.removeItem(index);

        return "You used " + item.getName()
                + " and restored " + restoredHealth + " HP.";
    }

}
