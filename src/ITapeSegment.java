/**
 * Interface for the tape segment.
 */
public interface ITapeSegment {

    /**
     * Get the symbol on this segment of tape.
     * @return The symbol on the current segment of tape.
     */
    String getTapeInput();

    /**
     * Write a new symbol to the tape segment.
     * @param symbol The symbol to write to this tape segment.
     */
    void writeSymbolToTape(String symbol);

    /**
     * Get the tape segment to the left of
     * this tape segment.
     * @return The tape segment to the left of this tape segment.
     */
    ITapeSegment moveLeft();

    /**
     * Get the tape segment to the right of
     * this tape segment.
     * @return The tape segment to the right of this tape segment.
     */
    ITapeSegment moveRight();

}
