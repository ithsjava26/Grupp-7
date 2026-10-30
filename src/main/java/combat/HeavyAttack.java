package combat;

import java.util.Random;

/**
 * Implements a heavy attack damage calculation strategy for a combat system.
 * This strategy inflicts high damage (scaling attacker's attack power by 2)
 * with a chance of missing entirely. Guaranteed minimum damage applies on hit.
 */
public class HeavyAttack implements AttackStrategy {

    private static final double HIT_CHANCE = 0.75;
    private static final int DAMAGE_MULTIPLIER = 2;
    private static final int MINIMUM_DAMAGE = 1;

    private final Random random;

    public HeavyAttack() {
        this(new Random());
    }

    public HeavyAttack(Random random) {
        this.random = random;
    }

    @Override
    public int calculateDamage(GameCharacter attacker, GameCharacter target) {
        if (!isHit()) {
            return 0;
        }

        int rawDamage = attacker.getAttack() * DAMAGE_MULTIPLIER - target.getDefense();
        return Math.max(MINIMUM_DAMAGE, rawDamage);
    }

    private boolean isHit() {
        return random.nextDouble() < HIT_CHANCE;
    }
}