public class GameplayMenu {

    public void displayMenu() {
        IO.println();
        IO.println("What do you want to do?");
        IO.println();
        IO.println("1. Explore");
        IO.println("2. Inventory");
        IO.println("3. View character status");
        IO.println("3. Save Game");
        IO.println("4. Return to Main Menu");
        IO.println();
        IO.print("Choose an option: ");
    }
    public void runMenu() {
        while (true) {
            displayMenu();

            String choice = IO.readln();

            switch (choice) {
                case "1":
                    IO.println("You look around for supplies...");
                    GameUtils.pauseForUser();
                    break;
                case "2":
                    IO.println("Your inventory is empty.");
                    GameUtils.pauseForUser();
                    break;
                case "3":
                    IO.println("Save game is not implemented yet.");
                    GameUtils.pauseForUser();
                    break;
                case "4":
                    return;
                default:
                    IO.println("Invalid option. Please try again.");
                    GameUtils.pauseForUser();
            }
        }
    }
}
