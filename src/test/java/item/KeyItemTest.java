package item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KeyItemTest {

    @Test
    void storesNameAndDescription() {
        KeyItem key = new KeyItem("Car keys", "Opens the car");

        assertEquals("Car keys", key.getName());
        assertEquals("Opens the car", key.getDescription());
        assertEquals(1, key.getRequiredLevel());
        assertEquals("Car keys (Level 1) - Opens the car", key.toString());
    }
}
