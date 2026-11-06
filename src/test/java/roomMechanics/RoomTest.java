
package roomMechanics;

import org.junit.jupiter.api.Test;
import roomMechanics.ExplorationAction;
import roomMechanics.ExplorationOption;
import roomMechanics.Room;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void constructorShouldCopyExplorationOptions() {
        List<ExplorationOption> options = new ArrayList<>();

        options.add(new ExplorationOption(
                "Search the desk",
                ExplorationAction.SEARCH_DESKS,
                false
        ));

        Room room = new Room(
                "office",
                "Office",
                "An empty office.",
                options,
                "hallway"
        );

        options.clear();

        assertEquals(1, room.explorationOptions().size());
    }

    @Test
    void explorationOptionsShouldBeImmutable() {
        Room room = new Room(
                "office",
                "Office",
                "An empty office.",
                new ArrayList<>(),
                "hallway"
        );

        assertThrows(
                UnsupportedOperationException.class,
                () -> room.explorationOptions().add(
                        new ExplorationOption(
                                "Search the desk",
                                ExplorationAction.SEARCH_DESKS,
                                false
                        )
                )
        );
    }
}
