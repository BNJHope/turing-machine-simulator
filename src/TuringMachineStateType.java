/**
 * Determines what type of state a state is, i.e if its an accept, reject or ordinary state.
 */
public enum TuringMachineStateType {

    /**
     * For states that have no special purpose.
     */
    ORDINARY,

    /**
     * For the accept state in the machine.
     */
    ACCEPT,

    /**
     * For the reject state in the machine.
     */
    REJCECT;
}
