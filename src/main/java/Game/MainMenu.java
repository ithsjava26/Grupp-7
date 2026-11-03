package Game;

public class MainMenu {

    public void displayMenu() {
        IO.println("==============================");
        IO.println("       THE LAST SHIFT");
        IO.println("==============================");
        IO.println();
        IO.println("1. Start New Game");
        IO.println("2. Load Game");
        IO.println("3. Exit");
        IO.println();
        IO.print("Choose an option: ");
    }

    static void main() {
        MainMenu mainMenu = new MainMenu();

        while (true) {
            mainMenu.displayMenu();

            String choice = IO.readln();

            switch (choice) {
                case "1": {
                    String playerName = mainMenu.startNewGame();

                    GameEngine gameEngine = new GameEngine(playerName);
                    gameEngine.startGame();
                    break;
                }

                case "2":
                    // Load game
                    break;

                case "3":
                    // Exit game
                    return;

                default:
                    IO.println("Invalid option. Please try again.");
            }
        }
    }

    public String startNewGame() {
        IO.println("Your journey begins...");
        GameUtils.pauseForUser();

        IO.println("Choose a name for your character:");

        String playerName = IO.readln();

        while (playerName.isBlank()) {
            IO.println("Please enter a name:");
            playerName = IO.readln();
        }

        IO.println("Welcome, " + playerName + ".");
        GameUtils.pauseForUser();

        return playerName;
    }
}


