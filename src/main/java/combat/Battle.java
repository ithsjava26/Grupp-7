package combat;

/**
 * Represents a battle between a hero and an enemy.
 * It manages the combat rounds and determines the outcome of the battle.
 */
public class Battle {
    private final Hero hero;
    private final Enemy enemy;

    public Battle(Hero hero, Enemy enemy) {
        if (hero == null || enemy == null) {
            throw new IllegalArgumentException("hero and enemy can not be null");
        }
        this.hero = hero;
        this.enemy = enemy;
    }

    public BattleResult getResult() {
        if (!hero.isAlive()) {
            return BattleResult.HERO_LOST;
        }
        if (!enemy.isAlive()) {
            return BattleResult.HERO_WON;
        }
        return BattleResult.ONGOING;
    }

    public BattleResult playRound() {
        if (getResult() != BattleResult.ONGOING) {
            return getResult();
        }
        attack(hero, enemy);
        if (enemy.isAlive()) {
            attack(enemy, hero);
        }
        return getResult();
    }

    private void attack(GameCharacter attacker, GameCharacter target) {
        int damage = attacker.getAttackStrategy().calculateDamage(attacker, target);
        target.takeDamage(damage);
    }
}
