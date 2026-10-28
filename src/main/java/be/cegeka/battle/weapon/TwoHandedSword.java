package be.cegeka.battle.weapon;

public class TwoHandedSword extends Weapon {
    @Override
    public int getPower(Weapon opponentWeapon) {
        return 5;
    }

    @Override
    public WeaponCategory getCategory() {
        return WeaponCategory.SWORD;
    }
}
