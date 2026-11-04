package game;

import roomMechanics.ConsoleColors;

public class GameUtils {

    private static final int CONSOLE_WIDTH = 100;

    public static void printNarrative(String text) {
        IO.println(wrapText(text));
    }

    public static void printDialogue(String text) {
        printFormatted(text, ConsoleColors.BLUE);
    }

    public static void printSystemMessage(String text) {
        printFormatted(text, ConsoleColors.YELLOW);
    }

    public static void printWarning(String text) {
        printFormatted(text, ConsoleColors.RED);
    }

    private static void printFormatted(String text, String color) {
        IO.println(color + wrapText(text) + ConsoleColors.RESET);
    }

    private static String wrapText(String text) {
        StringBuilder result = new StringBuilder();
        int lineLength = 0;

        for (String word : text.split("\\s+")) {
            if (lineLength + word.length() > CONSOLE_WIDTH) {
                result.append("\n");
                lineLength = 0;
            } else if (lineLength > 0) {
                result.append(" ");
                lineLength++;
            }

            result.append(word);
            lineLength += word.length();
        }

        return result.toString();
    }

    public static void pauseForUser() {
        IO.println();
        IO.print("Press ENTER to continue...");
        IO.readln();
        IO.println();
        IO.println();
    }

    public static void pauseForCombat() {
        IO.println();
        IO.print("Press ENTER to start the fight...");
        IO.readln();
    }

    public static void pauseAfterRoom() {
        String divider = ConsoleColors.YELLOW
                + "========================================"
                + ConsoleColors.RESET;

        IO.println();
        IO.println(divider);
        IO.print("Press ENTER to continue.");
        IO.readln();
    }
}