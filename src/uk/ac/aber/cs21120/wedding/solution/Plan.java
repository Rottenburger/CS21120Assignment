package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.IPlan;

import java.util.*;

public class Plan implements IPlan {

    public ArrayList<Set> tables = new ArrayList<>(); //TODO decide weather to use a LinkedList or ArrayList
    public Set<String> tableSet = new HashSet<>(); //Hash set of strings to represent guests

    /**
     * Constructor initialises table size by adding numbered seats to the table set. Then
     * adds that table set to the list of tables as defined by the number of tables parameter
     * @param numberOfTables defines the number of tableSets in the plan
     * @param seatsPerTable defines the number of seats per tableSet
     */
    public Plan(int numberOfTables, int seatsPerTable) {
        int seatNumber = 0;

        while (seatsPerTable > tableSet.size()) {
            tableSet.add("Seat" + seatNumber);
            seatNumber++;
    }

        while (tables.size() < numberOfTables) {
            tables.add(tableSet);
        }
    }


    /**
     * return the number of seats per table. All tables have the same number of seats.
     * This doesn't change once the Plan has been created.
     * @return the number of seats, a positive non-zero integer
     */
    @Override
    public int getSeatsPerTable() {
        return tableSet.size();
    }

    /**
     * Return the number of tables.
     * This doesn't change once the Plan has been created.
     * @return the number of tables, a positive non-zero integer
     */
    @Override
    public int getNumberOfTables() {
        return tables.size();
    }

    /**
     * Add a guest to a table. If the guest is already seated at any table it will
     * do nothing. If the table is already full (i.e. the number of guests at that table is
     * equal to getSeatsPerTable() it will do nothing.) If the table number is less than zero,
     * or greater than getSeatsPerTable(), it will raise IndexOutOfBoundsException.
     * @param table the table number
     * @param guest the name of the guest
     */
    @Override
    public void addGuestToTable(int table, String guest) throws IndexOutOfBoundsException {

        /*check if table is valid (too high or too low)
        * check if already seated (use guestplaced method)
        * check seats per table & if there is a spare seat (getSeats per table method)
        * */

        if (table <= -1) {
            throw new IndexOutOfBoundsException("that table number is invalid");
        }
        else {
            tables.get(table).remove(tableSet.iterator().next()); //TODO this removes every guest added & does this for every set in the list
            tables.get(table).add(guest);
            System.out.println(tables.get(table)); //Print tableSet to check if working
        }
    }

    /**
     * Remove a guest from any table they are sitting at. If the guest is not at any
     * table, it will do nothing.
     * @param guest the name of the guest
     */
    @Override
    public void removeGuestFromTable(String guest) {

        /*tables.get().remove(tableSet.iterator().next());*/

        for (Set listOfTables: tables) {
            if (listOfTables.contains(guest)) {
                listOfTables.remove(guest);
                listOfTables.add("Seat"); //TODO check number of seats avalible to create correctly named space
                break;
            }
            else {
                //Do nothing
            }
        }
        System.out.println(tables); //Check tableSet
    }

    /**
     * Return whether a guest is sitting at any table.
     * @param guest the name of the guest
     * @return true if the guest is at a table, false otherwise
     */
    @Override
    public boolean isGuestPlaced(String guest) {

        boolean guestFound = false; // .next().contains(guest)
        int tableCheckingNum = 0;

        while (tableCheckingNum < tables.size() && guestFound == false) {
            if (tables.get(tableCheckingNum).contains(guest)) {
                guestFound = true;
                break;
            }
            System.out.println(tables.get(tableCheckingNum)); //Check what is found
            System.out.println(guest); //Check what is found
            tableCheckingNum++;
        }

        /*while (tables.listIterator().hasNext() && !tableSet.contains(guest)) {

        }
        tables.listIterator().next().contains(guest);
        for (Set listOfTables: tables) {
            if (listOfTables.contains(guest)) {
                guestFound = true;
                System.out.println(listOfTables);
                break;
            }
        }*/

        return guestFound;
    }

    /**
     * Return a set of the guests seated at a particular table. If the
     * table number of out of range it will raise IndexOutOfBoundsException.
     * @param t the table number
     * @return a set of strings - the guests at that table
     */
    @Override
    public Set<String> getGuestsAtTable(int t) {

        if (t > tables.size()) {
            throw new IndexOutOfBoundsException("There is no table with that number!");
        }

        /*tables.get(t).contains("Seat");
        tables.get(t);*/

        return tables.get(t);
    }
}
