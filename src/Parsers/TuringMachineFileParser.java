package Parsers;

import DataStructures.State;
import DataStructures.Transition;
import DataStructures.TransitionParseObject;
import DataStructures.TuringMachine;
import Enums.TapeTransitionDirection;
import Enums.TuringMachineStateType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Converts an input file into its corresponding Turing Machine representation.
 */
public class TuringMachineFileParser {

    /**
     * The character that represents blank in the file.
     */
    public static final String BLANK_CHAR = "_";

    /**
     * The scanner used to read in the Turing Machine input file.
     */
    private Scanner fileScanner;

    /**
     * Denotes the string used in a line that holds a comment.
     */
    private static final String COMMENT_REPRESENTATION = "#";

    /**
     * Converts a Turing Machine input file
     * into an instance of a Turing Machine.
     * @param filename The input file for the Turing Machine
     * @return An instance of a Turing machine represented by the file.
     */
    public TuringMachine parseFile(String filename) throws FileNotFoundException {

        // the Turing Machine that will be created
        TuringMachine tm = new TuringMachine();

        // set up the input file
        this.setUpFileInput(filename);

        // gets the number of states from the next line
        int numOfStates = this.parseNumberOfStates();

        // add all the states in the file to the Turing Machine
        for(int stateStepper = 0; stateStepper < numOfStates; stateStepper++) {
            tm.addState(this.parseState());
        }

        // the tape alphabet of this Turing machine
        ArrayList<String> tapeAlphabet = this.parseAlphabetLine();

        /* the number of transitions in this Turing machine - equal to
        the (number of states in the machine without terminating states) * (the size of the tape alphabet) */
        int numberOfTransitions = (numOfStates - 2) * (tapeAlphabet.size());

        // get the alphabet line from the file
        tm.addAlphabet(tapeAlphabet);

        // read all of the transitions from the file and add them to the machine
        for(int transitionStepper = 0; transitionStepper < numberOfTransitions; transitionStepper++) {
            tm.addTransition(this.parseTransition(tm));
        }

        // close the file scanner since we have finished parsing the file.
        fileScanner.close();

        return tm;
    }

    /**
     * Read the file to get the number of states in the Turing Machine
     * @return The integer of how many states there are in the machine.
     */
    private int parseNumberOfStates() {
        String elements[] = this.getElementsOfNextLine();
        return Integer.parseInt(elements[1]);
    }

    /**
     * Parse a state from the input file.
     * @return The instance of the state represented by the line of the file
     */
    private State parseState() {

        // get the elements of the line
        String elements[] = this.getElementsOfNextLine();

        // new state object to return
        State newstate;

        /* The index of the element which contains
        the label of the state in the collection of elements */
        final int STATE_LABEL_INDEX = 0;

        /* The index of the element which contains the symbol determining
        if the state parsed is the accept or reject state. */
        final int ACCEPT_OR_REJECT_STATE_INDEX = 1;

        /* The symbol that determines that a
        state is then accepting state. */
        final String ACCEPT_SYMBOL = "+";

        /* If the input line only has one elements in it then it means
        that the state we are parsing is neither an accept or reject state
        so create a new ordinary state. */
        if(elements.length == 1) newstate = new State(elements[STATE_LABEL_INDEX], TuringMachineStateType.ORDINARY);

        /* Otherwise, give the state the type of either accept or reject accordingly.*/
        else newstate = (elements[ACCEPT_OR_REJECT_STATE_INDEX].equals(ACCEPT_SYMBOL))
                ? new State(elements[STATE_LABEL_INDEX], TuringMachineStateType.ACCEPT)
                : new State(elements[STATE_LABEL_INDEX], TuringMachineStateType.REJCECT);

        return newstate;
    }

    /**
     * Parse the alphabet line in the input file.
     * @return The arraylist representing the tape alphabet of the Turing Machine.
     */
    private ArrayList<String> parseAlphabetLine() {

        /* In the list of elemnts from the line, this is the index of
        the first member of the tape alphabet in the line */
        final int START_OF_SYMBOLS_INDEX = 2;

        // get elements of the next line
        String elements[] = this.getElementsOfNextLine();

        // list of elements in the alphabet
        ArrayList<String> alphabetList = new ArrayList();

        /* Add all of the elements from the line after the elements
        that shows the number of symbols in the tape alphabet. */
        alphabetList.addAll(Arrays.asList(elements).subList(START_OF_SYMBOLS_INDEX, elements.length));

        // add the blank character to the tape alphabet
        alphabetList.add(this.BLANK_CHAR);

        return alphabetList;
    }

