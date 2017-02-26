import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Turing Machine simulation.
 */
public class TuringMachine {

    /**
     * Current state where the Turing machine is.
     */
    private State currentState;

    /**
     * Current segment of tape that the Turing machine is reading.
     */
    private TapeSegment currentTapeSegment;

    /**
     * Maps a state label to the state it represents.
     */
    private HashMap<String, State> states;

    /**
     * The tape alphabet for this Turing Machine.
     */
    private ArrayList<String> alphabet;

    /**
     * Stores the given state in the hashmap of states, using the state label as its key.
     * @param state The state to add to the hashmap.
     */
    public void addState(State state) {
        this.states.put(state.getLabel(), state);
    }

    /**
     * Set the alphabet as the alphabet given to the function.
     * @param alphabet The alphabet to set as the tape alphabet for this machine.
     */
    public void addAlphabet(ArrayList<String> alphabet) {
        this.alphabet = alphabet;
    }

    /**
     * Add the given transition to the machine.
     * @param tpo The transition data to add to the machine.
     */
    public void addTransition(TransitionParseObject tpo) {
        this.states.get(tpo.startStateLabel).addTransition(tpo.inputSymbol, tpo.transition);
    }


    /**
     * Gets the state with the given label.
     * @param label The label of the state to get.
     * @return The state with this label.
     */
    public State getState(String label) {
        return this.states.get(label);
    }
}
