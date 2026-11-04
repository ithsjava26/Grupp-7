package game;

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
        IO.println(ConsoleColors.YELLOW + "0." + ConsoleColors.RESET + " Return to Main Menu");
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
            IO.println((i + 1) + ". " + options.get(i).displayName());
        }

        IO.println("0. Go back");
        IO.println();
        IO.print("Choose an option: ");

        return IO.readln();
    }
}

