package be.cegeka.battle;

import static be.cegeka.battle.TypeMatchupCalculator.calculateBonus;

public class Fight {

    private final Soldier winner;

    private final Soldier attacker;

    public Fight(Soldier attacker, Soldier defender) {
        this.attacker = attacker;
        winner = determineWinner(attacker, defender);
    }

    private Soldier determineWinner(Soldier attacker, Soldier defender) {
        int attackerPower = determineAttackerPower(attacker, defender);
        int defenderPower = defender.getWeapon().getPower(attacker.getWeapon());
        if (attackerPower >= defenderPower) {
            return attacker;
        }
        return defender;
    }

    private static int determineAttackerPower(Soldier attacker, Soldier defender) {
        return attacker.getWeapon().getPower(defender.getWeapon()) + calculateBonus(attacker.getWeapon(), defender.getWeapon());
    }

    public Soldier getWinner() {
        return winner;
    }

    public boolean didAttackerWin() {
        return winner.equals(attacker);
    }
}
