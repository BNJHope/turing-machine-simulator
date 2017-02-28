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
        final int PRINT_TAPE_FLAG_INDEX = 2;

        if(args.length < 2) {
            System.err.println("2 arguments needed - machine file and input file");
        }

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
            tm = turingMachineFileParser.parseFile(args[MACHINE_FILE_NAME_INDEX]);
        } catch (FileNotFoundException e) {
            System.err.println("Turing machine file not found.");
            System.exit(1);
        }

        System.out.println("Turing machine created!\nChecking input...");

        /*
        Handle the result from the Turing machine processing the input.
         */
        handleOutput(tm.checkIfInputIsAccepted(args[INPUT_FINAL_NAME_INDEX]));

        if(args.length > 2) {
            if (args[PRINT_TAPE_FLAG_INDEX].equals("-v")) {
                System.out.println("\nFinal Tape :\n" + tm.getTape());
            }
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
}
