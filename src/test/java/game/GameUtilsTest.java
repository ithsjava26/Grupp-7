
package game;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GameUtilsTest {

    private String captureNarrative(String text) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output));
            GameUtils.printNarrative(text);
        } finally {
            System.setOut(originalOut);
        }

        return output.toString().stripTrailing();
    }

    @Test
    void shortTextShouldNotWrap() {
        String result = captureNarrative("The office is empty.");

        assertEquals("The office is empty.", result);
    }

    @Test
    void longTextShouldWrapOntoMultipleLines() {
        String text = "The abandoned streets are filled with "
                + "strange noises and distant screams. ".repeat(4);

        String result = captureNarrative(text);

        assertTrue(result.contains("\n"));
    }

    @Test
    void wrappedTextShouldPreserveAllWords() {
        String text = "The zombie approaches slowly from the darkness. ".repeat(5);

        String result = captureNarrative(text);

        assertEquals(
                text.trim().replaceAll("\\s+", " "),
                result.replaceAll("\\s+", " ")
        );
    }
}
