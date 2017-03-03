
package ohha.domain;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExperimentInfoTest {
    
    
    @Before
    public void setUp() {
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        ExperimentInfo instance = new ExperimentInfo();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class ExperimentInfo.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        ExperimentInfo instance = new ExperimentInfo();
        instance.setName(name);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResponseNameForCode method, of class ExperimentInfo.
     */
    @Test
    public void testGetResponseNameForCode() {
        System.out.println("getResponseNameForCode");
        String code = "";
        ExperimentInfo instance = new ExperimentInfo();
        String expResult = "";
        String result = instance.getResponseNameForCode(code);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of addResponseMapping method, of class ExperimentInfo.
     */
    @Test
    public void testAddResponseMapping() {
        System.out.println("addResponseMapping");
        ResponseMapping mapping = null;
        ExperimentInfo instance = new ExperimentInfo();
        boolean expResult = false;
        boolean result = instance.addResponseMapping(mapping);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumOfResponsesForCond method, of class ExperimentInfo.
     */
    @Test
    public void testGetNumOfResponsesForCond() {
        System.out.println("getNumOfResponsesForCond");
        String cond = "";
        ExperimentInfo instance = new ExperimentInfo();
        int expResult = 0;
        int result = instance.getNumOfResponsesForCond(cond);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCorrectResponses method, of class ExperimentInfo.
     */
    @Test
    public void testSetCorrectResponses() {
        System.out.println("setCorrectResponses");
        String cond = "";
        List<List<String>> correctResponses = null;
        ExperimentInfo instance = new ExperimentInfo();
        instance.setCorrectResponses(cond, correctResponses);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSimpleCorrectResponses method, of class ExperimentInfo.
     */
    @Test
    public void testSetSimpleCorrectResponses() {
        System.out.println("setSimpleCorrectResponses");
        String cond = "";
        List<String> correctResponses = null;
        ExperimentInfo instance = new ExperimentInfo();
        instance.setSimpleCorrectResponses(cond, correctResponses);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCorrect method, of class ExperimentInfo.
     */
    @Test
    public void testIsCorrect_3args() {
        System.out.println("isCorrect");
        String cond = "";
        String respCode = "";
        int nResp = 0;
        ExperimentInfo instance = new ExperimentInfo();
        boolean expResult = false;
        boolean result = instance.isCorrect(cond, respCode, nResp);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCorrect method, of class ExperimentInfo.
     */
    @Test
    public void testIsCorrect_String_List() {
        System.out.println("isCorrect");
        String cond = "";
        List<String> respCodes = null;
        ExperimentInfo instance = new ExperimentInfo();
        boolean expResult = false;
        boolean result = instance.isCorrect(cond, respCodes);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ExperimentInfo.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ExperimentInfo instance = new ExperimentInfo();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
