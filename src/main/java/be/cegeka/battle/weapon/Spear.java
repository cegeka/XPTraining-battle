package be.cegeka.battle.weapon;

public class Spear extends Weapon {

    public static final int SPEAR_DAMAGE = 2;

    @Override
    public int getPower(Weapon opponentWeapon) {
        return SPEAR_DAMAGE;
    }

    @Override
    public WeaponCategory getCategory() {
        return WeaponCategory.SPEAR;
    }
}
