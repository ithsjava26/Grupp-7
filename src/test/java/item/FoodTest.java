package item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FoodTest {

    @Test
    void storesFoodDetailsAndHealAmount() {
        Food food = new Food("Chips", "Increases maximum health", 10);

        assertEquals("Chips", food.getName());
        assertEquals("Increases maximum health", food.getDescription());
        assertEquals(10, food.getHealAmount());
        assertEquals(1, food.getRequiredLevel());
    }

    @Test
    void rejectsNegativeHealAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new Food("Bad food", "Invalid", -1));
    }

    @Test
    void allowsZeroHealAmount() {
        Food food = new Food("Energy drink", "Boosts an attack", 0);

        assertEquals(0, food.getHealAmount());
    }
}
