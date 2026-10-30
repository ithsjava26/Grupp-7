package RoomMechanics;
import java.util.List;

public record Room(
        String id,
        String name,
        String description,
        List<ExplorationOption> explorationOptions,
        String nextRoomId
) {
}
