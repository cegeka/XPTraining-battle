package be.cegeka.battle;

import java.util.Optional;

public class War {

    private final Army victor;

    public War(Army attacker, Army defender) {
        victor = determineVictor(attacker, defender);
    }

    private Army determineVictor(Army attacker, Army defender) {
        Optional<Soldier> attackerFrontMan = attacker.getFrontMan();
        Optional<Soldier> defenderFrontMan = defender.getFrontMan();
        if (attackerFrontMan.isPresent() && defenderFrontMan.isPresent()) {
            startFrontMenFight(attacker, defender, attackerFrontMan.get(), defenderFrontMan.get());
            return determineVictor(attacker, defender);
        }

        if (attackerFrontMan.isEmpty()) {
            defender.reportVictory();
            return defender;
        } else {
            attacker.reportVictory();
            return attacker;
        }
    }

    private void startFrontMenFight(Army attacker, Army defender, Soldier attackerFrontMan, Soldier defenderFrontMan) {
        Fight fight = new Fight(attackerFrontMan, defenderFrontMan);
        if (fight.didAttackerWin()) {
            defender.killFrontMan();
        } else {
            attacker.killFrontMan();
        }
    }

    public Army getVictor() {
        return victor;
    }
}
