package game;

import combat.Hero;
import org.junit.jupiter.api.Test;
import roomMechanics.ExplorationAction;
import roomMechanics.Room;

import java.lang.reflect.Field;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {

    private Object getField(GameEngine engine, String fieldName)
            throws Exception {
        Field field = GameEngine.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(engine);
    }

    @Test
    void newGameShouldCreatePlayerWithCorrectName() throws Exception {
        GameEngine engine = new GameEngine("Alex");

        Hero player = (Hero) getField(engine, "player");

        assertEquals("Alex", player.getName());
        assertEquals(100, player.getHealth());
        assertEquals(100, player.getMaxHealth());
    }

    @Test
    void newGameShouldHaveNoCompletedExplorationActions()
            throws Exception {
        GameEngine engine = new GameEngine("Alex");

        @SuppressWarnings("unchecked")
        Set<ExplorationAction> completedActions =
                (Set<ExplorationAction>) getField(engine, "completedActions");

        assertTrue(completedActions.isEmpty());
    }

    @Test
    void newGameShouldHaveNoCompletedCombatEncounters()
            throws Exception {
        GameEngine engine = new GameEngine("Alex");

        @SuppressWarnings("unchecked")
        Set<String> completedEncounters =
                (Set<String>) getField(engine, "completedEncounters");

        assertTrue(completedEncounters.isEmpty());
    }
}