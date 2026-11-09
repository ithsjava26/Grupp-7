
package game;

import audio.SoundManager;
import combat.*;
import inventory.ItemUsage;
import item.*;
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
                    viewInventory();
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
                GameUtils.printSystemMessage("There is nothing to explore here.");
                GameUtils.pauseForUser();
                return;
            }

            String choice = gameplayMenu.runExplorationMenu(options);

            if (choice == null || choice.isBlank()) {
                IO.println("Please select an option.");
                continue;
            }

            if (choice.trim().equals("e")) {
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
        if (player.hasEnergyBoost()) {
            IO.println(ConsoleColors.YELLOW + "[ACTIVE] Energy boost: Next attack deals double damage!"
                    + ConsoleColors.RESET);
        }

        IO.println(ConsoleColors.YELLOW + "======================================" + ConsoleColors.RESET);

        GameUtils.pauseForUser();
    }

    private List<Enemy> createEnemiesForRoom(Room room) {
        return switch (room.id()) {
            case "street-2" -> List.of(
                    new Enemy("Infected Civilian", 55, 55, 11, 3, new MeleeAttack())
            );

            case "park-2" -> List.of(
                    new Enemy("Infected Runner", 35, 35, 9, 2, new MeleeAttack()),
                    new Enemy("Infected Heavy", 50, 50, 11, 4, new HeavyAttack())
            );

            case "garage" -> List.of(
                    new Enemy("Your Partner", 70, 70, 10, 4, new HeavyAttack())
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

    private void viewInventory() {
        ItemUsage itemUsage = new ItemUsage(player.getInventory());

        while (true) {
            String choice = gameplayMenu.runInventoryMenu(player);

            if (choice == null || choice.isBlank()) {
                return;
            }

            choice = choice.trim();

            if (choice.equals("0")) {
                return;
            }

            try {
                int itemNumber = Integer.parseInt(choice);

                if (itemNumber < 1 || itemNumber > player.getInventory().getSize()) {
                    IO.println("Invalid inventory selection.");
                    GameUtils.pauseForUser();
                    continue;
                }

                Item selectedItem = player.getInventory().getItem(itemNumber - 1);

                handleSelectedItem(selectedItem, itemUsage);

            } catch (NumberFormatException e) {
                IO.println("Please enter a valid number.");
                GameUtils.pauseForUser();
            }
        }
    }
    private void handleSelectedItem(Item item, ItemUsage itemUsage) {
        while (true) {
            IO.println();
            IO.println(ConsoleColors.YELLOW + "=== " + item.getName().toUpperCase()
                    + " ===" + ConsoleColors.RESET);
            IO.println();
            IO.println(item.getDescription());
            IO.println();

            if (item instanceof Weapon weapon) {
                boolean equipped = player.getEquippedWeapon() == weapon;

                if (equipped) {
                    IO.println(ConsoleColors.YELLOW + "1. Unequip weapon" + ConsoleColors.RESET);
                } else {
                    IO.println(ConsoleColors.YELLOW + "1. Equip weapon" + ConsoleColors.RESET);
                }

            } else if (item instanceof Armor armor) {
                boolean equipped = player.getEquippedArmor() == armor;

                if (equipped) {
                    IO.println(ConsoleColors.YELLOW + "1. Unequip armour" + ConsoleColors.RESET);
                } else {
                    IO.println(ConsoleColors.YELLOW + "1. Equip armour" + ConsoleColors.RESET);
                }

            } else if (item instanceof Food || item instanceof Medkit) {
                IO.println(ConsoleColors.YELLOW + "1. Use item" + ConsoleColors.RESET);

            } else {
                IO.println("This item cannot be used or equipped.");
            }

            IO.println(ConsoleColors.YELLOW + "e. Go back" + ConsoleColors.RESET);
            IO.println();
            IO.print("Choose an option: ");

            String choice = IO.readln();

            if (choice == null || choice.isBlank() || choice.trim().equals("e")) {
                return;
            }

            if (!choice.trim().equals("1")) {
                IO.println("Invalid option.");
                GameUtils.pauseForUser();
                continue;
            }

            if (item instanceof Weapon weapon) {
                if (player.getEquippedWeapon() == weapon) {
                    player.unequipWeapon();
                    IO.println("Weapon unequipped.");
                } else {
                    player.equipWeapon(weapon);
                    IO.println("Weapon equipped: " + weapon.getName());
                }

                GameUtils.pauseForUser();
                return;

            } else if (item instanceof Armor armor) {
                if (player.getEquippedArmor() == armor) {
                    player.unequipArmor();
                    IO.println("Armour unequipped.");
                } else {
                    player.equipArmor(armor);
                    IO.println("Armour equipped: " + armor.getName());
                }

                GameUtils.pauseForUser();
                return;

            } else if (item instanceof Food || item instanceof Medkit) {
                if (confirmItemUse(item)) {
                    int index = player.getInventory().getItems().indexOf(item);
                    IO.println(itemUsage.useItem(index, player));
                }

                GameUtils.pauseForUser();
                return;
            }

            GameUtils.pauseForUser();
            return;
        }
    }
    private boolean confirmItemUse(Item item) {
        IO.println();
        IO.println("Do you want to consume " + item.getName() + "?");
        IO.println(ConsoleColors.YELLOW + "1. Yes" + ConsoleColors.RESET);
        IO.println(ConsoleColors.YELLOW + "0. No" + ConsoleColors.RESET);
        IO.println();
        IO.print("Choose an option: ");

        String choice = IO.readln();

        return choice != null && choice.trim().equals("1");
    }
}
