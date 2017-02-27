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

    public Transition(State nextState, String outputSymbol, TapeTransitionDirection nextDirection) {
        this.nextState = nextState;
        this.outputSymbol = outputSymbol;
        this.nextDirection = nextDirection;
    }

    public State getNextState() {
        return nextState;
    }

    public String getOutputSymbol() {
        return outputSymbol;
    }

    public TapeTransitionDirection getNextDirection() {
        return nextDirection;
    }
}
