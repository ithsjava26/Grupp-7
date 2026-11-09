package keyitem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarKeysTest {

    @Test
    void defaultConstructorCreatesExpectedKeyItem() {
        CarKeys keys = new CarKeys();

        assertEquals("Car keys", keys.getName());
        assertEquals("Used to access your car.", keys.getDescription());
        assertEquals(1, keys.getRequiredLevel());
    }
}
