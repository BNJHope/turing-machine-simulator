/**
 * Data structure representing a transition.
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
}
