package be.cegeka.battle.weapon;

public class Trident extends Weapon {
    @Override
    public int getPower(Weapon opponentWeapon) {
        return 3 * Spear.SPEAR_DAMAGE;
    }

    @Override
    public WeaponCategory getCategory() {
        return WeaponCategory.SPEAR;
    }
}
