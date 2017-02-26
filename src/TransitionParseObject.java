/**
 * Container for data from a transition line in the input file.
 */
public class TransitionParseObject {

    /**
     * The start state of the transition.
     */
    String startStateLabel;

    /**
     * The input symbol of the transition.
     */
    String inputSymbol;

    /**
     * The transition that will be made with the given inputs.
     */
    Transition transition;
}
