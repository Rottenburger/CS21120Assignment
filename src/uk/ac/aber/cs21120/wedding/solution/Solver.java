package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.IPlan;
import uk.ac.aber.cs21120.wedding.interfaces.IRules;
import uk.ac.aber.cs21120.wedding.interfaces.ISolver;

public class Solver implements ISolver {

    public String[] solverGuests;
    public IPlan solverPlan;
    public IRules solveRules;

    /**
     * Constructor for Solver class takes a String guest input that will use
     * an IPlan class and a IRules class to build a seating plan that will be tested
     * @param guests String list of guests for the wedding
     * @param plan Plan containing tables with seats
     * @param rules Rules containing rules for guest seating
     */
    public Solver(String[]guests, IPlan plan, IRules rules) {
        solverGuests = guests;
        solverPlan = plan;
        solveRules = rules;
    }

    /**
     * Attempt to solve the plan with the rules and guests provided in
     * the constructor. Will recurse.
     *
     * @return true if a solution was found, false if no solution was found.
     */
    @Override
    public boolean solve() {
        boolean solveResult;
        for (int i = 0; i < solverPlan.getNumberOfTables(); i++) {
            while (solverPlan.getGuestsAtTable(i).size() < solverPlan.getSeatsPerTable()) {
                for (String guest : solverGuests) {
                    if (!solverPlan.isGuestPlaced(guest)) {
                        solverPlan.addGuestToTable(i, guest);
                        if (solveRules.isPlanOK(solverPlan)) {
                            solveResult = solve();
                            if (solveResult) {
                                return true;
                            }
                            solverPlan.removeGuestFromTable(guest);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
