package be.cegeka.battle.weapon;

public class Sword extends Weapon {
    @Override
    public int getPower(Weapon opponentWeapon) {
        return 2;
    }

    @Override
    public WeaponCategory getCategory() {
        return WeaponCategory.SWORD;
    }
}
