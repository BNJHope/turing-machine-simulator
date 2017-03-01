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
     * Test the Turing machine accepts long even acceptable palindromes.
     */
    @Test
    public void testAcceptLongEven() {

        String testFileName = testDirectory + "testAcceptLongEven.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine accepts long odd acceptable palindromes.
     */
    @Test
    public void testAcceptLongOdd() {

        String testFileName = testDirectory + "testAcceptLongOdd.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine accepts normal even acceptable palindromes.
     */
    @Test
    public void testAcceptNormalEven() {

        String testFileName = testDirectory + "testAcceptNormalEven.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine accepts normal odd acceptable palindromes.
     */
    @Test
    public void testAcceptNormalOdd() {

        String testFileName = testDirectory + "testAcceptNormalOdd.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine accepts short even acceptable palindromes.
     */
    @Test
    public void testAcceptShortEven() {

        String testFileName = testDirectory + "testAcceptShortEven.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine accepts short odd acceptable palindromes.
     */
    @Test
    public void testAcceptShortOdd() {

        String testFileName = testDirectory + "testAcceptShortOdd.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine rejects long even non-palindromes.
     */
    @Test
    public void testRejectLongEven() {

        String testFileName = testDirectory + "testRejectLongEven.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.REJECTED, result);

    }

    /**
     * Test the Turing machine rejects long odd non-palindromes.
     */
    @Test
    public void testRejectLongOdd() {

        String testFileName = testDirectory + "testRejectLongOdd.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.REJECTED, result);

    }

    /**
     * Test the Turing machine rejects normal even non-palindromes.
     */
    @Test
    public void testRejectNormalEven() {

        String testFileName = testDirectory + "testRejectNormalEven.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.REJECTED, result);

    }

    /**
     * Test the Turing machine rejects normal odd non-palindromes.
     */
    @Test
    public void testRejectNormalOdd() {

        String testFileName = testDirectory + "testRejectNormalOdd.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.REJECTED, result);

    }

    /**
     * Test the Turing machine rejects short even non-palindromes.
     */
    @Test
    public void testRejectShortEven() {

        String testFileName = testDirectory + "testRejectShortEven.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.REJECTED, result);

    }

    /**
     * Test the Turing machine rejects short odd non-palindromes.
     */
    @Test
    public void testRejectShortOdd() {

        String testFileName = testDirectory + "testRejectShortOdd.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.REJECTED, result);

    }

    /**
     * Test the Turing machine accepts empty file.
     */
    @Test
    public void testEmpty() {

        String testFileName = testDirectory + "testEmpty.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine accepts a single character.
     */
    @Test
    public void testSingleCharacter() {

        String testFileName = testDirectory + "testSingleCharacter.txt";
        TuringMachineReturnCode result = tm.checkIfFileIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }
}
