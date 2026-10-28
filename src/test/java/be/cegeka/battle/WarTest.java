package be.cegeka.battle;

import be.cegeka.battle.weapon.Axe;
import be.cegeka.battle.weapon.BareFist;
import be.cegeka.battle.weapon.Sword;
import be.cegeka.battle.weapon.Trident;
import be.cegeka.battle.weapon.Weapon;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class WarTest {

    public static Stream<Arguments> wageWars() {
        return Stream.of(
                attackerWins(),
                defenderWins()
        );
    }

    private static Arguments attackerWins() {
        Headquarters attackerHeadquarters = createHeadquartersEnlisting(1, 2, 3);
        Headquarters defenderHeadquarters = createHeadquartersEnlisting(5, 6, 7, 8);

        return Arguments.of(
                createArmyWithSoldiers(attackerHeadquarters, createSoldierWithWeapon(new BareFist()), createSoldierWithWeapon(new Sword()), createSoldierWithWeapon(new Axe())),
                createArmyWithSoldiers(defenderHeadquarters, createSoldierWithWeapon(new BareFist()), createSoldierWithWeapon(new BareFist()), createSoldierWithWeapon(new BareFist()), createSoldierWithWeapon(new Sword())),
                createExpectedArmyWithSoldiers(createSoldierWithWeapon(new Sword()), createSoldierWithWeapon(new Axe())),
                attackerHeadquarters,
                defenderHeadquarters,
                List.of(1),
                List.of(5, 6, 7, 8)
        );
    }

    private static Arguments defenderWins() {
        Headquarters attackerHeadquarters = createHeadquartersEnlisting(1, 2, 3, 4);
        Headquarters defenderHeadquarters = createHeadquartersEnlisting(5, 6, 7);

        return Arguments.of(
                createArmyWithSoldiers(attackerHeadquarters, createSoldierWithWeapon(new BareFist()), createSoldierWithWeapon(new BareFist()), createSoldierWithWeapon(new BareFist()), createSoldierWithWeapon(new Sword())),
                createArmyWithSoldiers(defenderHeadquarters, createSoldierWithWeapon(new BareFist()), createSoldierWithWeapon(new Sword()), createSoldierWithWeapon(new Trident())),
                createExpectedArmyWithSoldiers(createSoldierWithWeapon(new Trident())),
                defenderHeadquarters,
                attackerHeadquarters,
                List.of(5, 6),
                List.of(1, 2, 3, 4)
        );
    }

    private static Headquarters createHeadquartersEnlisting(Integer firstId, Integer... nextIds) {
        Headquarters headquarters = Mockito.mock(Headquarters.class);
        Mockito.when(headquarters.reportEnlistment("name")).thenReturn(firstId, nextIds);
        return headquarters;
    }

    // Army equality only compares soldiers (not ids), so the expected army doesn't need a stubbed headquarters
    private static Army createExpectedArmyWithSoldiers(Soldier... soldiers) {
        return createArmyWithSoldiers(Mockito.mock(Headquarters.class), soldiers);
    }

    private static Army createArmyWithSoldiers(Headquarters headquarters, Soldier... soldiers) {
        Army army = new Army(headquarters);
        Arrays.stream(soldiers).forEach(army::enroll);
        return army;
    }

    private static Soldier createSoldierWithWeapon(Weapon weapon) {
        Soldier soldier = new Soldier("name");
        soldier.equip(weapon);
        return soldier;
    }

    @ParameterizedTest
    @MethodSource("wageWars")
    public void wageWar_shouldReturnWinnerWithSurvivingSoldiersOnlyAndReportToHQ(Army attacker,
                                                                                 Army defender,
                                                                                 Army expectedWinnerWithSurvivingSoldiers,
                                                                                 Headquarters winnerHeadquarters,
                                                                                 Headquarters loserHeadquarters,
                                                                                 List<Integer> winnerReportedCasualties,
                                                                                 List<Integer> loserReportedCasualties) {
        War war = new War(attacker, defender);

        Army actual = war.getVictor();

        Assertions.assertThat(actual).isEqualTo(expectedWinnerWithSurvivingSoldiers);
        assertVictoryReported(expectedWinnerWithSurvivingSoldiers, winnerHeadquarters, loserHeadquarters);
        assertCasualtiesReported(winnerHeadquarters, loserHeadquarters, winnerReportedCasualties, loserReportedCasualties);
    }

    private static void assertVictoryReported(Army expectedWinnerWithSurvivingSoldiers, Headquarters winnerHeadquarters, Headquarters loserHeadquarters) {
        Mockito.verify(winnerHeadquarters).reportVictory(expectedWinnerWithSurvivingSoldiers.getSoldiers().size());
        Mockito.verify(loserHeadquarters, Mockito.never()).reportVictory(Mockito.anyInt());
    }

    private static void assertCasualtiesReported(Headquarters winnerHeadquarters, Headquarters loserHeadquarters, List<Integer> winnerReportedCasualties, List<Integer> loserReportedCasualties) {
        winnerReportedCasualties.forEach(id -> Mockito.verify(winnerHeadquarters).reportCasualty(id));
        loserReportedCasualties.forEach(id -> Mockito.verify(loserHeadquarters).reportCasualty(id));
    }
}