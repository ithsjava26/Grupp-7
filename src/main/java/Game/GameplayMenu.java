package Game;

public class GameplayMenu {

    public void displayMenu(String nextRoomName) {
        IO.println();
        IO.println("What do you want to do?");
        IO.println();
        IO.println("1. Explore");
        IO.println("2. Inventory");
        IO.println("3. View character status");
        IO.println("4. Save Game");
        IO.println("5. Continue to " + nextRoomName);
        IO.println("6. Return to Main Menu");
        IO.println();
        IO.print("Choose an option: ");
    }

    public String runMenu(String nextRoomName) {
        displayMenu(nextRoomName);
        return IO.readln();
    }
}

