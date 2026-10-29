package combat;

public class Enemy extends GameCharacter {

    public Enemy(String name, int health, int maxHealth, int attack, int defense, AttackStrategy attackStrategy ) {
        super(name, health, maxHealth, attack, defense,attackStrategy);
    }

    public Enemy(String name, int health, int maxHealth, int attack, int defense) {
        this(name,health,maxHealth,attack,defense, new MeleeAttack());
    }
}
