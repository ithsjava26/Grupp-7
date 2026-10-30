package RoomMechanics;

public record ExplorationOption(
        String displayName, // the text displayed to the player
        ExplorationAction action, // tells the game which action to execute
        boolean repeatable // if the player can choose the option again
) {
    public ExplorationOption {
        if (displayName == null || action == null) {
            throw new IllegalArgumentException(
                    "Exploration option requires a name and action."
            );
        }
    }
}