    /**
     * Parse a transition line from the input file.
     * @return An object containing the data contained in the transition line.
     */
    private TransitionParseObject parseTransition(TuringMachine tm) {

        /* Indices of where various elements of a transition are
        on the line read in from the file */
        final int START_STATE_SYMBOL_INDEX = 0,
                INPUT_SYMBOL_INDEX = 1,
                NEXT_STATE_LABEL_INDEX = 2,
                OUTPUT_SYMBOL_INDEX = 3,
                TRANSITION_DIRECTION_INDEX = 4;

        // the parts of the line in the file
        String lineElements[] = this.getElementsOfNextLine();

        // the collection of data needed for this transition
        TransitionParseObject tpo = new TransitionParseObject();

        /* Add the start state label to the transition object to return
        which is the state the transition starts in. */
        tpo.startStateLabel = lineElements[START_STATE_SYMBOL_INDEX];

        // add the input symbol to the transition object
        tpo.inputSymbol = lineElements[INPUT_SYMBOL_INDEX];

        /* Create the transition object to return, including the next state object, the output symbol
        and the direction where the read/write head will go after it has written the output. */
        tpo.transition = new Transition(tm.getState(lineElements[NEXT_STATE_LABEL_INDEX]),
                lineElements[OUTPUT_SYMBOL_INDEX],
                TapeTransitionDirection.parseTapeTransitionDirection(lineElements[TRANSITION_DIRECTION_INDEX]));

        return tpo;
    }

    /**
     * Set up the file streams.
     * @param filename The file to read.
     */
    private void setUpFileInput(String filename) throws FileNotFoundException {

        // try to create a scanner for the Turing machine creation file
        this.fileScanner = new Scanner(new File(filename));

    }

    /**
     * Get the individual components of the next line in the file, so long as it
     * is not a comment line.
     * @return The next non-comment line in the file, split into separate components.
     */
    private String[] getElementsOfNextLine() {

        // keeps the loop condition while the line is a comment line, empty or whitespace.
        boolean shouldIgnoreLine = true;

        //the next line read in from the file.
        String line = "";

        // keep looking for a line while the line is not empty, whitespace or comment
        while(shouldIgnoreLine) {

            // get the next line from the file
            line = fileScanner.nextLine();

            System.out.println("Line : " + line);
            System.out.println("Line is null : " + (line == null));
            /* If the line is a comment, empty or whitespace then exit the loop, otherwise
            keep searching for a non-comment line. */
            shouldIgnoreLine = !lineIsMeaningful(line);

            System.out.println("Should ignore line : " + shouldIgnoreLine);
        }

        return line.split("\\s+");
    }

    /**
     * Deterines whether given line has content required for machine.
     * @param line The line to check
     * @return True if line is meaninful, false is not.
     */
    private static boolean lineIsMeaningful(String line) {
        return !(lineIsEmpty(line) || lineIsComment(line) || lineIsWhitespace(line));
    }

    /**
     * Determines whether a given line if a comment or not.
     * @param line The line to check if it is a comment or not
     * @return True if line is comment, false if line is not comment.
     */
    private static boolean lineIsComment(String line) {
        return line.substring(0, COMMENT_REPRESENTATION.length()).equals(COMMENT_REPRESENTATION);
    }

    /**
     * Determines whether a given line is whitespace or not.
     * @param line The line to check if it is whitespace or not
     * @return True if line is whitespace, false if line is not whitespace.
     */
    private static boolean lineIsWhitespace(String line) {
        return line.matches("\\s+");
    }

    /**
     * Determines whether a given line is empty or not.
     * @param line The line to check if is empty.
     * @return True if line is empty, false is line is not empty.
     */
    private static boolean lineIsEmpty(String line) {
        return line.isEmpty();
    }
}
