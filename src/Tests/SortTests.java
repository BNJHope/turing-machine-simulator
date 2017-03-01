package Tests;

import DataStructures.TuringMachine;
import Enums.TuringMachineReturnCode;
import Parsers.TuringMachineFileParser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for Turing machine for sorting.
 */
public class SortTests {

    /**
     * Directory with all of the test materials needed.
     */
    private static final String testDirectory = "TestFiles/Sort/";

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
     * Test the Turing machine sorts short sequences correctly.
     */
    @Test
    public void testAcceptShort() {

        String testString = "abc";
        String testFileName = testDirectory + "shortSort.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        String expected = "_" + testString + "_", actual = tm.getTape();
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);
        assertEquals(expected, actual);

    }

    /**
     * Test the Turing machine sorts medium sequences correctly.
     */
    @Test
    public void testAcceptMedium() {

        String testString = "aaaaabbbbbccccc";
        String testFileName = testDirectory + "mediumSort.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        String expected = "_" + testString + "_", actual = tm.getTape();
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);
        assertEquals(expected, actual);

    }

    /**
     * Test the Turing machine sorts medium sequences correctly.
     */
    @Test
    public void testAcceptLong() {

        String testString = "aaaaaaaaaabbbbbbbbbbcccccccccc";
        String testFileName = testDirectory + "longSort.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        String expected = "_" + testString + "_", actual = tm.getTape();
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);
        assertEquals(expected, actual);

    }
}
