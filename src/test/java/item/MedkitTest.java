package item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedkitTest {

    @Test
    void storesHealAmountAndDefaultsRequiredLevelToOne() {
        Medkit medkit = new Medkit("Bandage", "Restores health", 20);

        assertEquals("Bandage", medkit.getName());
        assertEquals("Restores health", medkit.getDescription());
        assertEquals(20, medkit.getHealAmount());
        assertEquals(1, medkit.getRequiredLevel());
        assertEquals("Bandage (Level 1) - Restores health Heal Amount: 20", medkit.toString());
    }

    @Test
    void supportsUpdatingHealAmount() {
        Medkit medkit = new Medkit("First aid", "Restores health", 20);

        medkit.setHealAmount(35);

        assertEquals(35, medkit.getHealAmount());
    }

    @Test
    void storesExplicitRequiredLevel() {
        Medkit medkit = new Medkit("Advanced kit", "Restores health", 4, 40);

        assertEquals(4, medkit.getRequiredLevel());
        assertEquals(40, medkit.getHealAmount());
    }
}
