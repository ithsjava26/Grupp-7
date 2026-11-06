package combat;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the combat between a player and a list of enemies.
 */
public class CombatManager {

    public CombatResult startCombat(GameCharacter player, List<Enemy> enemies) {
        if (player == null || enemies == null || enemies.isEmpty()) {
            throw new IllegalArgumentException("player and enemies can not be null or empty");
        }
        List<Enemy> opponents = new ArrayList<>(enemies);

        IO.println("=== The battle begins! ===");
        while (true) {
            showStatus(player, opponents);

            playerTurn(player, opponents);
            if (livingEnemies(opponents).isEmpty()) {
                IO.println("All enemies have been defeated!");
                return CombatResult.VICTORY;
            }

            enemyTurns(player, opponents);
            if (!player.isAlive()) {
                IO.println(player.getName() + " has been defeated...");
                return CombatResult.DEFEAT;
            }
        }
    }

    private void playerTurn(GameCharacter player, List<Enemy> opponents) {
        Enemy target = chooseTarget(opponents);
        performAttack(player, target);
        if (!target.isAlive()) {
            IO.println(target.getName() + " is defeated!");
        }
    }

    private void enemyTurns(GameCharacter player, List<Enemy> opponents) {
        for (Enemy enemy : livingEnemies(opponents)) {
            performAttack(enemy, player);
            if (!player.isAlive()) {
                return;
            }
        }
    }

    private Enemy chooseTarget(List<Enemy> opponents) {
        List<Enemy> alive = livingEnemies(opponents);
        if (alive.size() == 1) {
            return alive.get(0);
        }
        while (true) {
            IO.println("Choose an enemy to attack:");
            for (int i = 0; i < alive.size(); i++) {
                IO.println((i + 1) + ". " + alive.get(i).getName());
            }
            String input = IO.readln().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= alive.size()) {
                    return alive.get(choice - 1);
                }
            } catch (NumberFormatException ignored) {
                // not a number, fall through to the invalid choice message below.
            }
            IO.println("Invalid choice, try again.");
        }
    }

    private void performAttack(GameCharacter attacker, GameCharacter target) {
        int damage = attacker.getAttackStrategy().calculateDamage(attacker, target);
        target.takeDamage(damage);
        IO.println(attacker.getName() + " attacks " + target.getName()
                + " for " + damage + " damage.");
    }

    private List<Enemy> livingEnemies(List<Enemy> opponents) {
        List<Enemy> alive = new ArrayList<>();
        for (Enemy enemy : opponents) {
            if (enemy.isAlive()) {
                alive.add(enemy);
            }
        }
        return alive;
    }

    private void showStatus(GameCharacter player, List<Enemy> opponents) {
        IO.println("--------------------");
        IO.println(player.getName() + ": " + player.getHealth() + "/" + player.getMaxHealth() + " HP");
        for (Enemy enemy : opponents) {
            String status = enemy.isAlive()
                    ? enemy.getHealth() + "/" + enemy.getMaxHealth() + " HP"
                    : "defeated";
            IO.println(enemy.getName() + ": " + status);
        }
        IO.println("--------------------");
    }
}