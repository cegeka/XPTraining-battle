package be.cegeka.battle;

import be.cegeka.battle.weapon.BareFist;
import be.cegeka.battle.weapon.Spear;
import be.cegeka.battle.weapon.Sword;
import be.cegeka.battle.weapon.Weapon;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class WeaponTest {

    public static Stream<Arguments> weapons() {
        return Stream.of(
                //Same class
                Arguments.of(new Sword(), new Sword(), true),
                //Different class, same strength
                Arguments.of(new Sword(), new Spear(), false),
                //Different class, different strength
                Arguments.of(new BareFist(), new Spear(), false)
        );
    }

    @ParameterizedTest
    @MethodSource("weapons")
    public void equals(Weapon weapon1, Weapon weapon2, boolean expected) {
        boolean actual = weapon1.equals(weapon2);

        assertThat(actual).isEqualTo(expected);
    }
}
