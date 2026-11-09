package item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArmorTest {

    @Test
    void storesDefenseBonusAndDefaultsRequiredLevelToOne() {
        Armor armor = new Armor("Jacket", "Protective jacket", 12);

        assertEquals("Jacket", armor.getName());
        assertEquals("Protective jacket", armor.getDescription());
        assertEquals(12, armor.getDefenseBonus());
        assertEquals(1, armor.getRequiredLevel());
        assertEquals("Jacket (Level 1) - Protective jacket [Defense: +12]", armor.toString());
    }

    @Test
    void storesExplicitRequiredLevel() {
        Armor armor = new Armor("Vest", "Armored vest", 3, 25);

        assertEquals(3, armor.getRequiredLevel());
        assertEquals(25, armor.getDefenseBonus());
    }
}
