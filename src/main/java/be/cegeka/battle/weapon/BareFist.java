package be.cegeka.battle.weapon;

public class BareFist extends Weapon {
    @Override
    public int getPower(Weapon opponentWeapon) {
        return 1;
    }

    @Override
    public WeaponCategory getCategory() {
        return WeaponCategory.NONE;
    }
}
