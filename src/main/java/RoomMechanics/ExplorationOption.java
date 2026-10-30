package RoomMechanics;

public record ExplorationOption(
        String displayName, //the text displayed to the player, e.g. "Search the desks"
        ExplorationAction action, //tells the game which action to execute, e.g. SEARCH_DESKS.
        boolean repeatable//if player can choose the option again after using it.
) {
}

