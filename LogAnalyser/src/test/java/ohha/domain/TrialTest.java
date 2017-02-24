package ohha.domain;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrialTest {
    
    private Trial trial;
        
    @Before
    public void setUp() {
        trial = new Trial("kh1", 1, "meS");
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
        int i = 10;
        String responseName = "Ding";
        trial.setSingleResponseName(i, responseName);
        assertFalse(trial.getResponseNames().contains(responseName));
    }

    @Test
    public void cantSetResponseCodeBiggerThanSize() {
        int i = 10;
        String response = "20";
        trial.setSingleResponseCode(i, response);
        assertFalse(trial.getResponseCodes().contains(response));
    }

    @Test
    public void cantSetReactionTimeBiggerThanSize() {
        int i = 10;
        int rt = 10000;
        trial.setSingleReactionTime(i, rt);
        assertFalse(trial.getReactionTimes().contains(rt));
    }

    @Test
    public void cantSetRespTimeBiggerThanSize() {
        int i = 10;
        int respTime = -10;
        trial.setSingleRespTime(i, respTime);
        assertFalse(trial.getRespTimes().contains(respTime));
    }
    
}
