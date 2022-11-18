package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.IPlan;
import uk.ac.aber.cs21120.wedding.interfaces.IRules;

import java.util.*;

public class Rules implements IRules {

    Set<String> friends = new HashSet<>(); //TODO I think i need to create a new set for each pair of friends and enemies
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
        /*Set<String> friends = new HashSet<>();*/
        friends.add(a);
        friends.add(b);
        //System.out.println(friends);
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
        /*Set<String> enemies = new HashSet<>();*/
        enemies.add(a);
        enemies.add(b);
        //System.out.println(enemies);
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
        while (tableLoop < p.getNumberOfTables()) {
            Set<String> table = p.getGuestsAtTable(tableLoop);
            /*System.out.println(guestTest); //Testing*/
            for (String guest : table) {
                int enemiesAtTable = 0;
                int friendsAtTable = 0;
                Iterator<String> enemiesIt = enemies.iterator();
                Iterator<String> friendsIt = friends.iterator();
                while (enemiesIt.hasNext()) {
                    if (guest.equals(enemies.iterator().next())) {
                        enemiesAtTable++;
                        if (enemiesAtTable > 2) {
                            return false;
                        }
                    }
                    else {
                        break;
                    }
                }
                if (table.size() == p.getSeatsPerTable() && friends.contains(guest)) {
                    while (friendsIt.hasNext()) {
                        if (guest.equals(friendsIt.next())) {
                            friendsAtTable++;
                        }
                    }
                    if (friendsAtTable < friends.size()) {
                        return false;
                    }
                }
                /*else if (guestTest.size() == p.getSeatsPerTable() && !guest.equals(friends.iterator().next()) && !friends.isEmpty()) {
                    return false;
                }*/
            }
                /*if (guestTest.iterator().hasNext() == enemies.iterator().hasNext()) {
                    return false;
                }
                else if (guestTest.size() == p.getSeatsPerTable() && !guestTest.equals(friends) && !friends.isEmpty()) {
                    return false;
                }*/
            tableLoop++;
        }
        return true;
    }
}
