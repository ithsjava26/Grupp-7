package food;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodItemsTest {

    @Test
    void chipsHaveExpectedNameAndHealthIncrease() {
        Chips chips = new Chips();

        assertAll(
                () -> assertEquals("Chips", chips.getName()),
                () -> assertEquals(10, chips.getHealAmount()));
    }

    @Test
    void proteinBarHasExpectedNameAndHealthIncrease() {
        ProteinBar proteinBar = new ProteinBar();

        assertAll(
                () -> assertEquals("Protein Bar", proteinBar.getName()),
                () -> assertEquals(10, proteinBar.getHealAmount()));
    }
}
