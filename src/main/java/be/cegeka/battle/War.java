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
            return startFrontMenFight(attacker, defender, attackerFrontMan.get(), defenderFrontMan.get());
        }

        if (attackerFrontMan.isEmpty()) {
            defender.reportVictory();
            return defender;
        } else {
            attacker.reportVictory();
            return attacker;
        }
    }

    private Army startFrontMenFight(Army attacker, Army defender, Soldier attackerFrontMan, Soldier defenderFrontMan) {
        Fight fight = new Fight(attackerFrontMan, defenderFrontMan);
        if (fight.didAttackerWin()) {
            defender.killFrontMan();
        } else {
            attacker.killFrontMan();
        }
        return determineVictor(attacker, defender);
    }

    public Army getVictor() {
        return victor;
    }
}
