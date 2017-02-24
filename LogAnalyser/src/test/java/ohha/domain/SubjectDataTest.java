package ohha.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class SubjectDataTest {
        
    @Before
    public void setUp() {
    }

    @Test
    public void DataWithNoNameSubjectIsNone() {
        SubjectData data = new SubjectData(null, null);
        String expResult = "none";
        assertEquals(expResult, data.getSubject());
    }
}
