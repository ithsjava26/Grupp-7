
public class MainMenu {

    public static void main(String[] args) {

        MainMenu mainMenu = new MainMenu();

        while (true) {
            mainMenu.displayMenu();

            String choice = IO.readln();

            switch (choice) {
                case "1":
                    // Start new game
                    break;

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
}

