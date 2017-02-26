import java.util.HashMap;

/**
 * State in a Turing Machine
 */
public class State {

    /**
     * Set of transitions mapping input to next state.
     */
    private HashMap<String, Transition> transitions = new HashMap();

    /**
     * The label of this state.
     */
    private String label;

    /**
     * Boolean determining if this is the accept state in the machine.
     */
    private boolean isAcceptState;

    /**
     * Boolean determining if this is the reject state in the machine.
     */
    private boolean isRejectState;

    public State(String label) {
        this.label = label;
    }

    public State(String label, boolean isAcceptState, boolean isRejectState) {
        this.transitions = transitions;
        this.label = label;
        this.isAcceptState = isAcceptState;
        this.isRejectState = isRejectState;
    }

    /**
     * Get the label of this state.
     * @return The label of this state.
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Make a transition from this
     * state to the next state depending on
     * the input.
     * @param input The input to make the transition with.
     * @return The next state from this transition.
     */
    public Transition makeTransition(String input) {

        // if the given input is valid for this state, then return the next state,
        // otherwise, return null
        return this.transitions.containsKey(input) ? this.transitions.get(input) : null;

    }

    /**
     * Add the given transition for the given input symbol.
     * @param input The input letter that will cause this transition.
     * @param transition The transition that will occur with the given input symbol.
     */
    public void addTransition(String input, Transition transition) {

        this.transitions.put(input, transition);
    }

}
