package DataStructures;

import DataStructures.State;
import DataStructures.TapeSegment;
import DataStructures.Transition;
import DataStructures.TransitionParseObject;
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
     * The label of the start state in the Turing machine.
     */
    private static final String START_LABEL = "q0";

    public TuringMachine() {
     this.states = new HashMap();
    }

    /**
     * Determines if this Turing machine accepts the given input.
     *
     * @param inputFileName The input file to examine.
     * @return The result from running the input through the machine.
     */
    public TuringMachineReturnCode checkIfInputIsAccepted(String inputFileName) {

        /* The previous direction - needed in case we need to make new
        tape segments. */
        TapeTransitionDirection previousDirection = null;

        /* The previous tape segment - needed if we need to make new
        tape segments. */
        TapeSegment previousTapeSegment = null;

        /* Create the tape for the machine from the file.
        If the method returns false then there was an error in
        reading the tape and we need to exit. */
        if (!this.createTapeFromFile(inputFileName))
            System.exit(1);

        // Get the start state from the collection of states.
        this.currentState = this.states.get(START_LABEL);

        // While we have not reached a terminating state, operate the machine.
        while (!currentState.isTerminatingState()) {

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
                    case LEFT:
                        newTapeSegment.setRight(previousTapeSegment);
                        previousTapeSegment.setLeft(newTapeSegment);
                }

            /* Otherwise, simply write the output symbol
            to current tape segment. */
            } else
                // write the new output symbol to the current tape segment.
                this.currentTapeSegment.writeSymbolToTape(transitionToMake.getOutputSymbol());

            previousTapeSegment = this.currentTapeSegment;

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
                    return TuringMachineReturnCode.ACCPETED;
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
     * Construct the tape from the input file.
     *
     * @param filename The name of the file with the tape.
     * @return True if tape was read successfully, false if not.
     */
    private boolean createTapeFromFile(String filename) {

        /* The constant that means the file reader has reached the
        end of file. */
        final int EOF = -1;

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
            System.err.println("Input file not found");
            return false;
        }

        /* Try to read the input file to the end and make the tape at
           the same time. */
        try {
            while ((nextToken = (char) inputFileStream.read()) != EOF) {

                // Convert the character read from the file into a string
                input = Character.toString(nextToken);

                /* If the symbol that has been read is in the input alphabet
                   then add it to the tape. Otherwise, return an error. */
                if (this.alphabet.contains(input)) {

                    /* Make a new tape statement, using the input read from the file
                       as the symbol on the tape and the previous tape segment as the tape segment
                       to the left of this tape segment. */
                    TapeSegment nextTapeSegment = new TapeSegment(input, previousTapeSegment);

                    /* Set the tape segment to the right of the previous tape segment
                       as the new tape segment that was created. */
                    previousTapeSegment.setRight(nextTapeSegment);

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

}
