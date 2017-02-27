package Enums;

/**
 * Set of return codes from the
 */
public enum TuringMachineReturnCode {

    /**
     * Return code for when an input
     * is accepted by the Turing Machine.
     */
    ACCEPTED,

    /**
     * Return code for when an input
     * is rejected by the Turing Machine.
     */
    REJECTED,

    /**
     * Return code for when the input causes
     * an unexpected termination of the Turing machine.
     */
    UNEXPECTED_TERMINATION
}
