package be.cegeka.battle;

public class Sword extends Weapon {
    public static final int DAMAGE = 2;

    @Override
    public int getDamage() {
        return DAMAGE;
    }
}