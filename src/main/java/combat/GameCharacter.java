package combat;

public record GameCharacter(
        String name,
        int health,
        int maxHealth,
        int attack,
        int defense
) {
    public GameCharacter {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can not be null or empty");
        }

        if (maxHealth <=0) {
            throw new IllegalArgumentException("maxHealth must be greater than 0");
        }
        if (health > maxHealth) {
            health = maxHealth;
        }
        if (health < 0) {
            health = 0;
        }
    }
    public boolean isAlive() {
        return health >0;
    }
    public GameCharacter takeDamage(int amount) {
        return new GameCharacter(name, health - amount, maxHealth, attack, defense);
    }
    public GameCharacter heal(int amount) {
        return new GameCharacter(name, health + amount, maxHealth, attack, defense);
    }
}
