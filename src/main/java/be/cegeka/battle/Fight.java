package be.cegeka.battle;

public class Fight {

    private final Soldier winner;

    private final Soldier attacker;
//    private final Soldier defender;

    public Fight(Soldier attacker, Soldier defender) {
        this.attacker = attacker;
//        this.defender = defender;

        winner = determineWinner(attacker, defender);
    }

    private Soldier determineWinner(Soldier attacker, Soldier defender) {
        if (attacker.getWeapon().getPower() > defender.getWeapon().getPower()) {
            return attacker;
        } else if (attacker.getWeapon().getPower() < defender.getWeapon().getPower()) {
            return defender;
        } else {
            return attacker;
        }
    }

    public Soldier getWinner() {
        return winner;
    }

    public boolean didAttackerWin() {
        return winner.equals(attacker);
    }
}
