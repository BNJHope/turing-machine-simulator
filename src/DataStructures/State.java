package DataStructures;

import java.util.HashMap;
import Enums.TuringMachineStateType;

/**
 * DataStructures.State in a Turing Machine
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
     * Determines if this is a normal state or a terminating state.
     */
    private TuringMachineStateType type;

    public State(String label, TuringMachineStateType type) {
        this.label = label;
        this.type = type;
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

    /**
     * Get the type of this state.
     * @return The type of this state.
     */
    public TuringMachineStateType getType() {
        return this.type;
    }

    /**
     * Returns whether or not this state is an accept or reject state.
     * @return true if it is a terminating state, false if not.
     */
    public boolean isTerminatingState() {
        return this.type.equals(TuringMachineStateType.ACCEPT)
                || this.type.equals(TuringMachineStateType.REJCECT);
    }
}
