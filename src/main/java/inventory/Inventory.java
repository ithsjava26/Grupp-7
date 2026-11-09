package inventory;

import item.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents an inventory that stores items without a capacity limit.
 */
public class Inventory {

    // Legacy capacity settings retained for compatibility.
    public static final int DEFAULT_CAPACITY = 10;

    private final List<Item> items;
    private int capacity;

    public Inventory() {
        this(DEFAULT_CAPACITY);
    }

    public Inventory(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public Inventory(int capacity, List<Item> initialItems) {
        this(capacity);
        if (initialItems != null) {
            for (Item item : initialItems) {
                if (item != null) {
                    this.items.add(item);
                }
            }
        }
    }

    /**
     * Adds a non-null item to the inventory.
     *
     * @param item the item to add
     * @return true if the item was added, false otherwise
     */
    public boolean addItem(Item item) {
        if (item == null) {
            return false;
        }
        return items.add(item);
    }

    /**
     * Removes a specific item from the inventory.
     *
     * @param item the item to remove
     * @return true if the item was found and removed, false otherwise
     */
    public boolean removeItem(Item item) {
        if (item == null) {
            return false;
        }
        return items.remove(item);
    }

    /**
     * Removes and returns the item at the specified 0-based index.
     *
     * @param index the index of the item to remove
     * @return the removed item
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public Item removeItem(int index) {
        return items.remove(index);
    }

    /**
     * Removes the first item with the specified name (case-insensitive).
     *
     * @param name the name of the item to remove
     * @return true if an item was removed, false otherwise
     */
    public boolean removeItemByName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(name.trim())) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the inventory contains the specified item instance.
     *
     * @param item the item to check
     * @return true if the item is present, false otherwise
     */
    public boolean hasItem(Item item) {
        return item != null && items.contains(item);
    }

    /**
     * Checks if the inventory contains an item with the specified name (case-insensitive).
     *
     * @param name the name of the item
     * @return true if an item with the name exists, false otherwise
     */
    public boolean hasItem(String name) {
        return findItemByName(name).isPresent();
    }

    /**
     * Gets the item at the specified 0-based index.
     *
     * @param index the index of the item
     * @return the item at the given index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public Item getItem(int index) {
        return items.get(index);
    }

    /**
     * Finds the first item with the specified name (case-insensitive).
     *
     * @param name the name of the item to search for
     * @return an Optional containing the found item, or empty if not found
     */
    public Optional<Item> findItemByName(String name) {
        if (name == null || name.isBlank()) {
            return Optional.empty();
        }
        return items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name.trim()))
                .findFirst();
    }

    /**
     * Gets the first item with the specified name (case-insensitive), or null if not found.
     *
     * @param name the name of the item
     * @return the item or null if not found
     */
    public Item getItemByName(String name) {
        return findItemByName(name).orElse(null);
    }

    /**
     * Returns an unmodifiable list of all items in the inventory.
     *
     * @return unmodifiable list of items
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Returns all items of a specific type (e.g. Weapon, Armor, Medkit).
     *
     * @param <T>  the item subtype
     * @param type the Class of the subtype
     * @return a list containing items of the specified type
     */
    public <T extends Item> List<T> getItemsByType(Class<T> type) {
        if (type == null) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>();
        for (Item item : items) {
            if (type.isInstance(item)) {
                result.add(type.cast(item));
            }
        }
        return result;
    }

    /**
     * Returns the legacy capacity setting.
     * Capacity no longer limits inventory size.
     *
     * @return configured capacity
     */
    public int getCapacity() {
        return capacity;
    }


    /**
     * Returns the number of items currently in the inventory.
     *
     * @return item count
     */
    public int getSize() {
        return items.size();
    }

    /**
     * Returns the number of items currently in the inventory.
     *
     * @return item count
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Inventory has no capacity limit.
     *
     * @return always false
     */
    public boolean isFull() {
        return false;
    }

    /**
     * Checks if the inventory is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Removes all items from the inventory.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Formats the inventory into a readable string representation.
     *
     * @return formatted inventory string
     */
    public String getDisplayString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== INVENTORY (")
                .append(getSize())
                .append(" items) ===\n");

        if (items.isEmpty()) {
            sb.append("(Inventory is empty)\n");
        } else {
            for (int i = 0; i < items.size(); i++) {
                sb.append(i + 1)
                        .append(". ")
                        .append(items.get(i).toString())
                        .append("\n");
            }
        }

        sb.append("========================");
        return sb.toString();
    }

    /**
     * Prints the inventory to standard output.
     */
    public void displayInventory() {
        System.out.println(getDisplayString());
    }

    @Override
    public String toString() {
        return getDisplayString();
    }
}
