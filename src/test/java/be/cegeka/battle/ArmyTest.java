package be.cegeka.battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ArmyTest {

    @Test
    public void enroll_shouldAddSoldiersToArmyInOrder() {
        Headquarters headquarters = Mockito.mock(Headquarters.class);
        Army army = new Army(headquarters);
        Soldier soldier1 = new Soldier("name");
        Soldier soldier2 = new Soldier("name");

        army.enroll(soldier1);
        army.enroll(soldier2);

        Assertions.assertThat(army.getSoldiers()).containsExactly(soldier1, soldier2);
        Assertions.assertThat(army.getFrontMan()).hasValue(soldier1);
    }

    @Test
    public void enroll_shouldReportToHeadquartersAndAssignIdToSoldier() {
        Headquarters headquarters = Mockito.mock(Headquarters.class);
        Army army = new Army(headquarters);
        Soldier soldier1 = new Soldier("name");
        Mockito.when(headquarters.reportEnlistment(soldier1.getName())).thenReturn(123);

        army.enroll(soldier1);

        Mockito.verify(headquarters).reportEnlistment(soldier1.getName());
        Assertions.assertThat(soldier1.getId()).hasValue(123);
    }

    @Test
    public void getFrontMan_shouldReturnEmptyOptional_whenArmyIsEmpty() {
        Headquarters headquarters = Mockito.mock(Headquarters.class);
        Army army = new Army(headquarters);
        Assertions.assertThat(army.getFrontMan()).isEmpty();
    }
}