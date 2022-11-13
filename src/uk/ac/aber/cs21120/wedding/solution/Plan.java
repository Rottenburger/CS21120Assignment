package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.IPlan;

import java.util.*;

public class Plan implements IPlan {

    public List<Set> tables = new ArrayList<>(); //TODO decide weather to use a LinkedList or ArrayList
    public int tableSeats;
    /*public Set<String> tableSet = new HashSet<>(); //Hash set of strings to represent guests*/

    /**
     * Constructor initialises table size by adding numbered seats to the table set. Then
     * adds that table set to the list of tables as defined by the number of tables parameter
     * @param numberOfTables defines the number of tableSets in the plan
     * @param seatsPerTable defines the number of seats per tableSet
     */
    public Plan(int numberOfTables, int seatsPerTable) {
        int tableNumber = 0;
        int tableSeat = 0;
        tableSeats = seatsPerTable; //Assigning the seats per table to a global variable because I don't know any other way

        while (tables.size() < numberOfTables) {
            tables.add(new HashSet<String>()); //THIS IS THE PROBLEM!!
        }

        while (numberOfTables > tableNumber) {
            if (tableSeat != seatsPerTable) {
                tables.get(tableNumber).add("Seat " + tableSeat);
                tableSeat++;
            }
            else {
                tableNumber++;
                tableSeat = 0;
            }
        }
    }


    /**
     * return the number of seats per table. All tables have the same number of seats.
     * This doesn't change once the Plan has been created.
     * @return the number of seats, a positive non-zero integer
     */
    @Override
    public int getSeatsPerTable() {
        return tableSeats;
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
        try {
            if (table <= -1 || tables.size() <= table) {
                throw new IndexOutOfBoundsException("That table does not exist");
            }
            else if (isGuestPlaced(guest)) {
                System.err.println("That guest has already been placed"); //Used for testing
            }
            else if (getGuestsAtTable(table).size() == getSeatsPerTable()) {
                System.err.println("This table is full"); //Used for testing
            }
            else {
                Iterator<String> iterator = tables.get(table).iterator();
                while(iterator.hasNext())
                {
                    String value = iterator.next();
                    if (value.contains("Seat"))
                    {
                        iterator.remove();
                        break;
                    }
                }
                System.out.println(tables.get(3)); //Print tableSet to check if working
                tables.get(table).add(guest);

            /*tables.set(table, tableSet).remove("Seat 0");
            tables.get(table).remove("Seat 0");

            tables.get(table);
            tables.get(table).add(guest);*/
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("That table does not exist");
        }

        /*else {
            tables.get(table).remove(tableSet.iterator().next()); //TODO this removes every guest added & does this for every set in the list
            tables.get(table).add(guest);
            System.out.println(tables.get(table)); //Print tableSet to check if working
        }*/
    }

    /**
     * Remove a guest from any table they are sitting at. If the guest is not at any
     * table, it will do nothing.
     * @param guest the name of the guest
     */
    @Override
    public void removeGuestFromTable(String guest) {
        int tableCheckingNum = 0;
        int guestTableLocation;

        while (tableCheckingNum < tables.size()) {
            if (tables.get(tableCheckingNum).contains(guest)) {
                guestTableLocation = tableCheckingNum;
                tables.get(guestTableLocation).remove(guest);
                break;
            }
            tableCheckingNum++;
        }
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

        while (tableCheckingNum < tables.size() && !guestFound) {
            if (tables.get(tableCheckingNum).contains(guest)) {
                guestFound = true;
                break;
            }
            /*System.out.println(tables.get(tableCheckingNum)); //Check what is found
            System.out.println(guest); //Check what is found*/
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
        if (t <= -1 || t > tables.size()) {
            throw new IndexOutOfBoundsException("There is no table with that number!");
        }
        Set table = tables.get(t);

        Iterator<String> iterator = table.iterator();
        while(iterator.hasNext())
        {
            String value = iterator.next();
            if (value.contains("Seat"))
            {
                iterator.remove();
            }
            else {
                break;
            }
        }
        return table;
    }
}
