package ohha.domain;

import java.io.FileNotFoundException;
import java.util.List;
import ohha.logic.ExperimentInfoIO;
import ohha.logic.LogParser;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class SubjectDataTest {
    
    private ExperimentInfo info;
    private LogParser parser;
    private List<Trial> trials;
    
    @Before
    public void setUp() throws FileNotFoundException {
        String filename = "src/main/resources/kati_exp.json";
        info = ExperimentInfoIO.loadFromJson(filename);
        parser = new LogParser("src/main/resources/kh01-ka_ti.log", info, 3);
        trials = parser.parseIntoTrials();
    }

    @Test
    public void dataWithNoNameSubjectIsNone() {
        SubjectData data = new SubjectData(null, null);
        String expResult = "none";
        assertEquals(expResult, data.getSubject());
    }
    
    @Test
    public void dataWithNameIsSetCorrectly() {
        SubjectData data = new SubjectData(info, trials);
        String expResult = trials.get(0).getSubject();
        String result = data.getSubject();
        assertEquals(expResult, result);
    }
    
    @Test
    public void dataNamePrintedCorrectly() {
        SubjectData data = new SubjectData(info, trials);
        String expResult = trials.get(0).getSubject() + "_" + info.getName() + "_data";
        String result = data.toString();
        assertEquals(expResult, result);
    }
    
    @Test
    public void variablesAreSetCorrectly() {
        SubjectData data = new SubjectData(null, null);
        data.setInfo(info);
        data.setSubject("Pasi");
        data.setTrials(trials);
        assertEquals(info, data.getInfo());
        assertEquals("Pasi", data.getSubject());
        assertEquals(trials, data.getTrials());
    }
}
