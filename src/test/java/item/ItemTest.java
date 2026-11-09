package item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    @Test
    void storesNameAndDescriptionAndDefaultsRequiredLevelToOne() {
        Item item = new TestItem("Test item", "A test description");

        assertEquals("Test item", item.getName());
        assertEquals("A test description", item.getDescription());
        assertEquals(1, item.getRequiredLevel());
        assertEquals("Test item (Level 1) - A test description", item.toString());
    }

    @Test
    void storesExplicitRequiredLevel() {
        Item item = new TestItem("Advanced item", "A description", 4);

        assertEquals(4, item.getRequiredLevel());
        assertEquals("Advanced item (Level 4) - A description", item.toString());
    }

    private static class TestItem extends Item {
        private TestItem(String name, String description) {
            super(name, description);
        }

        private TestItem(String name, String description, int requiredLevel) {
            super(name, description, requiredLevel);
        }
    }
}
