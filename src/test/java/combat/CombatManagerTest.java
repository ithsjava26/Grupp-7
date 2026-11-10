package combat;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombatManagerTest {

    @Test
    void startCombatRejectsNullPlayer() {
        Enemy enemy = new Enemy("Enemy", 10, 10, 3, 0, (attacker, target) -> 3);

        assertThrows(IllegalArgumentException.class,
                () -> new CombatManager().startCombat(null, List.of(enemy)));
    }

    @Test
    void startCombatRejectsNullEnemies() {
        Hero hero = new Hero("Hero", 20, 20, 5, 0, (attacker, target) -> 5);

        assertThrows(IllegalArgumentException.class,
                () -> new CombatManager().startCombat(hero, null));
    }

    @Test
    void startCombatRejectsEmptyEnemyList() {
        Hero hero = new Hero("Hero", 20, 20, 5, 0, (attacker, target) -> 5);

        assertThrows(IllegalArgumentException.class,
                () -> new CombatManager().startCombat(hero, List.of()));
    }
}