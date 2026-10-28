package be.cegeka.battle.weapon;

public class Axe extends Weapon {

    public static final int AXE_DAMAGE = 3;

    @Override
    public int getPower(Weapon opponentWeapon) {
        return AXE_DAMAGE;
    }

    @Override
    public WeaponCategory getCategory() {
        return WeaponCategory.AXE;
    }
}
