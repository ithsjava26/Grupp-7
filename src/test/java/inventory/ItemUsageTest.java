package inventory;

import combat.Hero;
import combat.MeleeAttack;
import food.ProteinBar;
import item.KeyItem;
import medkit.Bandage;
import medkit.EnergiDrink;
import medkit.PainKillers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemUsageTest {

    @Test
    void rejectsNullInventory() {
        assertThrows(IllegalArgumentException.class, () -> new ItemUsage(null));
    }

    @Test
    void picksUpItemsThroughInventory() {
        Inventory inventory = new Inventory(1);
        ItemUsage usage = new ItemUsage(inventory);

        assertTrue(usage.pickUpItem(new Bandage()));
        assertFalse(usage.pickUpItem(new Bandage()));
        assertFalse(usage.pickUpItem(null));
    }

    @Test
    void returnsMessagesForNullAndInvalidSelections() {
        ItemUsage usage = new ItemUsage(new Inventory());

        assertEquals("No character selected.", usage.useItem(0, null));
        assertEquals("Invalid inventory selection.", usage.useItem(0, hero()));
        usage.pickUpItem(new Bandage());
        assertEquals("No character selected.", usage.useItem(0, null));
        assertEquals("Invalid inventory selection.", usage.useItem(-1, hero()));
        assertEquals("Invalid inventory selection.", usage.useItem(1, hero()));
    }

    @Test
    void foodIncreasesMaximumHealthAndIsConsumed() {
        Inventory inventory = new Inventory();
        ItemUsage usage = new ItemUsage(inventory);
        Hero hero = hero();
        ProteinBar food = new ProteinBar();
        usage.pickUpItem(food);

        String result = usage.useItem(0, hero);

        assertEquals("You ate Protein Bar. Maximum HP increased by 10.", result);
        assertEquals(100, hero.getHealth());
        assertEquals(110, hero.getMaxHealth());
        assertTrue(inventory.isEmpty());
    }

    @Test
    void regularMedkitRestoresHealthAndIsConsumedOnlyWhenEffective() {
        Inventory inventory = new Inventory();
        ItemUsage usage = new ItemUsage(inventory);
        Hero hero = hero();
        hero.takeDamage(30);
        usage.pickUpItem(new Bandage());

        assertEquals("You used Bandage and restored 20 HP.", usage.useItem(0, hero));
        assertEquals(90, hero.getHealth());
        assertTrue(inventory.isEmpty());

        usage.pickUpItem(new Bandage());
        assertEquals("You used Bandage and restored 10 HP.", usage.useItem(0, hero));
        assertEquals(100, hero.getHealth());
        assertTrue(inventory.isEmpty());

        usage.pickUpItem(new Bandage());
        assertEquals(
                "Your health is already full. The item was not consumed.",
                usage.useItem(0, hero));
        assertEquals(1, inventory.getSize());
    }

    @Test
    void painkillersIncreaseMaximumAndCurrentHealth() {
        Inventory inventory = new Inventory();
        ItemUsage usage = new ItemUsage(inventory);
        Hero hero = hero();
        hero.takeDamage(20);
        usage.pickUpItem(new PainKillers());

        assertEquals(
                "You used Painkillers. Maximum HP increased by 5 and you restored 5 HP.",
                usage.useItem(0, hero));
        assertEquals(85, hero.getHealth());
        assertEquals(105, hero.getMaxHealth());
        assertTrue(inventory.isEmpty());
    }

    @Test
    void energyDrinkActivatesHeroBoostAndIsConsumed() {
        Inventory inventory = new Inventory();
        ItemUsage usage = new ItemUsage(inventory);
        Hero hero = hero();
        usage.pickUpItem(new EnergiDrink());

        assertEquals(
                "You used Energy Drink. Your next attack will deal double damage!",
                usage.useItem(0, hero));
        assertTrue(hero.hasEnergyBoost());
        assertTrue(inventory.isEmpty());
    }

    @Test
    void doesNotConsumeUnsupportedItems() {
        Inventory inventory = new Inventory();
        ItemUsage usage = new ItemUsage(inventory);
        usage.pickUpItem(new KeyItem("Car keys", "Opens a car"));

        assertEquals("This item cannot be consumed.", usage.useItem(0, hero()));
        assertEquals(1, inventory.getSize());
    }

    @Test
    void doesNotAllowUseWhileCharacterIsDead() {
        Inventory inventory = new Inventory();
        ItemUsage usage = new ItemUsage(inventory);
        Hero hero = hero();
        hero.takeDamage(100);
        usage.pickUpItem(new Bandage());

        assertEquals("You cannot use items while dead.", usage.useItem(0, hero));
        assertEquals(1, inventory.getSize());
    }

    private static Hero hero() {
        return new Hero("Test hero", 100, 100, 10, 0, new MeleeAttack());
    }
}
