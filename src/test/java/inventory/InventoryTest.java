package inventory;

import item.Armor;
import item.Item;
import item.Medkit;
import item.Weapon;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void usesDefaultCapacityAndStartsEmpty() {
        Inventory inventory = new Inventory();

        assertEquals(Inventory.DEFAULT_CAPACITY, inventory.getCapacity());
        assertEquals(0, inventory.getSize());
        assertEquals(0, inventory.getItemCount());
        assertTrue(inventory.isEmpty());
        assertFalse(inventory.isFull());
    }

    @Test
    void rejectsNonPositiveCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new Inventory(0));
        assertThrows(IllegalArgumentException.class, () -> new Inventory(-1));
    }

    @Test
    void constructorCopiesOnlyNonNullItemsUpToCapacity() {
        Item first = new Medkit("Bandage", "Restores health", 10);
        Item second = new Weapon("Axe", "A weapon", 20);
        Inventory inventory = new Inventory(1, List.of(first, second));

        assertEquals(1, inventory.getSize());
        assertSame(first, inventory.getItem(0));
    }

    @Test
    void addItemRejectsNullAndItemsWhenFull() {
        Inventory inventory = new Inventory(1);
        Item item = new Medkit("Bandage", "Restores health", 10);

        assertFalse(inventory.addItem(null));
        assertTrue(inventory.addItem(item));
        assertFalse(inventory.addItem(new Weapon("Axe", "A weapon", 20)));
        assertTrue(inventory.isFull());
        assertEquals(1, inventory.getSize());
    }

    @Test
    void removesItemsByInstanceIndexAndName() {
        Inventory inventory = new Inventory();
        Item bandage = new Medkit("Bandage", "Restores health", 10);
        inventory.addItem(bandage);
        inventory.addItem(new Armor("Jacket", "Protective", 5));

        assertTrue(inventory.removeItem(bandage));
        assertFalse(inventory.removeItem(bandage));

        Item jacket = inventory.removeItem(0);
        assertEquals("Jacket", jacket.getName());

        inventory.addItem(new Weapon("Fire Axe", "A weapon", 20));
        assertTrue(inventory.removeItemByName("  fire axe "));
        assertFalse(inventory.removeItemByName("missing"));
        assertFalse(inventory.removeItemByName(" "));
        assertFalse(inventory.removeItemByName(null));
        assertTrue(inventory.isEmpty());
    }

    @Test
    void itemRemovalHandlesNullAndIndexRemovalChecksBounds() {
        Inventory inventory = new Inventory();

        assertFalse(inventory.removeItem((Item) null));
        assertThrows(IndexOutOfBoundsException.class, () -> inventory.removeItem(0));
    }

    @Test
    void findsAndChecksItemsByNameIgnoringCase() {
        Inventory inventory = new Inventory();
        Item bandage = new Medkit("Bandage", "Restores health", 10);
        inventory.addItem(bandage);

        assertTrue(inventory.hasItem(bandage));
        assertFalse(inventory.hasItem((Item) null));
        assertTrue(inventory.hasItem("BANDAGE"));
        assertFalse(inventory.hasItem("missing"));
        assertEquals(Optional.of(bandage), inventory.findItemByName(" bandage "));
        assertEquals(Optional.empty(), inventory.findItemByName(null));
        assertEquals(Optional.empty(), inventory.findItemByName(" "));
        assertSame(bandage, inventory.getItemByName("bandage"));
        assertNull(inventory.getItemByName("missing"));
    }

    @Test
    void returnsItemsByTypeAndAnUnmodifiableView() {
        Inventory inventory = new Inventory();
        Armor armor = new Armor("Jacket", "Protective", 5);
        Weapon weapon = new Weapon("Axe", "A weapon", 20);
        inventory.addItem(armor);
        inventory.addItem(weapon);

        assertEquals(List.of(armor), inventory.getItemsByType(Armor.class));
        assertEquals(List.of(weapon), inventory.getItemsByType(Weapon.class));
        assertTrue(inventory.getItemsByType(Medkit.class).isEmpty());
        assertTrue(inventory.getItemsByType(null).isEmpty());
        assertThrows(UnsupportedOperationException.class,
                () -> inventory.getItems().add(new Medkit("Kit", "Restores health", 10)));
    }

    @Test
    void validatesCapacityChanges() {
        Inventory inventory = new Inventory(3);
        inventory.addItem(new Medkit("Bandage", "Restores health", 10));

        inventory.setCapacity(1);
        assertEquals(1, inventory.getCapacity());
        assertTrue(inventory.isFull());

        assertThrows(IllegalArgumentException.class, () -> inventory.setCapacity(0));
        assertThrows(IllegalArgumentException.class, () -> inventory.setCapacity(-1));

        Inventory withTwoItems = new Inventory(2);
        withTwoItems.addItem(new Armor("Jacket", "Protective", 5));
        withTwoItems.addItem(new Weapon("Axe", "A weapon", 20));
        assertThrows(IllegalArgumentException.class, () -> withTwoItems.setCapacity(1));
    }

    @Test
    void clearAndDisplayReflectInventoryContents() {
        Inventory inventory = new Inventory(2);

        assertTrue(inventory.getDisplayString().contains("(Inventory is empty)"));
        inventory.addItem(new Medkit("Bandage", "Restores health", 10));
        assertTrue(inventory.getDisplayString().contains("1. Bandage (Level 1)"));
        assertEquals(inventory.getDisplayString(), inventory.toString());

        inventory.clear();

        assertTrue(inventory.isEmpty());
        assertEquals(0, inventory.getItemCount());
    }
}
