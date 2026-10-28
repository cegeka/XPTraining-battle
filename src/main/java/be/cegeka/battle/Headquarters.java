package be.cegeka.battle;

public interface Headquarters {

    int reportEnlistment(String soldierName);

    void reportCasualty(int soldierId);

    void reportVictory(int remainingNumberOfSoldiers);
}
