package Game;

import RoomMechanics.ExplorationHandler;
import RoomMechanics.Room;
import RoomMechanics.World;

public class GameEngine {

    private final World world = new World();
    private final ExplorationHandler explorationHandler = new ExplorationHandler();
    private String playerName;
    private Room currentRoom;

    public GameEngine(String playerName) {
        this.playerName = playerName;
    }

    public void enterRoom(Room room) {
        IO.println("========== " + room.name().toUpperCase() + " ==========");
        IO.println(room.description());
    }

    public void displayCurrentRoom(Room room) {
        enterRoom(room);

        Room nextRoom = world.getNextRoom(room);
        String nextRoomName = nextRoom != null
                ? nextRoom.name()
                : "The end";

        GameplayMenu gameplayMenu = new GameplayMenu();
        gameplayMenu.runMenu(nextRoomName);
    }

    public void startGame() {
        currentRoom = world.getStartingRoom();
        displayCurrentRoom(currentRoom);
    }
}