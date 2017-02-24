package ohha.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileReaderTest {
    
    @Before
    public void setUp() {
    }

    @Test
    public void nonExistingFileReturnsEmptyArray() {
        FileReader instance = new FileReader();
        boolean expResult = true;
        boolean result = instance.readFile("spurdo").isEmpty();
        assertEquals(expResult, result);
    }
    
    @Test
    public void readFileIsCorrectSize() {
        FileReader instance = new FileReader();
        int expResult = 1636;
        int result = instance.readFile("src/main/resources/subj1_exp1.log").size();
        assertEquals(expResult, result);
    }
    
}
