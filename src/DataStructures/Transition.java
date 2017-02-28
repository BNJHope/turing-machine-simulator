package DataStructures;

import Enums.TapeTransitionDirection;

/**
 * Output data from a transition function.
 */
public class Transition {

    /**
     * The next state from the transition.
     */
    private State nextState;

    /**
     * The symbol to write to the current tape segment.
     */
    private String outputSymbol;

    /**
     * The next direction for the tape pointer to go in this transition.
     */
    private TapeTransitionDirection nextDirection;

    public Transition(State nextState, String outputSymbol, TapeTransitionDirection nextDirection) {
        this.nextState = nextState;
        this.outputSymbol = outputSymbol;
        this.nextDirection = nextDirection;
    }

    /**
     * Getter for the next state of the transition.
     * @return The next state held by the transition.
     */
    public State getNextState() {
        return nextState;
    }

    /**
     * Getter for the output symbol of the transition.
     * @return The output symbol held by the transition.
     */
    public String getOutputSymbol() {
        return outputSymbol;
    }

    /**
     * Getter for the next tape direction of the transition.
     * @return The tape direction held by the transition.
     */
    public TapeTransitionDirection getNextDirection() {
        return nextDirection;
    }
}
