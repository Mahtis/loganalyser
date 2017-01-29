
package ohha.logic;

import ohha.domain.ExperimentInfo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExperimentInfoIOTest {
    
    public ExperimentInfoIOTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testLoadFromFile() {
        String filename = "/Users/mikkotiainen/Documents/test2.txt";
        ExperimentInfoIO instance = new ExperimentInfoIO();
        System.out.println(instance.loadFromFile(filename));
        int expResult = 4;
        int result = instance.loadFromFile(filename).getConditions().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of saveToFile method, of class ExperimentInfoIO.
     */
    @Test
    public void testSaveToFile() throws Exception {
        System.out.println("saveToFile");
        ExperimentInfo info = null;
        String location = "";
        String expName = "";
        ExperimentInfoIO instance = new ExperimentInfoIO();
        instance.saveToFile(info, location, expName);
        fail("The test case is a prototype.");
    }
    
}
