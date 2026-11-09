package combat;

import inventory.Inventory;
import inventory.ItemUsage;
import item.Food;
import item.Item;
import item.Medkit;
import roomMechanics.ConsoleColors;

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
        while (true) {
            IO.println();
            IO.println("What do you want to do?");
            IO.println("1. Attack");
            IO.println("2. Use item");
            IO.println();
            IO.print("Choose an action: ");

            String choice = IO.readln();

            if (choice == null) {
                continue;
            }

            switch (choice.trim()) {
                case "1":
                    Enemy target = chooseTarget(opponents);
                    performAttack(player, target);

                    if (!target.isAlive()) {
                        IO.println(target.getName() + " is defeated!");
                    }
                    return;

                case "2":
                    if (player instanceof Hero hero) {
                        boolean itemUsed = useBattleItem(hero);

                        if (itemUsed) {
                            return;
                        }
                    } else {
                        IO.println("You cannot access the inventory.");
                    }
                    break;

                default:
                    IO.println("Invalid choice. Try again.");
            }
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
                IO.println((i + 1) + ". " + ConsoleColors.RED
                        + alive.get(i).getName() + ConsoleColors.RESET);
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

    private boolean useBattleItem(Hero player) {
        Inventory inventory = player.getInventory();
        List<Integer> usableItems = new ArrayList<>();

        IO.println();
        IO.println("=== USABLE ITEMS ===");
        IO.println();

        for (int i = 0; i < inventory.getSize(); i++) {
            Item item = inventory.getItem(i);

            if (item instanceof Food || item instanceof Medkit) {
                usableItems.add(i);
                IO.println(usableItems.size() + ". " + item.getName());
            }
        }

        if (usableItems.isEmpty()) {
            IO.println("You have no usable items.");
            return false;
        }

        IO.println("e. Go back");
        IO.println();
        IO.print("Choose an item: ");

        String input = IO.readln();

        if (input == null || input.isBlank() || input.trim().equals("e")) {
            return false;
        }

        try {
            int choice = Integer.parseInt(input.trim());

            if (choice < 1 || choice > usableItems.size()) {
                IO.println("Invalid item selection.");
                return false;
            }

            int inventoryIndex = usableItems.get(choice - 1);
            Item selectedItem = inventory.getItem(inventoryIndex);

            IO.println();
            IO.println("Are you sure you want to use " + selectedItem.getName() + "?");
            IO.println("1. Yes");
            IO.println("0. No");
            IO.print("Choose an option: ");

            String confirmation = IO.readln();

            if (confirmation != null && confirmation.trim().equals("1")) {
                ItemUsage itemUsage = new ItemUsage(inventory);
                String result = itemUsage.useItem(inventoryIndex, player);
                IO.println(result);

                return result.startsWith("You used ")
                        || result.startsWith("You ate ");
            } else {
                IO.println("Item use cancelled.");
                return false;
            }

        } catch (NumberFormatException e) {
            IO.println("Please enter a valid number.");
            return false;
        }
    }

    private void performAttack(GameCharacter attacker, GameCharacter target) {
        int damage = attacker.getAttackStrategy().calculateDamage(attacker, target);

        if (damage > 0 && attacker instanceof Hero hero && hero.consumeEnergyBoost()) {
            damage *= 2;
            IO.println(ConsoleColors.YELLOW + "[SYSTEM] Energy boost activated! Double damage!"
                    + ConsoleColors.RESET);
        }

        target.takeDamage(damage);

        String attackerName;
        String targetName;

        if (attacker instanceof Hero) {
            attackerName = ConsoleColors.GREEN + attacker.getName() + ConsoleColors.RESET;
        } else {
            attackerName = ConsoleColors.RED + attacker.getName() + ConsoleColors.RESET;
        }

        if (target instanceof Hero) {
            targetName = ConsoleColors.GREEN + target.getName() + ConsoleColors.RESET;
        } else {
            targetName = ConsoleColors.RED + target.getName() + ConsoleColors.RESET;
        }

        IO.println(attackerName + " attacks " + targetName + " for "
                + ConsoleColors.RED + damage + ConsoleColors.RESET + " damage.");
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
        IO.println();
        IO.println("--------------------");
        IO.println("=== BATTLE STATUS ===");
        IO.println();

        IO.println(ConsoleColors.GREEN + player.getName() + ConsoleColors.RESET
                + ": " + player.getHealth() + "/" + player.getMaxHealth() + " HP");

        IO.println("Attack: " + player.getAttack()
                + " | Defence: " + player.getDefense());

        if (player instanceof Hero hero) {
            if (hero.getEquippedWeapon() != null) {
                IO.println("Weapon: " + hero.getEquippedWeapon().getName());
            }

            if (hero.getEquippedArmor() != null) {
                IO.println("Armour: " + hero.getEquippedArmor().getName());
            }
        }

        IO.println();
        IO.println("--- ENEMIES ---");

        for (Enemy enemy : opponents) {
            String status = enemy.isAlive()
                    ? enemy.getHealth() + "/" + enemy.getMaxHealth() + " HP"
                    : "defeated";

            IO.println(ConsoleColors.RED + enemy.getName() + ConsoleColors.RESET
                    + ": " + status);
        }

        IO.println("--------------------");
    }

}
