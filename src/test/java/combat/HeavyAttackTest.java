package combat;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HeavyAttackTest {

    @Test
    void calculateDamageReturnsZeroWhenAttackMisses() {
        HeavyAttack attack = new HeavyAttack(randomReturning(0.75));
        Hero attacker = new Hero("Hero", 20, 20, 10, 0, (a, t) -> 0);
        Enemy target = new Enemy("Enemy", 20, 20, 4, 4, (a, t) -> 0);

        assertEquals(0, attack.calculateDamage(attacker, target));
    }

    @Test
    void calculateDamageAppliesMultiplierAndReducedDefenseOnHit() {
        HeavyAttack attack = new HeavyAttack(randomReturning(0.5));
        Hero attacker = new Hero("Hero", 20, 20, 10, 0, (a, t) -> 0);
        Enemy target = new Enemy("Enemy", 20, 20, 4, 4, (a, t) -> 0);

        assertEquals(13, attack.calculateDamage(attacker, target));
    }

    @Test
    void calculateDamageHasMinimumOfOneOnHit() {
        HeavyAttack attack = new HeavyAttack(randomReturning(0.5));
        Hero attacker = new Hero("Hero", 20, 20, 1, 0, (a, t) -> 0);
        Enemy target = new Enemy("Enemy", 20, 20, 100, 100, (a, t) -> 0);

        assertEquals(1, attack.calculateDamage(attacker, target));
    }

    private Random randomReturning(double value) {
        return new Random() {
            @Override
            public double nextDouble() {
                return value;
            }
        };
    }
}