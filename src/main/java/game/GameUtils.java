package game;

public class GameUtils {
    public static void pauseForUser() {
        IO.println();
        IO.print("Press ENTER to continue.");
        IO.readln();
    }
    public static void pauseForCombat() {
        IO.println();
        IO.print("Press ENTER to start the fight...");
        IO.readln();
    }
}

