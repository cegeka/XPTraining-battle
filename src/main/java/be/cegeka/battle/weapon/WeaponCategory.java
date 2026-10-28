package be.cegeka.battle.weapon;

import java.util.HashMap;
import java.util.Map;

public enum WeaponCategory {
    NONE,
    AXE,
    SPEAR,
    SWORD,
    MAGIC;

    private static final Map<WeaponCategory, WeaponCategory> ADVANTAGE_MAP = new HashMap<>();

    static {
        ADVANTAGE_MAP.put(AXE, SPEAR);
        ADVANTAGE_MAP.put(SPEAR, SWORD);
        ADVANTAGE_MAP.put(SWORD, AXE);
    }

    public boolean hasAdvantageAgainst(WeaponCategory opponent) {
        return canHaveAdvantage() && hasAdvantageAgainstCategory(opponent);
    }

    private boolean canHaveAdvantage() {
        return ADVANTAGE_MAP.containsKey(this);
    }

    private boolean hasAdvantageAgainstCategory(WeaponCategory opponent) {
        return ADVANTAGE_MAP.get(this) == opponent;
    }
}
