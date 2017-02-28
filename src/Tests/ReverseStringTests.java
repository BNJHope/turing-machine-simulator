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

        String testString = "abc", resultStart = new String(new char[testString.length() + 1]).replace('\0', 'x');
        resultStart = '_' + resultStart.substring(1, resultStart.length());

        String testFileName = testDirectory + "testShortAccept.txt";
        TuringMachineReturnCode result = tm.checkIfInputIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);
        assertEquals(resultStart + "#" + new StringBuilder(testString).reverse().toString(), tm.getTape());

    }
}
