package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.IPlan;

import java.util.*;

public class Plan implements IPlan {

    public List tables = new ArrayList<>(); //TODO decide weather to use a LinkedList or ArrayList
    public Set<Integer> table = new HashSet<Integer>(); //Hash set of strings to represent guests

    /**
     * Empty constructor that takes in number of tables and number of seats as parameters.
     * @param numberOfTables
     * @param seatsPerTable
     */
    public Plan(int numberOfTables, int seatsPerTable) {
        tables.add(numberOfTables);
        table.add(seatsPerTable);
    }


    /**
     * return the number of seats per table. All tables have the same number of seats.
     * This doesn't change once the Plan has been created.
     * @return the number of seats, a positive non-zero integer
     */
    @Override
    public int getSeatsPerTable() {
        return 0;
    }

    /**
     * Return the number of tables.
     * This doesn't change once the Plan has been created.
     * @return the number of tables, a positive non-zero integer
     */
    @Override
    public int getNumberOfTables() {
        return 0;
    }

    /**
     * Add a guest to a table. If the guest is already seated at any table it will
     * do nothing. If the table is already full (i.e. the number of guests at that table is
     * equal to getSeatsPerTable() it will do nothing. If the table number is less than zero,
     * or greater than getSeatsPerTable(), it will raise IndexOutOfBoundsException.
     * @param table the table number
     * @param guest the name of the guest
     */
    @Override
    public void addGuestToTable(int table, String guest) {

    }

    /**
     * Remove a guest from any table they are sitting at. If the guest is not at any
     * table, it will do nothing.
     * @param guest the name of the guest
     */
    @Override
    public void removeGuestFromTable(String guest) {

    }

    /**
     * Return whether a guest is sitting at any table.
     * @param guest the name of the guest
     * @return true if the guest is at a table, false otherwise
     */
    @Override
    public boolean isGuestPlaced(String guest) {
        return false;
    }

    /**
     * Return a set of the guests seated at a particular table. If the
     * table number of out of range it will raise IndexOutOfBoundsException.
     * @param t the table number
     * @return a set of strings - the guests at that table
     */
    @Override
    public Set<String> getGuestsAtTable(int t) {
        return null;
    }
}
