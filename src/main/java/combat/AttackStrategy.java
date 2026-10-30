package combat;

/**
 * Decides how much damage an attack does, based on the attacker and the target.
 */
public interface AttackStrategy {
    int calculateDamage(GameCharacter attacker, GameCharacter target);
}
