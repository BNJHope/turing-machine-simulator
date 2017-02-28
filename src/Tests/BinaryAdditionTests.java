package Tests;

import DataStructures.TuringMachine;
import Enums.TuringMachineReturnCode;
import Parsers.TuringMachineFileParser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Created by bh59 on 28/02/17.
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
        TuringMachineReturnCode result = tm.checkIfInputIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine accepts short acceptable binary additions results of differing length.
     */
    @Test
    public void testAcceptShortOdd() {

        String testFileName = testDirectory + "testShortOddAccept.txt";
        TuringMachineReturnCode result = tm.checkIfInputIsAccepted(testFileName);
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine accepts normal length acceptable binary additions results of same length.
     */
    @Test
    public void testAcceptNormalEven() {

        System.out.println("\n\nStarting normal even\n\n");
        String testFileName = testDirectory + "testNormalEvenAccept.txt";
        TuringMachineReturnCode result = tm.checkIfInputIsAccepted(testFileName);
        System.out.println("\n\nEnding normal even\n\n");
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }

    /**
     * Test the Turing machine accepts normal length acceptable binary additions results of differing length.
     */
    @Test
    public void testAcceptNormalOdd() {

        System.out.println("\n\nStarting normal odd\n\n");
        String testFileName = testDirectory + "testNormalOddAccept.txt";
        TuringMachineReturnCode result = tm.checkIfInputIsAccepted(testFileName);
        System.out.println("\n\nEnding normal odd\n\n");
        assertEquals(TuringMachineReturnCode.ACCEPTED, result);

    }


}
