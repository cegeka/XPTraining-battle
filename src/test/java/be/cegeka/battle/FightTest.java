package be.cegeka.battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class FightTest {

    public static Stream<Arguments> fightDifferentStrength() {
        Soldier str3 = new Soldier("name");
        str3.equip(Weapon.AXE);
        Soldier str2 = new Soldier("name");
        str2.equip(Weapon.SWORD);
        Soldier str1 = new Soldier("name");
        return Stream.of(
                Arguments.of(str3, str2, str3),
                Arguments.of(str2, str3, str3),
                Arguments.of(str2, str1, str2),
                Arguments.of(str1, str2, str2)
        );
    }

    @ParameterizedTest
    @MethodSource("fightDifferentStrength")
    public void fight_twoSoldiersWithDifferentWeaponStrength_strongestWins(Soldier attacker, Soldier defender, Soldier expectedWinner) {
        Fight fight = new Fight(attacker, defender);
        Soldier actual = fight.getWinner();

        Assertions.assertThat(actual).isEqualTo(expectedWinner);
    }

    @Test
    public void fight_twoSoldiersWithSameWeaponStrength_AttackerWins() {
        Soldier attacker = new Soldier("name");
        attacker.equip(Weapon.AXE);
        Soldier defender = new Soldier("name");
        defender.equip(Weapon.AXE);

        Fight fight = new Fight(attacker, defender);
        Soldier actual = fight.getWinner();

        Assertions.assertThat(actual).isEqualTo(attacker);
    }
}