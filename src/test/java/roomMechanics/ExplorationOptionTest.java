package roomMechanics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExplorationOptionTest {
    @Test
    void constructorShouldCreateValidExplorationOption() {
        ExplorationOption option = new ExplorationOption(
                "Search the desk",
                ExplorationAction.SEARCH_DESKS,
                false
        );

        assertEquals("Search the desk", option.displayName());
        assertEquals(ExplorationAction.SEARCH_DESKS, option.action());
        assertFalse(option.repeatable());
    }

    @Test
    void constructorShouldRejectNullDisplayName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ExplorationOption(
                        null,
                        ExplorationAction.SEARCH_DESKS,
                        false
                )
        );
    }

    @Test
    void constructorShouldRejectNullAction() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ExplorationOption(
                        "Search the desk",
                        null,
                        false
                )
        );
    }
}
