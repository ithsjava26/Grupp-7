package weapon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WeaponItemsTest {

    @Test
    void hitAreasHaveExpectedDamageMultipliers() {
        assertAll(
                () -> assertEquals(3, HitArea.HEAD.getMultiplier()),
                () -> assertEquals(2, HitArea.TORSO.getMultiplier()),
                () -> assertEquals(1, HitArea.LEGS.getMultiplier()));
    }

    @Test
    void concreteWeaponsHaveExpectedDefaultValues() {
        Glock19 glock = new Glock19();
        Fireaxe fireaxe = new Fireaxe();
        DesertEagle desertEagle = new DesertEagle();

        assertAll(
                () -> assertEquals("Glock 19", glock.getName()),
                () -> assertEquals(30, glock.getDamageBonus()),
                () -> assertEquals("Fire Axe", fireaxe.getName()),
                () -> assertEquals(15, fireaxe.getDamageBonus()),
                () -> assertEquals("DesertEagle", desertEagle.getName()),
                () -> assertEquals(60, desertEagle.getDamageBonus()));
    }

    @Test
    void concreteWeaponsSupportCustomValues() {
        Glock19 glock = new Glock19("Custom Glock", "Custom description", 30);
        Fireaxe fireaxe = new Fireaxe("Custom axe", "Custom description", 15);
        DesertEagle desertEagle = new DesertEagle("Custom Eagle", "Custom description", 70);

        assertAll(
                () -> assertEquals("Custom Glock", glock.getName()),
                () -> assertEquals(30, glock.getDamageBonus()),
                () -> assertEquals("Custom axe", fireaxe.getName()),
                () -> assertEquals(15, fireaxe.getDamageBonus()),
                () -> assertEquals("Custom Eagle", desertEagle.getName()),
                () -> assertEquals(70, desertEagle.getDamageBonus()));
    }
}
