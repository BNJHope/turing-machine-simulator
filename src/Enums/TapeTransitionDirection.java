package Enums;

/**
 * Enum for the direction to go next in the tape.
 */
public enum TapeTransitionDirection {
    /**
     * Denotes for the tape head to move to the right of the current tape segment.
     */
    RIGHT,

    /**
     * Denotes for the tape head to move to the left of the current tape segment.
     */
    LEFT,

    /**
     * If there is a parse error, then this is returned.
     */
    ERR;

    /**
     * Convert an input string into a tape transition direction.
     * @param input The input to convert into the corresponding direction.
     * @return A member of the enum representing the transition.
     */
    public static TapeTransitionDirection parseTapeTransitionDirection(String input) {
        // the right and left characters in a file
        final String right = "R";
        final String left = "L";

        // convert the input into the transition direction.
        switch (input) {
            case right:
                return RIGHT;
            case left:
                return LEFT;
            default:
                return ERR;
        }

    }
}
