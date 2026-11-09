package medkit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MedkitItemsTest {

    @Test
    void concreteMedkitsHaveExpectedNamesAndHealAmounts() {
        Antibiotics antibiotics = new Antibiotics();
        Antiseptic antiseptic = new Antiseptic();
        Bandage bandage = new Bandage();
        EnergiDrink energyDrink = new EnergiDrink();
        FirstAidKit firstAidKit = new FirstAidKit();
        PainKillers painKillers = new PainKillers();

        assertAll(
                () -> assertEquals("Antibiotics", antibiotics.getName()),
                () -> assertEquals(20, antibiotics.getHealAmount()),
                () -> assertEquals("Antiseptic", antiseptic.getName()),
                () -> assertEquals(20, antiseptic.getHealAmount()),
                () -> assertEquals("Bandage", bandage.getName()),
                () -> assertEquals(20, bandage.getHealAmount()),
                () -> assertEquals("Energy Drink", energyDrink.getName()),
                () -> assertEquals(0, energyDrink.getHealAmount()),
                () -> assertEquals("First Aid Kit", firstAidKit.getName()),
                () -> assertEquals(20, firstAidKit.getHealAmount()),
                () -> assertEquals("Painkillers", painKillers.getName()),
                () -> assertEquals(5, painKillers.getHealAmount()));
    }

    @Test
    void concreteMedkitsSupportCustomValues() {
        Antibiotics antibiotics = new Antibiotics("Custom antibiotics", "Custom", 11);
        Antiseptic antiseptic = new Antiseptic("Custom antiseptic", "Custom", 12);
        Bandage bandage = new Bandage("Custom bandage", "Custom", 13);
        EnergiDrink energyDrink = new EnergiDrink("Custom drink", "Custom", 0);
        FirstAidKit firstAidKit = new FirstAidKit("Custom kit", "Custom", 14);
        PainKillers painKillers = new PainKillers("Custom painkillers", "Custom", 15);

        assertAll(
                () -> assertEquals("Custom antibiotics", antibiotics.getName()),
                () -> assertEquals(11, antibiotics.getHealAmount()),
                () -> assertEquals("Custom antiseptic", antiseptic.getName()),
                () -> assertEquals(12, antiseptic.getHealAmount()),
                () -> assertEquals("Custom bandage", bandage.getName()),
                () -> assertEquals(13, bandage.getHealAmount()),
                () -> assertEquals("Custom drink", energyDrink.getName()),
                () -> assertEquals(0, energyDrink.getHealAmount()),
                () -> assertEquals("Custom kit", firstAidKit.getName()),
                () -> assertEquals(14, firstAidKit.getHealAmount()),
                () -> assertEquals("Custom painkillers", painKillers.getName()),
                () -> assertEquals(15, painKillers.getHealAmount()));
    }
}
