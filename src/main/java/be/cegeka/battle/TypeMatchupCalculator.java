package be.cegeka.battle;

import be.cegeka.battle.weapon.Weapon;

public class TypeMatchupCalculator {

    public static int calculateBonus(Weapon attackerWeapon, Weapon defenderWeapon) {
        if(attackerWeapon.getCategory().hasAdvantageAgainst(defenderWeapon.getCategory())) {
            return 3;
        }
        return 0;
    }
}
