package game;

import roomMechanics.*;

import java.util.HashSet;
import java.util.Set;

public class GameEngine {

    private final World world = new World();
    private final ExplorationHandler explorationHandler = new ExplorationHandler();
    private final GameplayMenu gameplayMenu = new GameplayMenu();
    private final String playerName;
    private Room currentRoom;
    private final Set<ExplorationAction> completedActions = new HashSet<>();

    public GameEngine(String playerName) {
        this.playerName = playerName;
    }

    public void enterRoom(Room room) { //Prints the room heading and description.
        IO.println(ConsoleColors.YELLOW + "========== " + room.name().toUpperCase()
                + " ==========" + ConsoleColors.RESET);
        IO.println();
        GameUtils.printNarrative(room.description());

        GameUtils.pauseAfterRoom();
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
                IO.println("Please select an option.");
                continue;
            }

            choice = choice.trim();

            switch (choice) {

                case "1":
                    exploreCurrentRoom();
                    break;

                case "2":
                    IO.println("Inventory is not available yet.");
                    GameUtils.pauseForUser();
                    break;

                case "3":
                    IO.println("Character status is not available yet.");
                    GameUtils.pauseForUser();
                    break;

                case "4":
                    //moves player to next room
                    Room nextRoom = world.getNextRoom(currentRoom);

                    if (nextRoom == null) {
                        IO.println("You have reached the end of the game.");
                        gameRunning = false;
                    } else {
                        currentRoom = nextRoom;
                        displayCurrentRoom(currentRoom);
                    }
                    break;

                case "5":
                    IO.println("Save functionality is not available yet.");
                    GameUtils.pauseForUser();
                    break;

                case "0": //exits gameplay loop
                    IO.println("Returning to main menu...");
                    gameRunning = false;
                    break;

                default:
                    IO.println("Invalid option. Please try again.");
            }
        }
    }


    private void exploreCurrentRoom() {

        while (true) {

            // Rebuild the available options each time the menu is displayed.
            var options = currentRoom.explorationOptions().stream()
                    .filter(option -> option.repeatable()
                            || !completedActions.contains(option.action()))
                    .toList();

            if (options.isEmpty()) {
                IO.println("There is nothing to explore here.");
                GameUtils.pauseForUser();
                return;
            }

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
                        playerName);

                if (!selectedExploration.repeatable()) {
                    completedActions.add(selectedExploration.action());
                }

                GameUtils.pauseForUser();

            } catch (NumberFormatException e) {
                IO.println("Please enter a number.");
            }
        }
    }
}
