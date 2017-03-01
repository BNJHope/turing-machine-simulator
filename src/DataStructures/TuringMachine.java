package DataStructures;

import Enums.TapeTransitionDirection;
import Enums.TuringMachineReturnCode;
import Parsers.TuringMachineFileParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A model of a Turing machine.
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
     * The label of the start state in the Turing machine.
     */
    private static final String START_LABEL = "q0";

    public TuringMachine() {
     this.states = new HashMap();
    }

    /**
     * Determines if this Turing machine accepts the given string.
     * @param stringToCheck The string to examine.
     * @return The result from running the input through the machine.
     */
    public TuringMachineReturnCode checkIfStringIsAccepted(String stringToCheck) {

        /* Create the tape for the machine from the input string.
        If the method returns false then there was an error in
        reading the tape and we need to exit. */
        if (!this.createTapeFromString(stringToCheck))
            System.exit(1);

        /*
         * Now that the tape has been read, start the machine and return the code
         * from it.
         */
        return this.startMachine();

    }

    /**
     * Determines if this Turing machine accepts the given input file.
     *
     * @param inputFileName The input file to examine.
     * @return The result from running the input through the machine.
     */
    public TuringMachineReturnCode checkIfFileIsAccepted(String inputFileName) {

        /* Create the tape for the machine from the file.
        If the method returns false then there was an error in
        reading the tape and we need to exit. */
        if (!this.createTapeFromFile(inputFileName))
            System.exit(1);

        /*
         * Now that the tape has been read, start the machine and return the code
         * from it.
         */
        return this.startMachine();

    }

    /**
     * Starts the machine after the tape has been formed.
     * @return The Turing machine result code from processing the tape.
     */
    public TuringMachineReturnCode startMachine() {

        /* The previous direction - needed in case we need to make new
        tape segments. */
        TapeTransitionDirection previousDirection = null;

        /* The previous tape segment - needed if we need to make new
        tape segments. */
        TapeSegment previousTapeSegment = null;

        // Get the start state from the collection of states.
        this.currentState = this.states.get(START_LABEL);

        // While we have not reached a terminating state, operate the machine.
        while (!this.currentState.isTerminatingState()) {

            /* If the tape segment is null then it is treated like
            a blank character. If it is not null, then we read the symbol */
            String tapeSymbol = (this.currentTapeSegment == null)
                    ? TuringMachineFileParser.BLANK_CHAR
                    : this.currentTapeSegment.getSymbol();

            // the transition that will be made from the state with the given input symbol.
            Transition transitionToMake = currentState.makeTransition(tapeSymbol);

            // set the current state to the state that we go to in the transition.
            this.currentState = transitionToMake.getNextState();

            /* If this is a new tape segment, then we create a new tape segment object
             to write the output symbol to and stitch it to the previous part. */
            if(currentTapeSegment == null) {

                TapeSegment newTapeSegment = new TapeSegment(transitionToMake.getOutputSymbol());

                switch (previousDirection) {
                    case RIGHT:
                        newTapeSegment.setLeft(previousTapeSegment);
                        previousTapeSegment.setRight(newTapeSegment);
                        break;
                    case LEFT:
                        newTapeSegment.setRight(previousTapeSegment);
                        previousTapeSegment.setLeft(newTapeSegment);
                        break;
                }

                currentTapeSegment = newTapeSegment;

            /* Otherwise, simply write the output symbol
            to current tape segment. */
            } else this.currentTapeSegment.writeSymbolToTape(transitionToMake.getOutputSymbol());

            /* Set the previous tape segment to the current tape segment
            for the next iteration. */
            previousTapeSegment = this.currentTapeSegment;

            /*
            Set the previous direction ready for the next iteration.
             */
            previousDirection = transitionToMake.getNextDirection();

            // move left or right depending on the direction from the transition.
            this.currentTapeSegment = (transitionToMake.getNextDirection().equals(TapeTransitionDirection.RIGHT))
                    ? this.currentTapeSegment.getRightSegment()
                    : this.currentTapeSegment.getLeftSegment();
        }

        // if the machine did end on a terminating state
        if(currentState != null) {
            /* Return the appropriate result code depending
            on the type of the state that the machine terminated in. */
            switch (currentState.getType()) {
                case ACCEPT:
                    return TuringMachineReturnCode.ACCEPTED;
                case REJCECT:
                    return TuringMachineReturnCode.REJECTED;
                default:
                    return TuringMachineReturnCode.UNEXPECTED_TERMINATION;
            }

            // if it did not end on a terminating state
        } else {
            return TuringMachineReturnCode.UNEXPECTED_TERMINATION;
        }

    }

    /**
     * Construct the tape from the string.
     * @param input The string to construct the tape from.
     * @return True if tape was made successfully, false if not.
     */
    public boolean createTapeFromString(String input) {

        // the previous tape segment that was read.
        TapeSegment previousTapeSegment = null, nextTapeSegment;

        /* determines whether the first segment has been read yet
         or not from the file, since the first segment needs to be stored. */
        boolean firstSegmentRead = false;

        /*
         * Create a tape segment for every symbol in the input.
         */
        for(char symbol : input.toCharArray()) {

            if (this.alphabet.contains(Character.toString(symbol))) {

                /* Make a new tape statement, using the input read from the string
                   as the symbol on the tape and the previous tape segment as the tape segment
                   to the left of this tape segment. */
                nextTapeSegment = new TapeSegment(Character.toString(symbol), previousTapeSegment);

                /* If there has been a previous tape segment then
                set the tape segment to the right of the previous tape segment
                as the new tape segment that was created. */
                if(previousTapeSegment != null) previousTapeSegment.setRight(nextTapeSegment);

                /* Update the previous tape segment value, ready for the next tape segment
                   to use as its tape segment piece to the left. */
                previousTapeSegment = nextTapeSegment;

                /* If this is the first segment, then set the Turing machine's
                    next tape segment to read as the first segment. */
                if (!firstSegmentRead) {
                    firstSegmentRead = true;
                    this.currentTapeSegment = nextTapeSegment;
                }

            /* If the character that we read is not in the tape alphabet
               then return an error. */
            } else {
                System.err.println("Character " + input + " not in tape alphabet.");
                return false;
            }
        }

        return true;
    }

    /**
     * Construct the tape from the input file.
     *
     * @param filename The name of the file with the tape.
     * @return True if tape was read successfully, false if not.
     */
    private boolean createTapeFromFile(String filename) {

        /* The constant that means the file reader has reached the
        end of file. */
        final int EOF_CONST = -1;

        // input stream for the file that is being read.
        FileInputStream inputFileStream;

        // the next token in the input file.
        char nextToken;

        // the input from the file interpreted as a string
        String input;

        // the previous tape segment that was read.
        TapeSegment previousTapeSegment = null;

        /* determines whether the first segment has been read yet
         or not from the file, since the first segment needs to be stored. */
        boolean firstSegmentRead = false;

        // try to set up the stream from the input file
        try {
            inputFileStream = new FileInputStream(new File(filename));
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found : " + filename);
            return false;
        }

        /* Try to read the input file to the end and make the tape at
           the same time. */
        try {
            while ((nextToken = (char) inputFileStream.read()) != (char) EOF_CONST) {

                // Convert the character read from the file into a string
                input = Character.toString(nextToken);

                /* If the symbol that has been read is in the input alphabet
                   then add it to the tape. Otherwise, return an error. */
                if (this.alphabet.contains(input)) {

                    /* Make a new tape statement, using the input read from the file
                       as the symbol on the tape and the previous tape segment as the tape segment
                       to the left of this tape segment. */
                    TapeSegment nextTapeSegment = new TapeSegment(input, previousTapeSegment);

                    /* If there has been a previous tape segment then
                    set the tape segment to the right of the previous tape segment
                    as the new tape segment that was created. */
                    if(previousTapeSegment != null) previousTapeSegment.setRight(nextTapeSegment);

                    /* Update the previous tape segment value, ready for the next tape segment
                       to use as its tape segment piece to the left. */
                    previousTapeSegment = nextTapeSegment;

                    /* If this is the first segment, then set the Turing machine's
                        next tape segment to read as the first segment. */
                    if (!firstSegmentRead) {
                        firstSegmentRead = true;
                        this.currentTapeSegment = nextTapeSegment;
                    }

                /* If the character that we read is not in the tape alphabet
                   then return an error. */
                } else {
                    System.err.println("Character " + input + " not in tape alphabet.");
                    return false;
                }

            }

        } catch (IOException e) {
            System.err.println("Error while reading input file");
            return false;
        }

        return true;
    }

    /**
     * Stores the given state in the hash map of states, using the state label as its key.
     *
     * @param state The state to add to the hash map.
     */
    public void addState(State state) {
        this.states.put(state.getLabel(), state);
    }

    /**
     * Set the alphabet as the alphabet given to the function.
     *
     * @param alphabet The alphabet to set as the tape alphabet for this machine.
     */
    public void addAlphabet(ArrayList<String> alphabet) {
        this.alphabet = alphabet;
    }

    /**
     * Add the given transition to the machine.
     *
     * @param tpo The transition data to add to the machine.
     */
    public void addTransition(TransitionParseObject tpo) {
        this.states.get(tpo.startStateLabel).addTransition(tpo.inputSymbol, tpo.transition);
    }

    /**
     * Gets the state with the given label.
     *
     * @param label The label of the state to get.
     * @return The state with this label.
     */
    public State getState(String label) {
        return this.states.get(label);
    }

    /**
     * Gets the string representation of the symbols of the tape of this machine.
     * @return The symbols of the tape in string form.
     */
    public String getTape() {

        /* Get the current tape segment to work from and use it as the segment to work from. */
        TapeSegment currentSeg = this.currentTapeSegment;

        /* Result string i.e all of the symbols on the tape. */
        String result = new String(currentSeg.getSymbol());

        /* while there are still segments to the right of the current segment,
        add the symbol on the tape segment on the right to the tape. */
        while(currentSeg != null) {
            currentSeg = currentSeg.getRightSegment();
            if(currentSeg != null) result += currentSeg.getSymbol();
        }

        /* Reset the reference of the current segment to the start of where we
        worked from. */
        currentSeg = this.currentTapeSegment;

        /* while there are still segments to the left of the current segment,
        add the symbol on the tape segment on the left to the tape. */
        while(currentSeg != null) {
            currentSeg = currentSeg.getLeftSegment();
            if(currentSeg != null) result = currentSeg.getSymbol() + result;
        }

        return result;
    }
}
