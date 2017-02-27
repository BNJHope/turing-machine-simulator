package Tests;

import DataStructures.TuringMachine;
import Enums.TuringMachineReturnCode;
import Parsers.TuringMachineFileParser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Tests the palindrome machine
 */
public class PalindromeTests {

    /**
     * Directory with all of the test materials needed.
     */
    private static final String testDirectory = "TestFiles/Palindrome/";

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
     * Test the Turing machine accepts acceptable palindromes of length 5.
     */
    @Test
    public void testAcceptNormalOdd() {

        String testFileName = testDirectory + "testAcceptNormalOdd.txt";
        TuringMachineReturnCode result = tm.checkIfInputIsAccepted(testFileName);
        assertEquals(result, TuringMachineReturnCode.ACCPETED);

    }

}
