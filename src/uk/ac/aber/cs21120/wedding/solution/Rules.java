package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.IPlan;
import uk.ac.aber.cs21120.wedding.interfaces.IRules;

import java.util.*;

public class Rules implements IRules {

    /*Set<String> friends = new HashSet<>(); //TODO I think i need to create a new set for each pair of friends and enemies
    Set<String> enemies = new HashSet<>();
    ArrayList<Set<String>> guestRules = new ArrayList<>();*/

    public Map<String, Set<String>> friendRules = new HashMap<>();
    public Map<String, Set<String>> enemyRules = new HashMap<>();

    /**
     * Add a rule that two guests must be seated at the same table.
     * Adding the same rule twice has no effect.
     *
     * @param a a guest
     * @param b another guest
     */
    @Override
    public void addMustBeTogether(String a, String b) {
        if (friendRules.containsKey(a) && !friendRules.get(a).contains(b)) {
            friendRules.get(a).add(b);
        }
        if (friendRules.containsKey(b) && !friendRules.get(b).contains(a)) {
            friendRules.get(a).add(a);
        }
        /*else if (friendRules.get(a).contains(b) || friendRules.get(b).contains(a)) { //TODO
            System.err.println("They are already friends");
        }*/
        else {
            friendRules.put(a, new HashSet<>());
            friendRules.get(a).add(b);

            friendRules.put(b, new HashSet<>());
            friendRules.get(b).add(a);
            //System.out.println(friendRules);
        }

        /*friends.add(a);
        friends.add(b);
        guestRules.add(friends);
        System.out.println(friends);*/
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
        if (enemyRules.containsKey(a) && !enemyRules.get(a).contains(b)) {
            enemyRules.get(a).add(b);
            //System.out.println(enemyRules); //TESTING
        }
        if (enemyRules.containsKey(b) && !enemyRules.get(b).contains(a)) {
            enemyRules.get(b).add(a);
            //System.out.println(enemyRules); //TESTING
        }
        /*else if (enemyRules.get(a).contains(b) || enemyRules.get(b).contains(a)) { //TODO
            System.err.println("They are already enemies");
        }*/
        else {
            enemyRules.put(a, new HashSet<>());
            enemyRules.get(a).add(b);

            enemyRules.put(b, new HashSet<>());
            enemyRules.get(b).add(a);
            //System.out.println(enemyRules); //TESTING
        }

        /*if (enemies.contains(a + b)) {
            //Do nothing
        }
        else {
            enemies.add(a + b);
        }*/

        /*Set<String> enemies = new HashSet<>();
        enemies.add(a);
        enemies.add(b);
        guestRules.add(enemies);
        System.out.println(enemies);*/
    }

    //IMPORTANT!!! Create a new set for each guest A and A that will add more enimes to it
    //if there is not already a set for it. Then you can just use .contains for your method
    //bellow. Look at Plan.java, all you have to do is iterate.

    /**
     * Return true if the given plan does not break any of the stored rules.
     *
     * @param p a plan
     * @return true if the plan is OK, false if it breaks rules.
     */
    @Override
    public boolean isPlanOK(IPlan p) {
        for (int i = 0; i < p.getNumberOfTables(); i++) {
            Set<String> table = p.getGuestsAtTable(i);
            for (String guest : table) {
                if (enemyRules.containsKey(guest) && p.getGuestsAtTable(i).containsAll(enemyRules.get(guest))) {
                    return false;
                }
                if (friendRules.containsKey(guest) && table.size() == p.getSeatsPerTable() && !p.getGuestsAtTable(i).containsAll(friendRules.get(guest))) {
                    return false;
                }
            }
        }
        return true;

        /*if (friendRules.get(guest).containsAll(p.getGuestsAtTable(i))) {
            return true;
        }*/

        /*int tableLoop = 0;
        while (tableLoop < p.getNumberOfTables()) {
            Set<String> table = p.getGuestsAtTable(tableLoop);
            *//*System.out.println(guestTest); //Testing*//*
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
                *//*else if (guestTest.size() == p.getSeatsPerTable() && !guest.equals(friends.iterator().next()) && !friends.isEmpty()) {
                    return false;
                }*//*
            }
                *//*if (guestTest.iterator().hasNext() == enemies.iterator().hasNext()) {
                    return false;
                }
                else if (guestTest.size() == p.getSeatsPerTable() && !guestTest.equals(friends) && !friends.isEmpty()) {
                    return false;
                }*//*
            tableLoop++;
        }
        return true;*/
    }
}
