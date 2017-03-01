package Tests;

import DataStructures.TuringMachine;
import Enums.TuringMachineReturnCode;
import Parsers.TuringMachineFileParser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Automated tests class.
 */
public class BuildUnitTests {

    /**
     * Directory with all of the test materials needed.
     */
    private static final String testDirectory = "TestFiles/BuildUnitTests/";

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
     * Test the Turing machine accepts acceptable files with short lengths.
     */
    @Test
    public void testAcceptShortLength() {

        String testFileName = testDirectory + "testAcceptShort.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine accepts acceptable files with normal lengths.
     */
    @Test
    public void testAcceptNormalLength() {

        String testFileName = testDirectory + "testAcceptNormalLength.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine accepts acceptable files with long lengths.
     */
    @Test
    public void testAcceptLongLength() {

        String testFileName = testDirectory + "testAcceptLong.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine rejects invalid files with short lengths.
     */
    @Test
    public void testRejectShortLength() {

        String testFileName = testDirectory + "testRejectShort.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.REJECTED, result);

    }

    /**
     * Test the Turing machine rejects invalid files with normal lengths.
     */
    @Test
    public void testRejectNormalLength() {

        String testFileName = testDirectory + "testRejectNormalLength.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.REJECTED, result);

    }

    /**
     * Test the Turing machine rejects invalid files with long lengths.
     */
    @Test
    public void testRejectLongLength() {

        String testFileName = testDirectory + "testRejectLong.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.REJECTED, result);

    }

    /**
     * Test the Turing machine rejects the empty file for this Turing machine.
     */
    @Test
    public void testEmpty() {

        String testFileName = testDirectory + "testEmpty.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.REJECTED, result);

    }

    /**
     * Test the Turing machine rejects for the file with one 'a' character.
     */
    @Test
    public void testRejectSingleRejectableCharacter() {

        String testFileName = testDirectory + "testOneCharThenReject.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.REJECTED, result);

    }

    /**
     * Test that the Turing machine remains in accepting state after an acceptable
     * string, even if more symbols appear.
     */
    @Test
    public void testAcceptStateDoesNotChange() {

        String testFileName = testDirectory + "testTryToMoveFromAcceptState.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test that the Turing machine remains in reject state after an rejected
     * string, even if more symbols appear.
     */
    @Test
    public void testRejectStateDoesNotChange() {

        String testFileName = testDirectory + "testTryToMoveFromRejectState.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.REJECTED, result);

    }

}
