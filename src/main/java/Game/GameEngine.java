package Game;

import RoomMechanics.Room;
import RoomMechanics.World;

public class GameEngine {
    private final World world = new World(); //creates the world and its rooms
    private Room currentRoom; //tracks the player's present location.

    public void enterRoom(Room room) {
        IO.println("========== " + room.name().toUpperCase() + " ==========");
        IO.println(room.description());
    }

    public void displayCurrentRoom(Room room) { //combines displaying the room and launching gameplay menu
        enterRoom(room);

        Room nextRoom = world.getNextRoom(room); //finds next room using nextRoomId
        String nextRoomName = nextRoom != null // retrieves user friendly name
                ? nextRoom.name()  //if no next room, display the end
                : "The end";

        GameplayMenu gameplayMenu = new GameplayMenu();
        gameplayMenu.runMenu(nextRoomName);
    }

    public void startGame() {
        currentRoom = world.getStartingRoom();
        displayCurrentRoom(currentRoom);
    }
}