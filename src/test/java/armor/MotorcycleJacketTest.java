package armor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MotorcycleJacketTest {

    @Test
    void defaultConstructorCreatesExpectedArmor() {
        MotorcycleJacket jacket = new MotorcycleJacket();

        assertAll(
                () -> assertEquals("Motorcycle Jacket", jacket.getName()),
                () -> assertEquals(10, jacket.getDefenseBonus()),
                () -> assertEquals(1, jacket.getRequiredLevel()));
    }

    @Test
    void customConstructorUsesProvidedValues() {
        MotorcycleJacket jacket = new MotorcycleJacket("Custom jacket", "Custom description", 30);

        assertAll(
                () -> assertEquals("Custom jacket", jacket.getName()),
                () -> assertEquals("Custom description", jacket.getDescription()),
                () -> assertEquals(30, jacket.getDefenseBonus()));
    }
}
