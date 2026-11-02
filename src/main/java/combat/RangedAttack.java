package combat;

/**
 * Implements a ranged damage calculation strategy for a combat system.
 * This strategy calculates damage by reducing the attacker's attack value
 * by half of the target's defense value. The resulting damage is guaranteed
 * to be a minimum of 1, ensuring that an attack always inflicts at least
 * some damage regardless of the defense value.
 */
public class RangedAttack implements AttackStrategy {

    @Override
    public int calculateDamage(GameCharacter attacker, GameCharacter target) {
        return Math.max(1, attacker.getAttack() - target.getDefense() / 2);
    }
}
