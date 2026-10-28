package be.cegeka.battle;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SoldierTest {

    @Test
    void construction_aSoldierMustHaveAName() {
        Soldier soldier = new Soldier("name");

        assertThat(soldier.getName()).isEqualTo("name");
    }

    @Test
    void construction_aSoldierMustHaveAName_cannotBeNull() {
        assertThatThrownBy(() -> new Soldier(null))
                .hasMessage("A soldier must have a name")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void construction_aSoldierMustHaveAName_cannotBeEmpty() {
        assertThatThrownBy(() -> new Soldier(""))
                .hasMessage("A soldier must have a name")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void construction_aSoldierMustHaveAName_cannotBeBlank() {
        assertThatThrownBy(() -> new Soldier("     "))
                .hasMessage("A soldier must have a name")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void construction_aSoldierMustHaveADefaultWeapon() {
        Soldier soldier = new Soldier("name");

        assertThat(soldier.getWeapon()).isEqualTo(Weapon.BARE_FIST);
    }

    @Test
    public void equip_shouldChangeWeapon() {
        Soldier soldier = new Soldier("name");

        soldier.equip(Weapon.SWORD);

        assertThat(soldier.getWeapon()).isEqualTo(Weapon.SWORD);
    }

    @Test
    public void assignId_shouldAssignId() {
        Soldier soldier = new Soldier("name");

        soldier.assignId(123);

        assertThat(soldier.getId()).hasValue(123);
    }

    @Test
    public void assignId_givenIdAlreadyAssigned_shouldThrowException() {
        Soldier soldier = new Soldier("name");
        soldier.assignId(123);

        assertThatThrownBy(() -> soldier.assignId(456))
                .hasMessage("Soldier already has an id")
                .isInstanceOf(IllegalStateException.class);
    }

}