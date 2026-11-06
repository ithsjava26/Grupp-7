package combat;

import inventory.Inventory;
import item.Weapon;
import item.Armor;

public class Hero extends GameCharacter {

    private final Inventory inventory;

    private Weapon equippedWeapon;
    private Armor equippedArmor;

    public Hero(String name, int health, int maxHealth, int attack, int defense,
                AttackStrategy attackStrategy) {

        super(name, health, maxHealth, attack, defense, attackStrategy);

        this.inventory = new Inventory();
    }

    // Inventory

    public Inventory getInventory() {
        return inventory;
    }

    // Weapon equipment

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public boolean equipWeapon(Weapon weapon) {
        if (weapon == null || !inventory.hasItem(weapon)) {
            return false;
        }

        equippedWeapon = weapon;
        return true;
    }

    public void unequipWeapon() {
        equippedWeapon = null;
    }

    // Armour equipment

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public boolean equipArmor(Armor armor) {
        if (armor == null || !inventory.hasItem(armor)) {
            return false;
        }

        equippedArmor = armor;
        return true;
    }

    public void unequipArmor() {
        equippedArmor = null;
    }

    // Effective combat stats

    @Override
    public int getAttack() {
        int bonus = equippedWeapon != null
                ? equippedWeapon.getDamageBonus()
                : 0;

        return super.getAttack() + bonus;
    }

    @Override
    public int getDefense() {
        int bonus = equippedArmor != null
                ? equippedArmor.getDefenseBonus()
                : 0;

        return super.getDefense() + bonus;
    }
}