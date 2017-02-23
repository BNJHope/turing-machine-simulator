/**
 * A segment in the Turing Machine tape.
 */
public class TapeSegment implements ITapeSegment{

    /**
     * The tape segment to the left of this tape segment.
     */
    private TapeSegment left;

    /**
     * The tape segment to the right of this tape segment.
     */
    private TapeSegment right;

    /**
     * The symbol on this tape segment.
     */
    private String symbol;

    /**
     * Constructor for tape segment, given symbol and surrounding states.
     * @param symbol The symbol on the tape.
     * @param left The segment to the left of this tape segment.
     * @param right The segment to the right of this tape segment.
     */
    public TapeSegment(String symbol, TapeSegment left, TapeSegment right) {

        this.symbol = symbol;
        this.left = left;
        this.right = right;
    }

    /**
     * Constructs the tape segment with just the symbol.
     * @param symbol
     */
    public TapeSegment(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Set the tape segment to the left of this tape segment.
     * @param left
     */
    public void setLeft(TapeSegment left) {
        this.left = left;
    }

    /**
     * Set the tape segment to the right of this tape segment.
     * @param right
     */
    public void setRight(TapeSegment right) {
        this.right = right;
    }

    /**
     * Get the symbol on this tape segment.
     * @return Symbol on this tape segment.
     */
    @Override
    public String getTapeInput() {
        return this.symbol;
    }

    /**
     * Write the given symbol onto the tape segment.
     * @param symbol The symbol to write to this tape segment.
     */
    @Override
    public void writeSymbolToTape(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Get the tape segment to the left of this tape segment.
     * @return
     */
    @Override
    public ITapeSegment moveLeft() {
        return this.left;
    }

    /**
     * Get the tape segment to the right of this tape segment.
     * @return
     */
    @Override
    public ITapeSegment moveRight() {
        return this.right;
    }
}
