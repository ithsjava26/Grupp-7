package combat;

public class Hero extends GameCharacter {

    public Hero(String name, int health, int maxHealth, int attack, int defense, AttackStrategy attackStrategy) {
        super(name, health, maxHealth, attack, defense, attackStrategy);
    }
    public Hero(String name, int health, int maxHealth, int attack, int defense) {
        this(name,health,maxHealth,attack,defense, new MeleeAttack());
    }
}


