package Game;

public class GameEngine {
    private final World world = new World(); //creates the world and its rooms

    public void enterRoom(Room room) {
        IO.println("========== " + room.name().toUpperCase() + " ==========");
        IO.println(room.description());
    }

    public void displayCurrentRoom(Room room) { //combines displaying the room and launching the gameplay menu.
        enterRoom(room);

        GameplayMenu gameplayMenu = new GameplayMenu();
        gameplayMenu.runMenu();
    }

    public void startGame() {
        displayCurrentRoom( //displays room and launches gameplay menu
                world.getStartingRoom()); //retrieves the Office room.
    }
}