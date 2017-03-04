package ohha.domain;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrialTest {
    
    private Trial trial;
        
    @Before
    public void setUp() {
        trial = new Trial("kh1", 1, "meS", Arrays.asList("power"), Arrays.asList("1"), Arrays.asList(100));
        trial.setRespTimes(Arrays.asList(1000));
    }

    @Test
    public void cantSetNegativeResponseName() {
        int i = -1;
        String responseName = "Ding";
        trial.setSingleResponseName(i, responseName);
        assertFalse(trial.getResponseNames().contains(responseName));
    }

    @Test
    public void cantSetNegativeResponseCode() {
        int i = -1;
        String response = "20";
        trial.setSingleResponseCode(i, response);
        assertFalse(trial.getResponseCodes().contains(response));
    }

    @Test
    public void cantSetNegativeReactionTime() {
        int i = -1;
        int rt = 10000;
        trial.setSingleReactionTime(i, rt);
        assertFalse(trial.getReactionTimes().contains(rt));
    }

    @Test
    public void cantSetNegativeRespTime() {
        int i = -1;
        int respTime = -10;
        trial.setSingleRespTime(i, respTime);
        assertFalse(trial.getRespTimes().contains(respTime));
    }

    @Test
    public void cantSetResponseNameBiggerThanSize() {
        int i = trial.getResponseNames().size();
        String responseName = "Ding";
        trial.setSingleResponseName(i, responseName);
        assertFalse(trial.getResponseNames().contains(responseName));
    }

    @Test
    public void cantSetResponseCodeBiggerThanSize() {
        int i = trial.getResponseCodes().size();
        String response = "20";
        trial.setSingleResponseCode(i, response);
        assertFalse(trial.getResponseCodes().contains(response));
    }

    @Test
    public void cantSetReactionTimeBiggerThanSize() {
        int i = trial.getReactionTimes().size();
        int rt = 10000;
        trial.setSingleReactionTime(i, rt);
        assertFalse(trial.getReactionTimes().contains(rt));
    }

    @Test
    public void cantSetRespTimeBiggerThanSize() {
        int i = trial.getRespTimes().size();
        int respTime = -10;
        trial.setSingleRespTime(i, respTime);
        assertFalse(trial.getRespTimes().contains(respTime));
    }
    
    @Test
    public void settingVariablesCorrectly() {
        Trial t = new Trial(null, 0, null);
        t.setSubject("Pasi");
        t.setCondition("Test");
        t.setTrialNum(20);
        assertEquals("Pasi", t.getSubject());
        assertEquals(20, t.getTrialNum());
        assertEquals("Test", t.getCondition());
    }
    
    @Test
    public void trialPrintedCorrectly() {
        Trial t = new Trial("kh1", 1, "Test");
        t.setSingleResponseCode(0, "1");
        t.setSingleResponseName(0, "right");
        t.setSingleReactionTime(0, 200);
        t.setCorrect(true);
        String expResult = "kh1\t1\tTest\t[1]\t[right]\t[200]\ttrue";
        String result = t.toString();
        assertEquals(expResult, result);
    }
    
}
