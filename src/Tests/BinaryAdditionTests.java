package Tests;

import DataStructures.TuringMachine;
import Enums.TuringMachineReturnCode;
import Parsers.TuringMachineFileParser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Tests for binary addition machine.
 */
public class BinaryAdditionTests {
    /**
     * Directory with all of the test materials needed.
     */
    private static final String testDirectory = "TestFiles/BinaryAddition/";

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
     * Test the Turing machine accepts short acceptable binary additions results of same length.
     */
    @Test
    public void testAcceptShortEven() {

        String testFileName = testDirectory + "testShortEvenAccept.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine accepts short acceptable binary additions results of differing length.
     */
    @Test
    public void testAcceptShortOdd() {

        String testFileName = testDirectory + "testShortOddAccept.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine accepts normal length acceptable binary additions results of same length.
     */
    @Test
    public void testAcceptNormalEven() {

        String testFileName = testDirectory + "testNormalEvenAccept.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine accepts normal length acceptable binary additions results of differing length.
     */
    @Test
    public void testAcceptNormalOdd() {

        String testFileName = testDirectory + "testNormalOddAccept.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }



    /**
     * Test the Turing machine accepts long length acceptable binary additions results of same length.
     */
    @Test
    public void testAcceptLongEven() {

        String testFileName = testDirectory + "testLongEvenAccept.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine accepts long length acceptable binary additions results of differing length.
     */
    @Test
    public void testAcceptLongOdd() {

        String testFileName = testDirectory + "testLongOddAccept.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine deals with carrying correctly when the first number is longer
     */
    @Test
    public void testCarryFirstLonger() {

        String testFileName = testDirectory + "testCarryFirstLonger.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine deals with carrying correctly when the second number is longer
     */
    @Test
    public void testCarrySecondLonger() {

        String testFileName = testDirectory + "testCarrySecondLonger.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine returns rejected when the addition is wrong.
     */
    @Test
    public void testReject() {

        String testFileName = testDirectory + "testReject.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.REJECTED, result);

    }

    /**
     * Test the Turing machine returns rejected when the input is invalid.
     */
    @Test
    public void testInvalidCombination() {

        String testFileName = testDirectory + "testInvalidCombination.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.REJECTED, result);

    }
}
