package Main;

import DataStructures.TuringMachine;
import Enums.TuringMachineReturnCode;
import Parsers.TuringMachineFileParser;

import java.io.FileNotFoundException;

/**
 * Main class for running Turing Machine.
 */
public class TuringMachineMain {

    public static void main(String[] args) {

        /* Index in the args of where the file name
        of the file that defines the machine is located. */
        final int MACHINE_FILE_NAME_INDEX = 0;

        /* Index in the args of where the file name
        of the file that gives the input for the machine is located. */
        final int INPUT_FINAL_NAME_INDEX = 1;

        /* Index for where the print flag is. */
        final int FLAG_INDEX = 2;

        /* If there is only one argument and it is the "-d" for data flag then
        * run the data collection material. */
        if (args.length == 1 && args[0].equals("-d")) {
            runDevelopmentData();

        /* Otherwise, close the system if there are not at least 2 arguments. */
        } else if(args.length < 2) {
            System.err.println("2 arguments needed - machine file and input file");
            System.exit(1);
        } else {
            runTuringMachine(args[MACHINE_FILE_NAME_INDEX], args[INPUT_FINAL_NAME_INDEX], (args.length >= 3 && args[FLAG_INDEX].equals("-v")));
        }

    }

    /**
     * Main method for running a Turing machine with given machine description file name and input file name.
     * @param machineDescriptionFileName The name of the file with the Turing machine description.
     * @param inputFileName The name of the file with the input for the machine.
     * @param verboseMode Determines if the flag to print tape segment was applied or not.
     */
    public static void runTuringMachine(String machineDescriptionFileName, String inputFileName, boolean verboseMode) {

        System.out.println("");
        System.out.println("Creating Turing machine...");
        /*
        The parser to create the Turing machine from the machine file.
         */
        TuringMachineFileParser turingMachineFileParser = new TuringMachineFileParser();

        /*
        Try to create the Turing Machine using the parser and the input file.
         */
        TuringMachine tm = null;

        try {
            tm = turingMachineFileParser.parseFile(machineDescriptionFileName);
        } catch (FileNotFoundException e) {
            System.err.println("Turing machine file not found.");
            System.exit(1);
        }

        System.out.println("Turing machine created!\nChecking input...");

        /*
        Handle the result from the Turing machine processing the input.
         */
        handleOutput(tm.checkIfFileIsAccepted(inputFileName));

        if(verboseMode) {
            System.out.println("\nFinal Tape :\n" + tm.getTape());
        }

        System.out.println();
    }

    /**
     * Handles the result of the given input in the Turing machine from the machine file.
     * @param result The result from the Turing machine processing the input.
     */
    private static void handleOutput(TuringMachineReturnCode result) {

        switch (result) {
            case ACCEPTED:
                System.out.println("Accepted");
                break;
            case REJECTED:
                System.out.println("Rejected");
                break;
            case UNEXPECTED_TERMINATION:
                System.out.println("Unexpectedly terminated");
                break;
        }

    }

    /**
     * Runs the given data measure function. This was swapped in and out depending on what needed to be printed.
     */
    private static void runDevelopmentData() {
        DataMeasures dataMeasures = new DataMeasures();
        dataMeasures.printSquareData();
    }
}
