package be.cegeka.battle;

public enum Weapon {
    AXE(3),
    SWORD(2),
    SPEAR(2),
    BARE_FIST(1);

    private final int power;

    Weapon(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}
