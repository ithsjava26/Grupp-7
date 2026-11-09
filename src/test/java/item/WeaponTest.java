package item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeaponTest {

    @Test
    void storesDamageBonusAndDefaultsRequiredLevelToOne() {
        Weapon weapon = new Weapon("Rifle", "A test rifle", 35);

        assertEquals("Rifle", weapon.getName());
        assertEquals("A test rifle", weapon.getDescription());
        assertEquals(35, weapon.getDamageBonus());
        assertEquals(1, weapon.getRequiredLevel());
        assertEquals("Rifle (Level 1) - A test rifle [Damage: +35]", weapon.toString());
    }

    @Test
    void storesExplicitRequiredLevel() {
        Weapon weapon = new Weapon("Shotgun", "A test shotgun", 5, 50);

        assertEquals(5, weapon.getRequiredLevel());
        assertEquals(50, weapon.getDamageBonus());
    }
}
