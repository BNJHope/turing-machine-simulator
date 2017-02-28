package Tests;

import DataStructures.TuringMachine;
import Enums.TuringMachineReturnCode;
import Parsers.TuringMachineFileParser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Tests for reverse string Turing machine.
 */
public class ReverseStringTests {

    /**
     * Directory with all of the test materials needed.
     */
    private static final String testDirectory = "TestFiles/ReverseString/";

    /**
     * Name of the file that defines where the machine is.
     */
    private static final String machineFileName = testDirectory + "machine_input.txt";

    /**
     * The Turing machine to be used in the tests.
     */
    private static TuringMachine tm;

    /**
     * Create the Turing machine from the file before we run the tests.
     */
    @BeforeClass
    public static void crateTuringMachineFromFile() {

        TuringMachineFileParser parser = new TuringMachineFileParser();

        try {
            tm = parser.parseFile(machineFileName);
        } catch (FileNotFoundException e) {
            System.err.println("Machine file not found");
        }

    }

    /**
     * Test the Turing machine accepts short strings with a hash tag on the end and generates
     * reverse string correctly.
     */
    @Test
    public void testShortAccept() {

        /* The string we are trying to reverse */
        String testString = "ab";

        String testFileName = testDirectory + "testShortAccept.txt";
        TuringMachineReturnCode result = tm.checkIfInputIsAccepted(testFileName);

        // check the code is accepted
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

        /**
         * Gets a string representation of all characters on the tape and the actual reverse string we want to check,
         * which is contained in all tape segments after the '#' character.
         */
        String tapeResult = tm.getTape(), reverseString = tapeResult.substring(tapeResult.indexOf("#") + 1, tapeResult.length());

        assertEquals(new StringBuilder(testString).reverse().toString(), reverseString);

    }

    /**
     * Test the Turing machine accepts medium strings with a hash tag on the end and generates
     * reverse string correctly.
     */
    @Test
    public void testMediumAccept() {

        /* The string we are trying to reverse */
        String testString = "abcabcabc";

        String testFileName = testDirectory + "testMediumAccept.txt";
        TuringMachineReturnCode result = tm.checkIfInputIsAccepted(testFileName);

        // check the code is accepted
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

        /**
         * Gets a string representation of all characters on the tape and the actual reverse string we want to check,
         * which is contained in all tape segments after the '#' character.
         */
        String tapeResult = tm.getTape(), reverseString = tapeResult.substring(tapeResult.indexOf("#") + 1, tapeResult.length());

        assertEquals(new StringBuilder(testString).reverse().toString(), reverseString);

    }

    /**
     * Test the Turing machine accepts long strings with a hash tag on the end and generates
     * reverse string correctly.
     */
    @Test
    public void testLongAccept() {

        /* The string we are trying to reverse */
        String testString = "abcabcabcabcabcabcabcabcabcabcabcacbacbbacbacbabcabbacbacbabbacbacbabcabcbac";

        String testFileName = testDirectory + "testLongAccept.txt";
        TuringMachineReturnCode result = tm.checkIfInputIsAccepted(testFileName);

        // check the code is accepted
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

        /**
         * Gets a string representation of all characters on the tape and the actual reverse string we want to check,
         * which is contained in all tape segments after the '#' character.
         */
        String tapeResult = tm.getTape(), reverseString = tapeResult.substring(tapeResult.indexOf("#") + 1, tapeResult.length());

        assertEquals(new StringBuilder(testString).reverse().toString(), reverseString);

    }

    /**
     * Test the Turing machine accepts one character strings with a hash tag on the end and generates
     * reverse string correctly.
     */
    @Test
    public void testOneCharacter() {

        /* The string we are trying to reverse */
        String testString = "a";

        String testFileName = testDirectory + "testOneCharacter.txt";
        TuringMachineReturnCode result = tm.checkIfInputIsAccepted(testFileName);

        // check the code is accepted
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

        /**
         * Gets a string representation of all characters on the tape and the actual reverse string we want to check,
         * which is contained in all tape segments after the '#' character.
         */
        String tapeResult = tm.getTape(), reverseString = tapeResult.substring(tapeResult.indexOf("#") + 1, tapeResult.length());

        assertEquals(new StringBuilder(testString).reverse().toString(), reverseString);

    }

    /**
     * Test the Turing machine accepts strings with no characters i.e only a hash tag.
     */
    @Test
    public void testNoCharacter() {

        /* The string we are trying to reverse */
        String testString = "";

        String testFileName = testDirectory + "testNoCharacter.txt";
        TuringMachineReturnCode result = tm.checkIfInputIsAccepted(testFileName);

        // check the code is accepted
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

        /**
         * Gets a string representation of all characters on the tape and the actual reverse string we want to check,
         * which is contained in all tape segments after the '#' character.
         */
        String tapeResult = tm.getTape(), reverseString = tapeResult.substring(tapeResult.indexOf("#") + 1, tapeResult.length());

        assertEquals(new StringBuilder(testString).reverse().toString(), reverseString);

    }

}
