package be.cegeka.battle;

import be.cegeka.battle.weapon.Axe;
import be.cegeka.battle.weapon.MagicPotion;
import be.cegeka.battle.weapon.Sword;
import be.cegeka.battle.weapon.Weapon;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MagicPotionTest {

    public static Stream<Arguments> magicPotionPower() {
        return Stream.of(
                //Odd -> 0
                Arguments.of(new Axe(), 0),
                //Even -> 10
                Arguments.of(new Sword(), 10),
                //Potion -> 10
                Arguments.of(new MagicPotion(), 10)
        );
    }

    @ParameterizedTest
    @MethodSource("magicPotionPower")
    public void getPower(Weapon otherWeapon, int expectedPower) {
        int actual = new MagicPotion().getPower(otherWeapon);

        Assertions.assertThat(actual).isEqualTo(expectedPower);
    }
}
