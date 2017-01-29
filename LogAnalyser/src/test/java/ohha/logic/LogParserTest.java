package ohha.logic;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ohha.domain.ExperimentInfo;
import ohha.domain.ResponseMapping;
import ohha.domain.Trial;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogParserTest {
    private LogParser parser;
    private List<Trial> trials;
    private ExperimentInfo info;
        
    @Before
    public void setUp() throws FileNotFoundException {
        info = new ExperimentInfo();
        
        List<String> conds = Arrays.asList("meS", "meV", "deV", "deS");
        info.setConditions(conds);
        
        List<String> responseCodes = Arrays.asList("1","2");
        info.setResponseNames(responseCodes);
        info.setResponseCodes(responseCodes);
        info.addResponseMapping(new ResponseMapping("meS"));
        info.addResponseMapping(new ResponseMapping("deS"));
        info.addResponseMapping(new ResponseMapping("meV"));
        info.addResponseMapping(new ResponseMapping("deV"));
        info.setSimpleCorrectResponses("meS", Arrays.asList("2"));
        info.setSimpleCorrectResponses("deS", Arrays.asList("2"));
        info.setSimpleCorrectResponses("meV", Arrays.asList("1"));
        info.setSimpleCorrectResponses("deV", Arrays.asList("1"));
        
        parser = new LogParser("/Users/mikkotiainen/Documents/test.log", info, 3);
        trials = parser.ParseIntoTrials();
    }

    @Test
    public void numberOfTrialsParsedIsCorrect() {
        int expResult = 120;
        int result = trials.size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void correctResponsesCategorizedRight() {
        int expResult = 113;
        int result = 0;
        for (Trial trial : trials) {
            if(trial.isCorrect()) {
                result++;
            }
        }
        assertEquals(expResult, result);
    }
    
    @Test(expected = FileNotFoundException.class)
    public void parserDoesntAcceptsOnlyPresentationLogs() throws FileNotFoundException {
        LogParser parser2 = new LogParser("/Users/mikkotiainen/Documents/test.pyc", info, 3);
    }
    
    
    
}
