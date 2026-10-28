package be.cegeka.battle.weapon;

public class MagicPotion extends Weapon {
    @Override
    public int getPower(Weapon opponentWeapon) {
        // Workaround to avoid stack overflow when comparing two potions.
        // Make this check more generic if more weapons are introduced with magic potion-like power calculation logic
        if(opponentWeapon.getCategory() == WeaponCategory.MAGIC) {
            return 10;
        }

        return opponentWeapon.getPower(this) % 2 == 0 ? 10 : 0;
    }

    @Override
    public WeaponCategory getCategory() {
        return WeaponCategory.MAGIC;
    }
}
