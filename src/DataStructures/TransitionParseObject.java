package DataStructures;

/**
 * Container for data from a transition line in the input file.
 */
public class TransitionParseObject {

    /**
     * The start state of the transition.
     */
    public String startStateLabel;

    /**
     * The input symbol of the transition.
     */
    public String inputSymbol;

    /**
     * The transition that will be made with the given inputs.
     */
    public Transition transition;
}
