package be.cegeka.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Army {

    private final List<Soldier> soldiers;
    private final Headquarters headquarters;

    public Army(Headquarters headquarters) {
        this.headquarters = headquarters;
        this.soldiers = new ArrayList<>();
    }

    public void enroll(Soldier soldier) {
        int soldierId = this.headquarters.reportEnlistment(soldier.getName());
        soldier.assignId(soldierId);

        this.soldiers.add(soldier);
    }

    public List<Soldier> getSoldiers() {
        return soldiers;
    }

    public Optional<Soldier> getFrontMan() {
        if (soldiers.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(soldiers.getFirst());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(soldiers, army.soldiers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(soldiers);
    }

    public void killFrontMan() {
        if (!soldiers.isEmpty()) {
            headquarters.reportCasualty(soldiers.getFirst().getId().orElseThrow());
            soldiers.removeFirst();
        }
    }

    public void reportVictory() {
        headquarters.reportVictory(soldiers.size());
    }
}
