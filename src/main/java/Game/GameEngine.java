package Game;

import RoomMechanics.ExplorationHandler;
import RoomMechanics.Room;
import RoomMechanics.World;

public class GameEngine {

    private final World world = new World();
    private final ExplorationHandler explorationHandler = new ExplorationHandler();
    private final GameplayMenu gameplayMenu = new GameplayMenu();
    private String playerName;
    private Room currentRoom;

    public GameEngine(String playerName) {
        this.playerName = playerName;
    }

    public void enterRoom(Room room) { //Prints the room heading and description.
        IO.println("========== " + room.name().toUpperCase() + " ==========");
        IO.println(room.description());
    }

    public void displayCurrentRoom(Room room) {//Calls enterRoom() when displaying a room.
        enterRoom(room);
    }

    public String getPlayerChoice() {  //	Displays the menu and returns the player's selection.
        Room nextRoom = world.getNextRoom(currentRoom);
        String nextRoomName = nextRoom != null
                ? nextRoom.name()
                : "The end";

        return gameplayMenu.runMenu(nextRoomName);
    }

    public void startGame() {
        currentRoom = world.getStartingRoom();
        displayCurrentRoom(currentRoom);

        boolean gameRunning = true;

        while (gameRunning) {
            String choice = getPlayerChoice();

            switch (choice) {

                case "1":
                    exploreCurrentRoom();
                    break;

                case "5": //moves player to next room
                    currentRoom = world.getNextRoom(currentRoom);

                    if (currentRoom == null) {
                        IO.println("You have reached the end of the game.");
                        gameRunning = false;
                    } else {
                        displayCurrentRoom(currentRoom);
                    }
                    break;

                case "6": //exits gameplay loop
                    IO.println("Returning to main menu...");
                    gameRunning = false;
                    break;

                default:
                    IO.println("That option isn't implemented yet. Try again.");
            }
        }
    }

    private void exploreCurrentRoom() {
        var options = currentRoom.explorationOptions();
        String choice = gameplayMenu.runExplorationMenu(options);

        try {
            int selectedOption = Integer.parseInt(choice) - 1; //converts the player's input from a String into an integer.

            if (selectedOption < 0 || selectedOption >= options.size()) {
                IO.println("Invalid option. Please try again.");
                return;
            }

            var selectedExploration = options.get(selectedOption); //retrieves the chosen ExplorationOption.
            explorationHandler.handleExploration(selectedExploration.action(), playerName); //executes the corresponding narrative/interaction.

        } catch (NumberFormatException e) {
            IO.println("Please enter a number.");
        }
    }
}