
package combat;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BattleTest {

    @Test
    void constructorRejectsNullHero() {
        Enemy enemy = new Enemy("Enemy", 10, 10, 3, 0, (attacker, target) -> 3);

        assertThrows(IllegalArgumentException.class, () -> new Battle(null, enemy));
    }

    @Test
    void constructorRejectsNullEnemy() {
        Hero hero = new Hero("Hero", 20, 20, 5, 0, (attacker, target) -> 5);

        assertThrows(IllegalArgumentException.class, () -> new Battle(hero, null));
    }

    @Test
    void getResultReturnsOngoingWhileBothCharactersAreAlive() {
        Battle battle = new Battle(
                new Hero("Hero", 20, 20, 5, 0, (attacker, target) -> 5),
                new Enemy("Enemy", 10, 10, 3, 0, (attacker, target) -> 3)
        );

        assertEquals(BattleResult.ONGOING, battle.getResult());
    }

    @Test
    void getResultReturnsHeroWonWhenEnemyIsDefeated() {
        Battle battle = new Battle(
                new Hero("Hero", 20, 20, 5, 0, (attacker, target) -> 5),
                new Enemy("Enemy", 0, 10, 3, 0, (attacker, target) -> 3)
        );

        assertEquals(BattleResult.HERO_WON, battle.getResult());
    }

    @Test
    void getResultReturnsHeroLostWhenHeroIsDefeated() {
        Battle battle = new Battle(
                new Hero("Hero", 0, 20, 5, 0, (attacker, target) -> 5),
                new Enemy("Enemy", 10, 10, 3, 0, (attacker, target) -> 3)
        );

        assertEquals(BattleResult.HERO_LOST, battle.getResult());
    }

    @Test
    void playRoundAppliesHeroAndEnemyAttacks() {
        Hero hero = new Hero("Hero", 20, 20, 5, 0, (attacker, target) -> 5);
        Enemy enemy = new Enemy("Enemy", 10, 10, 3, 0, (attacker, target) -> 3);
        Battle battle = new Battle(hero, enemy);

        BattleResult result = battle.playRound();

        assertEquals(BattleResult.ONGOING, result);
        assertEquals(17, hero.getHealth());
        assertEquals(5, enemy.getHealth());
    }

    @Test
    void playRoundDoesNotLetDefeatedEnemyCounterattack() {
        AtomicInteger enemyAttackCount = new AtomicInteger();
        Hero hero = new Hero("Hero", 20, 20, 10, 0, (attacker, target) -> 10);
        Enemy enemy = new Enemy("Enemy", 5, 5, 3, 0, (attacker, target) -> {
            enemyAttackCount.incrementAndGet();
            return 3;
        });
        Battle battle = new Battle(hero, enemy);

        BattleResult result = battle.playRound();

        assertEquals(BattleResult.HERO_WON, result);
        assertEquals(0, enemyAttackCount.get());
        assertEquals(20, hero.getHealth());
        assertEquals(0, enemy.getHealth());
    }

    @Test
    void playRoundDoesNotAttackWhenBattleHasAlreadyEnded() {
        AtomicInteger attackCount = new AtomicInteger();
        Hero hero = new Hero("Hero", 0, 20, 5, 0, (attacker, target) -> {
            attackCount.incrementAndGet();
            return 5;
        });
        Enemy enemy = new Enemy("Enemy", 10, 10, 3, 0, (attacker, target) -> {
            attackCount.incrementAndGet();
            return 3;
        });
        Battle battle = new Battle(hero, enemy);

        assertEquals(BattleResult.HERO_LOST, battle.playRound());
        assertEquals(0, attackCount.get());
        assertEquals(10, enemy.getHealth());
    }
}
