package inventory;

import combat.GameCharacter;
import combat.Hero;
import item.Food;
import item.Item;
import item.Medkit;
import medkit.PainKillers;
import medkit.EnergiDrink;

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

        if (!character.isAlive()) {
            return "You cannot use items while dead.";
        }

        Item item = inventory.getItem(index);

        // Energy drinks double the damage of the next attack.
        if (item instanceof EnergiDrink && character instanceof Hero hero) {
            hero.activateEnergyBoost();
            inventory.removeItem(index);

            return "You used " + item.getName()
                    + ". Your next attack will deal double damage!";
        }
        // Food permanently increases maximum health.
        if (item instanceof Food food) {
            int healthIncrease = food.getHealAmount();

            character.increaseMaxHealth(healthIncrease);
            inventory.removeItem(index);

            return "You ate " + item.getName()
                    + ". Maximum HP increased by " + healthIncrease + ".";
        }

        // Painkillers increase maximum health and restore current health.
        if (item instanceof PainKillers painKillers) {
            int healthIncrease = painKillers.getHealAmount();

            character.increaseMaxHealth(healthIncrease);
            character.heal(healthIncrease);

            inventory.removeItem(index);

            return "You used " + item.getName()
                    + ". Maximum HP increased by " + healthIncrease
                    + " and you restored " + healthIncrease + " HP.";
        }

        // Ordinary medkits only restore current health.
        if (item instanceof Medkit medkit) {
            int healthBefore = character.getHealth();

            character.heal(medkit.getHealAmount());

            int restoredHealth = character.getHealth() - healthBefore;

            if (restoredHealth <= 0) {
                return "Your health is already full. The item was not consumed.";
            }

            inventory.removeItem(index);

            return "You used " + item.getName()
                    + " and restored " + restoredHealth + " HP.";
        }

        return "This item cannot be consumed.";
    }
}