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
        
        List<String> conds = Arrays.asList("kaS", "tiV", "kaV", "tiS");
        info.setConditions(conds);
        
        List<String> responseCodes = Arrays.asList("1","2");
        info.setResponseNames(responseCodes);
        info.setResponseCodes(responseCodes);
        info.addResponseMapping(new ResponseMapping("kaS"));
        info.addResponseMapping(new ResponseMapping("tiS"));
        info.addResponseMapping(new ResponseMapping("kaV"));
        info.addResponseMapping(new ResponseMapping("tiV"));
        info.setSimpleCorrectResponses("kaS", Arrays.asList("1"));
        info.setSimpleCorrectResponses("tiS", Arrays.asList("1"));
        info.setSimpleCorrectResponses("kaV", Arrays.asList("2"));
        info.setSimpleCorrectResponses("tiV", Arrays.asList("2"));
        
        parser = new LogParser("src/main/resources/kh01-ka_ti.log", info, 3);
        trials = parser.parseIntoTrials();
        //for (Trial trial : trials) {
        //    System.out.println(trial);
        //}
    }

    @Test
    public void numberOfTrialsParsedIsCorrect() {
        int expResult = 120;
        int result = trials.size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void numberOfTrialsIsCorrect() {
        int expResult = 120;
        int result = trials.get(trials.size()-1).getTrialNum();
        assertEquals(expResult, result);
    }
    
    @Test
    public void correctResponsesCategorizedRight() {
        int expResult = 117;
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
        LogParser parser2 = new LogParser("src/main/resources/test.pyc", info, 3);
    }
    
    @Test
    public void missingResponseNamesAreReplacedByNone() throws FileNotFoundException {
        LogParser parser2 = new LogParser("src/main/resources/kh01-ka_ti_del2.log", info, 3);
        List<Trial> trials2 = parser2.parseIntoTrials();
        String expResult = "none";
        assertEquals(expResult, trials2.get(1).getResponseNames().get(0));
    }
    
    @Test
    public void missingResponseCodesAreReplacedByZero() throws FileNotFoundException {
        LogParser parser2 = new LogParser("src/main/resources/kh01-ka_ti_del2.log", info, 3);
        List<Trial> trials2 = parser2.parseIntoTrials();
        String expResult = "0";
        assertEquals(expResult, trials2.get(1).getResponseCodes().get(0));
    }
    
    @Test
    public void missingResponseTimesAreReplacedByZero() throws FileNotFoundException {
        LogParser parser2 = new LogParser("src/main/resources/kh01-ka_ti_del2.log", info, 3);
        List<Trial> trials2 = parser2.parseIntoTrials();
        Integer expResult = 0;
        assertEquals(expResult, trials2.get(1).getReactionTimes().get(0));
        assertEquals(expResult, trials2.get(1).getRespTimes().get(0));
    }
    
}
