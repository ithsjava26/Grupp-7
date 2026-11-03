package game;

import RoomMechanics.ExplorationHandler;
import RoomMechanics.Room;
import RoomMechanics.World;

public class GameEngine {

    private final World world = new World();
    private final ExplorationHandler explorationHandler = new ExplorationHandler();
    private final GameplayMenu gameplayMenu = new GameplayMenu();
    private final String playerName;
    private Room currentRoom;

    public GameEngine(String playerName) {
        this.playerName = playerName;
    }

    public void enterRoom(Room room) { //Prints the room heading and description.
        IO.println("========== " + room.name().toUpperCase() + " ==========");
        IO.println();
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
            if (choice == null || choice.isBlank()) {
                IO.println("Please select an option");
                continue;
            }

            switch (choice) {

                case "1":
                    exploreCurrentRoom();
                    break;

                case "2":
                    IO.println("Inventory is not available yet.");
                    break;

                case "3":
                    IO.println("Character status is not available yet.");
                    break;

                case "4":
                    IO.println("Save functionality is not available yet.");
                    break;

                case "5": //moves player to next room
                    Room nextRoom = world.getNextRoom(currentRoom);

                    if (nextRoom == null) {
                        IO.println("You have reached the end of the game.");
                        gameRunning = false;
                    } else {
                        currentRoom = nextRoom;
                        displayCurrentRoom(currentRoom);
                    }
                    break;

                case "6": //exits gameplay loop
                    IO.println("Returning to main menu...");
                    gameRunning = false;
                    break;

                default:
                    IO.println("Invalid option. Please choose a number between 1 and 6.");
            }
        }
    }


    private void exploreCurrentRoom() {
        var options = currentRoom.explorationOptions();

        if (options.isEmpty()) {
            IO.println("There is nothing to explore here.");
            GameUtils.pauseForUser();
            return;
        }

        while (true) {
            String choice = gameplayMenu.runExplorationMenu(options);

            if (choice == null || choice.isBlank()) {
                IO.println("Please select an option.");
                continue;
            }

            if (choice.trim().equals("0")) {
                return;
            }

            try {
                int selectedOption = Integer.parseInt(choice.trim()) - 1;

                if (selectedOption < 0 || selectedOption >= options.size()) {
                    IO.println("Invalid option. Please try again.");
                    continue;
                }

                var selectedExploration = options.get(selectedOption);

                explorationHandler.handleExploration(
                        selectedExploration.action(),
                        playerName
                );

                GameUtils.pauseForUser();

            } catch (NumberFormatException e) {
                IO.println("Please enter a number.");
            }
        }
    }
}