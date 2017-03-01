package Main;

import DataStructures.TuringMachine;
import Parsers.TuringMachineFileParser;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Prints data by repeatedly measuring times taken for different Turing machines to complete
 * with varying size data.
 */
public class DataMeasures {

    /**
     * Parser for the Turing machine input files.
     */
    private TuringMachineFileParser parser;

    public DataMeasures() {
        this.parser = new TuringMachineFileParser();
    }

    public void printPalindromeData() {

        String machineLocation = "TestFiles/Palindrome/machine_input.txt";

        TuringMachine tm = null;

        Instant start, end;

        try {
            tm = this.parser.parseFile(machineLocation);
        } catch (FileNotFoundException e) {
            System.err.println("Palindrome machine description file not found.");
            System.exit(1);
        }

        String testString;

        for(int i = 1; i < 50; i++) {
            testString = this.generateRandomPalindromeHalf(i);
            testString += new StringBuilder(testString).reverse().toString();
            start = Instant.now();
            tm.checkIfStringIsAccepted(testString);
        }


    }

    public void printBinaryAdditionData() {

    }

    public void printBubbleSortData() {

    }

    public void printReverseStringData() {

    }

    /**
     * Generates random half of palindrome string within {a,b,c} of the given length.
     * @param length Length of string to generate.
     * @return String of the given length of a random combination of {a,b,c}
     */
    private String generateRandomPalindromeHalf(int length) {
        String result = "";
        final int max = (int) 'c', min = (int) 'a';
        for(int i = 0; i < length; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            result += (char) randomNum;
        }

        return result;
    }
}
