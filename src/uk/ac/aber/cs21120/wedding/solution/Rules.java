package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.IPlan;
import uk.ac.aber.cs21120.wedding.interfaces.IRules;

import java.util.*;

public class Rules implements IRules {

    Set<String> friends = new HashSet<>();
    Set<String> enemies = new HashSet<>();

    /**
     * Add a rule that two guests must be seated at the same table.
     * Adding the same rule twice has no effect.
     *
     * @param a a guest
     * @param b another guest
     */
    @Override
    public void addMustBeTogether(String a, String b) {
        friends.add(a + b);
    }

    /**
     * Add a rule that two guests must never be seated at the same table.
     * Adding the same rule twice has no effect.
     *
     * @param a a guest
     * @param b another guest
     */
    @Override
    public void addMustBeApart(String a, String b) {
        enemies.add(a + b);
    }

    /**
     * Return true if the given plan does not break any of the stored rules.
     *
     * @param p a plan
     * @return true if the plan is OK, false if it breaks rules.
     */
    @Override
    public boolean isPlanOK(IPlan p) {
        int tableLoop = 0;
        while (p.getNumberOfTables() < tableLoop) {
            int guestLoop = 0;
            Set guestTest = p.getGuestsAtTable(tableLoop);
            while (guestTest.size() < guestLoop) {
                if (guestTest == enemies) {
                    return false;
                }
                else if (guestTest == friends) {
                    return false;
                }
            }
        }
        return true;
    }
}
