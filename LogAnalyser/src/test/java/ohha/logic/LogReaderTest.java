package ohha.logic;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogReaderTest {
    
    public LogReaderTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void olematonTiedostoPalauttaaTyhjanListan() {
        LogReader instance = new LogReader();
        boolean expResult = true;
        boolean result = instance.readLog("spurdo").isEmpty();
        assertEquals(expResult, result);
    }
    
}
