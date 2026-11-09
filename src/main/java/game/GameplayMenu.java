package game;

import combat.Hero;
import item.Item;
import roomMechanics.ConsoleColors;
import roomMechanics.ExplorationOption;
import java.util.List;

public class GameplayMenu {

    public void displayMenu(String nextRoomName) {
        IO.println();
        IO.println("What do you want to do?");
        IO.println();
        IO.println(ConsoleColors.YELLOW + "1." + ConsoleColors.RESET + " Explore");
        IO.println(ConsoleColors.YELLOW + "2." + ConsoleColors.RESET + " View inventory");
        IO.println(ConsoleColors.YELLOW + "3." + ConsoleColors.RESET + " View character status");
        IO.println(ConsoleColors.YELLOW + "4." + ConsoleColors.RESET + " Continue to " + nextRoomName);
        IO.println(ConsoleColors.YELLOW + "5." + ConsoleColors.RESET + " Save game");
        IO.println(ConsoleColors.YELLOW + "e." + ConsoleColors.RESET + " Return to Main Menu");
        IO.println();
        IO.print("Choose an option: ");
    }

    public String runMenu(String nextRoomName) {
        displayMenu(nextRoomName);
        return IO.readln();
    }

    public String runExplorationMenu(List<ExplorationOption> options) {
        IO.println();

        for (int i = 0; i < options.size(); i++) {
            IO.println(ConsoleColors.YELLOW + (i + 1) + ". " + ConsoleColors.RESET
                    + options.get(i).displayName());
        }

        IO.println(ConsoleColors.YELLOW + "e. " + ConsoleColors.RESET + "Go back");
        IO.println();
        IO.print("Choose an option: ");

        return IO.readln();
    }

    public void displayInventory(Hero player) {
        IO.println();
        IO.println(ConsoleColors.YELLOW + "=== INVENTORY ===" + ConsoleColors.RESET);
        IO.println();

        if (player.getInventory().isEmpty()) {
            IO.println("Your inventory is empty.");
            IO.println();
            return;
        }

        IO.println("Items (" + player.getInventory().getSize()
                + "/" + player.getInventory().getCapacity() + "):");
        IO.println();

        for (int i = 0; i < player.getInventory().getSize(); i++) {
            Item item = player.getInventory().getItem(i);

            String equippedStatus = "";

            if (item == player.getEquippedWeapon()
                    || item == player.getEquippedArmor()) {
                equippedStatus = ConsoleColors.GREEN + " [EQUIPPED]"
                        + ConsoleColors.RESET;
            }

            IO.println(ConsoleColors.YELLOW + (i + 1) + ". "
                    + ConsoleColors.RESET + item.getName() + equippedStatus);
        }

        IO.println();
    }

    public String runInventoryMenu(Hero player) {
        displayInventory(player);

        if (player.getInventory().isEmpty()) {
            IO.println(ConsoleColors.YELLOW + "e. " + ConsoleColors.RESET + "Go back");
            IO.println();
            IO.print("Choose an option: ");
            return IO.readln();
        }

        IO.println(ConsoleColors.YELLOW + "e. " + ConsoleColors.RESET + "Go back");
        IO.println();
        IO.print("Choose an item: ");

        return IO.readln();
    }
}

