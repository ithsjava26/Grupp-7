
package game;

import audio.SoundManager;
import combat.*;
import roomMechanics.*;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class GameEngine {

    private final World world = new World();
    private final SoundManager soundManager = new SoundManager();
    private final ExplorationHandler explorationHandler = new ExplorationHandler(soundManager);
    private final GameplayMenu gameplayMenu = new GameplayMenu();
    private final String playerName;
    private final Hero player;
    private Room currentRoom;
    private final Set<ExplorationAction> completedActions = new HashSet<>();
    private final CombatManager combatManager = new CombatManager();
    private final Set<String> completedEncounters = new HashSet<>();
    private boolean gameOver = false;

    public GameEngine(String playerName) {
        this.playerName = playerName;

        this.player = new Hero(
                playerName,
                100,
                100,
                15,
                5,
                new MeleeAttack()
        );
    }

    public void enterRoom(Room room) {
        IO.println(ConsoleColors.YELLOW + "=========== " + room.name().toUpperCase()
                + " ===========" + ConsoleColors.RESET);
        IO.println();
        GameUtils.printNarrative(room.description());

        GameUtils.pauseAfterRoom();
    }

    public void displayCurrentRoom(Room room) {
        enterRoom(room);

        if (!startEncounter(room)) {
            IO.println();
            GameUtils.printWarning("GAME OVER");
            IO.println("Returning to main menu...");
            gameOver = true;
        }
    }

    public String getPlayerChoice() {
        Room nextRoom = world.getNextRoom(currentRoom);
        String nextRoomName = nextRoom != null
                ? nextRoom.name()
                : "The end";

        return gameplayMenu.runMenu(nextRoomName);
    }

    public void startGame() {
        soundManager.start();
        soundManager.setMusic(SoundManager.Track.MAIN);

        currentRoom = world.getStartingRoom();
        displayCurrentRoom(currentRoom);

        boolean gameRunning = true;

        while (gameRunning && !gameOver) {
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
                    displayCharacterStatus();
                    break;

                case "4":
                    // Moves player to next room
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

                case "0": // Exits gameplay loop
                    IO.println("Returning to main menu...");
                    gameRunning = false;
                    break;

                default:
                    IO.println("Invalid option. Please try again.");
            }
        }

        soundManager.shutdown();
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
                        playerName,
                        player.getInventory());

                if (!selectedExploration.repeatable()) {
                    completedActions.add(selectedExploration.action());
                }

                GameUtils.pauseForUser();

                if (selectedExploration.action() == ExplorationAction.TALK_TO_COWORKER) {
                    return;
                }

            } catch (NumberFormatException e) {
                IO.println("Please enter a number.");
            }
        }
    }

    private void displayCharacterStatus() {
        IO.println();
        IO.println(ConsoleColors.YELLOW + "========== CHARACTER STATUS ==========" + ConsoleColors.RESET);

        IO.println(ConsoleColors.YELLOW + "Name: " + ConsoleColors.RESET + player.getName());
        IO.println(ConsoleColors.YELLOW + "Health: " + ConsoleColors.RESET
                + player.getHealth() + "/" + player.getMaxHealth());
        IO.println(ConsoleColors.YELLOW + "Attack: " + ConsoleColors.RESET + player.getAttack());
        IO.println(ConsoleColors.YELLOW + "Defence: " + ConsoleColors.RESET + player.getDefense());

        IO.println(ConsoleColors.YELLOW + "======================================" + ConsoleColors.RESET);

        GameUtils.pauseForUser();
    }

    private List<Enemy> createEnemiesForRoom(Room room) {
        return switch (room.id()) {
            case "street-2" -> List.of(
                    new Enemy("Infected Civilian", 35, 35, 8, 2, new MeleeAttack())
            );

            case "park-2" -> List.of(
                    new Enemy("Infected Runner", 35, 35, 9, 2, new MeleeAttack()),
                    new Enemy("Infected Heavy", 50, 50, 11, 4, new HeavyAttack())
            );

            case "garage" -> List.of(
                    new Enemy("Your Partner", 70, 70, 12, 4, new HeavyAttack())
            );

            default -> List.of();
        };
    }

    private boolean startEncounter(Room room) {
        if (completedEncounters.contains(room.id())) {
            return true;
        }

        List<Enemy> enemies = createEnemiesForRoom(room);

        if (enemies.isEmpty()) {
            return true;
        }

        IO.println();
        IO.println(ConsoleColors.RED + "!!! ENEMY ENCOUNTER !!!" + ConsoleColors.RESET);
        GameUtils.pauseForCombat();

        // Switch to battle music
        soundManager.setMusic(SoundManager.Track.BATTLE);

        CombatResult result = combatManager.startCombat(player, enemies);

        completedEncounters.add(room.id());

        if (result == CombatResult.DEFEAT) {
            soundManager.setMusic(SoundManager.Track.NONE);
            soundManager.playEffect(SoundManager.Effect.GAME_OVER);

            GameUtils.printWarning("You have been overwhelmed. Your journey ends here.");
            return false;
        }

        // Resume main music after victory
        soundManager.setMusic(SoundManager.Track.MAIN);
        soundManager.playEffect(SoundManager.Effect.VICTORY);

        GameUtils.printSystemMessage("The area is clear. You can move on.");
        GameUtils.pauseForUser();

        return true;
    }
}
