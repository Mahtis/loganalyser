package ohha.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ResponseMappingTest {
    
    private ResponseMapping mapping1;
    private ResponseMapping mapping2;
    
    @Before
    public void setUp() {
        mapping1 = new ResponseMapping("Test");
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList("1"));
        mapping1.setCorrectResponses(list);
        
        mapping2 = new ResponseMapping("Test2");
        List<List<String>> list2 = new ArrayList<>();
        list2.add(Arrays.asList("1","2"));
        list2.add(Arrays.asList("2"));
        list2.add(Arrays.asList("1"));
        mapping2.setCorrectResponses(list2);
    }


    @Test
    public void correctResponseReturnsTrue() {
        List<String> resps = Arrays.asList("1");
        assertTrue(mapping1.isCorrect(resps));
    }

    @Test
    public void multiplecorrectResponsesReturnsTrue() {
        List<String> resps = Arrays.asList("2","2","1");
        assertTrue(mapping2.isCorrect(resps));
    }
    
    @Test
    public void wrongSizeResponseListReturnsFalse() {
        List<String> resps = Arrays.asList("2","2","1");
        assertFalse(mapping1.isCorrect(resps));
    }
    
    @Test
    public void oneFalseResponseReturnsFalse() {
        List<String> resps = Arrays.asList("1","1","1");
        assertFalse(mapping2.isCorrect(resps));
    }

    @Test
    public void mappingsWithSameConditionsAreConsideredEqual() {
        ResponseMapping instance = new ResponseMapping("Test");
        assertTrue(instance.equals(mapping1));
    }
    
    @Test
    public void nullMappingIsNotEqual() {
        ResponseMapping instance = null;
        assertFalse(mapping1.equals(instance));
    }
    
    @Test
    public void objectNotMappingIsNotEqual() {
        String instance = "Test";
        assertFalse(mapping1.equals(instance));
    }
    
    @Test
    public void mappingsWithDiffeerentConditionsAreConsideredEqual() {
        ResponseMapping instance = new ResponseMapping("NOT TEST");
        assertFalse(instance.equals(mapping1));
    }
    
    @Test
    public void mappingWrittenToStringCorrectly() {
        String expResult = "Test: [[1]]";
        String result = mapping1.toString();
        assertEquals(expResult, result);
    }
    
}
