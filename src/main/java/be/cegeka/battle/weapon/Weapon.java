package be.cegeka.battle.weapon;

import java.util.Objects;

public abstract class Weapon {

    public abstract int getPower(Weapon opponentWeapon);

    public abstract WeaponCategory getCategory();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
        return getPowerAgainstBareFist() == weapon.getPowerAgainstBareFist();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), getPowerAgainstBareFist());
    }

    private int getPowerAgainstBareFist() {
        return getPower(new BareFist());
    }
}
