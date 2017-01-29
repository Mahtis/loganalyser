package ohha.logic;

import java.util.List;
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
        int result = instance.readFile("/Users/mikkotiainen/Documents/test.log").size();
        assertEquals(expResult, result);
    }
    
}
