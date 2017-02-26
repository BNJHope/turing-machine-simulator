import java.io.File;
import java.io.FileInputStream;
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
        the number of states in the machine * (the size of the alphabet + 1 for the blank character) */
        int numberOfTransistions = numOfStates * (tapeAlphabet.size() + 1);

        // get the alphabet line from the file
        tm.addAlphabet(tapeAlphabet);

        // read all of the transitions from the file and add them to the machine
        for(int transitionStepper = 0; transitionStepper < numberOfTransistions; transitionStepper++) {
            tm.addTransition(this.parseTransition(tm));
        }

        return tm;
    }

    /**
     * Read the file to get the number of states in the Turing Machine
     * @return The integer of how many states there are in the machine.
     */
    private int parseNumberOfStates() {
        return Integer.parseInt(this.getElementsOfNextLine()[1]);
    }

    /**
     * Parse a state from the input file.
     * @return The instance of the state represented by the line of the file
     */
    private State parseState() {
        String elements[] = this.getElementsOfNextLine();
        State newstate;

        if(elements.length == 1) newstate = new State(elements[0]);
        else newstate = (elements[1].equals("+")) ? new State(elements[0], true, false) : new State(elements[0], false, true);

        return newstate;
    }

    /**
     * Parse the alphabet line in the input file.
     * @return The arraylist representing the tape alphabet of the Turing Machine.
     */
    private ArrayList<String> parseAlphabetLine() {

        //get elements of the next line
        String elements[] = this.getElementsOfNextLine();

        //list of elements in the alphabet
        ArrayList<String> alphabetList = new ArrayList();

        alphabetList.addAll(Arrays.asList(elements).subList(2, elements.length));

        return alphabetList;
    }

    /**
     * Parse a transition line from the input file.
     * @return An object containing the data contained in the transition line.
     */
    private TransitionParseObject parseTransition(TuringMachine tm) {

        String lineElements[] = this.getElementsOfNextLine();

        TransitionParseObject tpo = new TransitionParseObject();

        tpo.startStateLabel = lineElements[0];

        tpo.inputSymbol = lineElements[1];

        tpo.transition = new Transition(tm.getState(lineElements[2]), lineElements[3], TapeTransitionDirection.parseTapeTransitionDirection(lineElements[4]));

        return tpo;
    }

    /**
     * Set up the file streams.
     * @param filename The file to read.
     */
    private void setUpFileInput(String filename) {

        //the input stream from the file
        FileInputStream input = null;

        try {
            input = new FileInputStream(new File(filename));
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            System.exit(1);
        }

        // a scanner to wrap around the file input stream
        this.fileScanner = new Scanner(input);

    }

    /**
     * Get the individual components of the next line in the file.
     * @return The next line in the file, split into separate components.
     */
    private String[] getElementsOfNextLine() {
        return this.fileScanner.nextLine().split("s+");
    }
}
