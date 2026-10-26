package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    @Test
    void testGreeting() {
        assertEquals("Hello, World!", Main.getGreeting());
    }
}
