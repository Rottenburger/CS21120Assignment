package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.IPlan;
import uk.ac.aber.cs21120.wedding.interfaces.IRules;
import uk.ac.aber.cs21120.wedding.interfaces.ISolver;

public class Solver implements ISolver {

    public Solver(String[]guests, IPlan plan, IRules rules) {
    }

    /**
     * Attempt to solve the plan with the rules and guests provided in
     * the constructor. Will recurse.
     *
     * @return true if a solution was found, false if no solution was found.
     */
    @Override
    public boolean solve() {
        return false;
    }
}
