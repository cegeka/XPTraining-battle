package be.cegeka.battle;


import com.google.common.base.Strings;

public class Soldier {

    private final String name;

    public Soldier(String name) {
        if (Strings.isNullOrEmpty(name)) {
            throw new IllegalArgumentException();
        }

        this.name = name;
    }

    String getName() {
        return this.name;
    }
}
