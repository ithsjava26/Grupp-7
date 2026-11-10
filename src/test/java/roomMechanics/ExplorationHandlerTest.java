package roomMechanics;

import audio.SoundManager;
import combat.Hero;
import combat.MeleeAttack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExplorationHandlerTest {

    private ExplorationHandler handler;
    private Hero player;

    @BeforeEach
    void setUp() {
        // Disable sound effects during tests.
        SoundManager silentSoundManager = new SoundManager() {
            @Override
            public void playEffect(Effect effect) {
                // Intentionally empty.
            }
        };

        handler = new ExplorationHandler(silentSoundManager);

        player = new Hero(
                "Alex",
                100,
                100,
                15,
                5,
                new MeleeAttack()
        );
    }


    @Test
    void searchingDesksShouldAddTwoItems() {
        handler.handleExploration(
                ExplorationAction.SEARCH_DESKS, "Alex", player);

        assertEquals(2, player.getInventory().getSize());
        assertTrue(player.getInventory().hasItem("Painkillers"));
        assertTrue(player.getInventory().hasItem("Energy Drink"));
    }


    @Test
    void searchingAbandonedCarShouldAddBandage() {
        handler.handleExploration(
                ExplorationAction.INSPECT_ABANDONED_CAR, "Alex", player);

        assertEquals(1, player.getInventory().getSize());
        assertTrue(player.getInventory().hasItem("Bandage"));
    }

    @Test
    void observingFiguresShouldNotAddItems() {
        handler.handleExploration(
                ExplorationAction.OBSERVE_FIGURES, "Alex", player);

        assertTrue(player.getInventory().isEmpty());
    }
}