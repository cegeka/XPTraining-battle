package be.cegeka.battle;


import com.google.common.base.Strings;

import java.util.Objects;
import java.util.Optional;

public class Soldier {

    private final String name;
    private Weapon weapon;
    private Integer id;

    public Soldier(String name) {
        validateName(name);

        this.name = name;
        this.weapon = Weapon.BARE_FIST;
    }

    private static void validateName(String name) {
        if (Strings.isNullOrEmpty(name) || name.isBlank()) {
            throw new IllegalArgumentException("A soldier must have a name");
        }
    }

    String getName() {
        return this.name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void equip(Weapon weapon) {
        this.weapon = weapon;
    }

    public void assignId(int id) {
        if (this.id != null) {
            throw new IllegalStateException("Soldier already has an id");
        }
        this.id = id;
    }

    public Optional<Integer> getId() {
        return Optional.ofNullable(id);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Soldier soldier = (Soldier) o;
        return Objects.equals(name, soldier.name) && weapon == soldier.weapon;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weapon);
    }
}
