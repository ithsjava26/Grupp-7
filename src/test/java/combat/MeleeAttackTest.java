package combat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeleeAttackTest {

    @Test
    void calculateDamageSubtractsHalfOfTargetDefense() {
        MeleeAttack attack = new MeleeAttack();
        Hero attacker = new Hero("Hero", 20, 20, 10, 0, attack);
        Enemy target = new Enemy("Enemy", 20, 20, 4, 6, attack);

        assertEquals(7, attack.calculateDamage(attacker, target));
    }

    @Test
    void calculateDamageHasMinimumOfOne() {
        MeleeAttack attack = new MeleeAttack();
        Hero attacker = new Hero("Hero", 20, 20, 1, 0, attack);
        Enemy target = new Enemy("Enemy", 20, 20, 4, 100, attack);

        assertEquals(1, attack.calculateDamage(attacker, target));
    }
}