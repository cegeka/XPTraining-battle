package be.cegeka.battle;

import be.cegeka.battle.weapon.Axe;
import be.cegeka.battle.weapon.BareFist;
import be.cegeka.battle.weapon.Spear;
import be.cegeka.battle.weapon.Sword;
import be.cegeka.battle.weapon.Weapon;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TypeMatchupCalculatorTest {

    @ParameterizedTest
    @MethodSource("categoryMatchups")
    public void calculateMatchupBonus_categoryMatchups(Weapon attackerWeapon, Weapon defenderWeapon, int expectedBonus) {
        int actual = TypeMatchupCalculator.calculateBonus(attackerWeapon, defenderWeapon);

        assertThat(actual).isEqualTo(expectedBonus);
    }

    public static Stream<Arguments> categoryMatchups() {
        return Stream.of(
                //Same category, no bonus
                Arguments.of(new Sword(), new Sword(), 0),
                //No category vs no category, no bonus
                Arguments.of(new BareFist(), new BareFist(), 0),
                //No category vs category, no bonus
                Arguments.of(new BareFist(), new Sword(), 0),
                //Category vs no category, no bonus
                Arguments.of(new Sword(), new BareFist(), 0),

                //Axe > Spear, +3 bonus
                Arguments.of(new Axe(), new Spear(), 3),
                //Spear > Sword, +3 bonus
                Arguments.of(new Spear(), new Sword(), 3),
                //Sword > Axe, +3 bonus
                Arguments.of(new Sword(), new Axe(), 3),

                //Spear < Axe, no bonus
                Arguments.of(new Spear(), new Axe(), 0),
                //Sword > Spear, no bonus
                Arguments.of(new Sword(), new Spear(), 0),
                //Axe < Sword, no bonus
                Arguments.of(new Axe(), new Sword(), 0)
        );
    }

}