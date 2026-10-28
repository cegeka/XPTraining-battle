package be.cegeka.battle.weapon;

import static be.cegeka.battle.weapon.Axe.AXE_DAMAGE;

public class BroadAxe extends Weapon {
    @Override
    public int getPower(Weapon opponentWeapon) {
        return 2 + AXE_DAMAGE;
    }

    @Override
    public WeaponCategory getCategory() {
        return WeaponCategory.AXE;
    }
}
