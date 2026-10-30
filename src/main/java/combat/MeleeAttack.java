package combat;

/**
 * Implements a melee-based damage calculation strategy for a combat system.
 * This strategy calculates damage by subtracting the target's defense
 * from the attacker's attack value. The resulting damage is guaranteed
 * to be a minimum of 1, ensuring that an attack always inflicts at
 * least some damage regardless of the defense value.
 */
public class MeleeAttack implements AttackStrategy {

    @Override
    public int calculateDamage(GameCharacter attacker, GameCharacter target) {
        return Math.max(1, attacker.getAttack() - target.getDefense());
    }
}
