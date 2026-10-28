package be.cegeka.battle;

import be.cegeka.battle.weapon.Axe;
import be.cegeka.battle.weapon.Sword;
import be.cegeka.battle.weapon.Trident;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class FightTest {

    public static Stream<Arguments> fightDifferentStrength() {
        Soldier str9 = new Soldier("name");
        str9.equip(new Trident());
        Soldier str2 = new Soldier("name");
        str2.equip(new Sword());
        Soldier str1 = new Soldier("name");
        return Stream.of(
                Arguments.of(str9, str2, str9),
                Arguments.of(str2, str9, str9),
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
        attacker.equip(new Axe());
        Soldier defender = new Soldier("name");
        defender.equip(new Axe());

        Fight fight = new Fight(attacker, defender);
        Soldier actual = fight.getWinner();

        Assertions.assertThat(actual).isEqualTo(attacker);
    }

    @Test
    public void fight_attackerCanReceiveBonusPowerIfWeaponTypeMatchupBeneficial() {
        //Swords receive +3 bonus vs axes, making sword power 5 vs the axe power of 3
        Soldier attacker = new Soldier("name");
        attacker.equip(new Sword());
        Soldier defender = new Soldier("name");
        defender.equip(new Axe());

        Fight fight = new Fight(attacker, defender);
        Soldier actual = fight.getWinner();

        Assertions.assertThat(actual).isEqualTo(attacker);
    }

    @Test
    public void fight_defenderCanNOTReceiveBonusPowerIfWeaponTypeMatchupBeneficial() {
        Soldier attacker = new Soldier("name");
        attacker.equip(new Axe());
        Soldier defender = new Soldier("name");
        defender.equip(new Sword());

        Fight fight = new Fight(attacker, defender);
        Soldier actual = fight.getWinner();

        Assertions.assertThat(actual).isEqualTo(attacker);
    }
}