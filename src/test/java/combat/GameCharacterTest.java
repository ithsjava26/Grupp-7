package combat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameCharacterTest {

    @Test
    void getAttackStrategy() {
        AttackStrategy strategy = (attacker, target) -> 4;
        Hero character = new Hero("Hero", 20, 20, 8, 3, strategy);

        assertSame(strategy, character.getAttackStrategy());
    }

    @Test
    void setAttackStrategy() {
        Hero character = new Hero("Hero", 20, 20, 8, 3, (attacker, target) -> 4);
        AttackStrategy newStrategy = (attacker, target) -> 7;

        character.setAttackStrategy(newStrategy);

        assertSame(newStrategy, character.getAttackStrategy());
    }

    @Test
    void setAttackStrategyRejectsNull() {
        Hero character = new Hero("Hero", 20, 20, 8, 3, (attacker, target) -> 4);

        assertThrows(IllegalArgumentException.class, () -> character.setAttackStrategy(null));
    }

    @Test
    void getName() {
        Hero character = new Hero("Hero", 20, 20, 8, 3, (attacker, target) -> 4);

        assertEquals("Hero", character.getName());
    }

    @Test
    void getHealth() {
        Hero character = new Hero("Hero", 15, 20, 8, 3, (attacker, target) -> 4);

        assertEquals(15, character.getHealth());
    }

    @Test
    void getMaxHealth() {
        Hero character = new Hero("Hero", 15, 20, 8, 3, (attacker, target) -> 4);

        assertEquals(20, character.getMaxHealth());
    }

    @Test
    void getAttack() {
        Hero character = new Hero("Hero", 20, 20, 8, 3, (attacker, target) -> 4);

        assertEquals(8, character.getAttack());
    }

    @Test
    void getDefense() {
        Hero character = new Hero("Hero", 20, 20, 8, 3, (attacker, target) -> 4);

        assertEquals(3, character.getDefense());
    }

    @Test
    void isAlive() {
        Hero livingCharacter = new Hero("Hero", 1, 20, 8, 3, (attacker, target) -> 4);
        Hero defeatedCharacter = new Hero("Defeated", 0, 20, 8, 3, (attacker, target) -> 4);

        assertTrue(livingCharacter.isAlive());
        assertFalse(defeatedCharacter.isAlive());
    }

    @Test
    void takeDamageReducesHealthAndDoesNotGoBelowZero() {
        Hero character = new Hero("Hero", 10, 20, 8, 3, (attacker, target) -> 4);

        character.takeDamage(4);
        assertEquals(6, character.getHealth());

        character.takeDamage(10);
        assertEquals(0, character.getHealth());
    }

    @Test
    void takeDamageRejectsNegativeAmount() {
        Hero character = new Hero("Hero", 10, 20, 8, 3, (attacker, target) -> 4);

        assertThrows(IllegalArgumentException.class, () -> character.takeDamage(-1));
        assertEquals(10, character.getHealth());
    }

    @Test
    void healRestoresHealthWithoutExceedingMaximum() {
        Hero character = new Hero("Hero", 10, 20, 8, 3, (attacker, target) -> 4);

        character.heal(5);
        assertEquals(15, character.getHealth());

        character.heal(10);
        assertEquals(20, character.getHealth());
    }

    @Test
    void healRejectsNegativeAmount() {
        Hero character = new Hero("Hero", 10, 20, 8, 3, (attacker, target) -> 4);

        assertThrows(IllegalArgumentException.class, () -> character.heal(-1));
        assertEquals(10, character.getHealth());
    }
}