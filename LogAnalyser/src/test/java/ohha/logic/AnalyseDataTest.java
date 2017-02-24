package ohha.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ohha.domain.ExperimentInfo;
import ohha.domain.SubjectData;
import ohha.domain.Trial;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnalyseDataTest {
    
    private SubjectData data;
    private List<Integer> reactionTimes;
    private AnalyseData analyser;
    
    @Before
    public void setUp() {
        String filename = "src/main/resources/Experiment2.json";
        ExperimentInfo info = ExperimentInfoIO.loadFromJson(filename);
        List<Trial> trials = new ArrayList<>();
        trials.add(new Trial("kh1", 1, "meS"));
        trials.add(new Trial("kh1", 2, "meS"));
        trials.add(new Trial("kh1", 3, "meV"));
        trials.add(new Trial("kh1", 4, "deS"));
        trials.add(new Trial("kh1", 5, "A"));
        trials.add(new Trial("kh1", 6, "A"));
        trials.add(new Trial("kh1", 7, "A"));
        trials.add(new Trial("kh1", 8, "B"));
        trials.add(new Trial("kh1", 9, "B"));
        trials.get(0).setReactionTimes(Arrays.asList(100));
        trials.get(1).setReactionTimes(Arrays.asList(500));
        trials.get(2).setReactionTimes(Arrays.asList(500));
        trials.get(3).setReactionTimes(Arrays.asList(500));
        trials.get(4).setReactionTimes(Arrays.asList(500));
        trials.get(5).setReactionTimes(Arrays.asList(500));
        trials.get(6).setReactionTimes(Arrays.asList(500));
        trials.get(7).setReactionTimes(Arrays.asList(900));
        trials.get(8).setReactionTimes(Arrays.asList(900));
        trials.get(2).setCorrect(true);
        trials.get(1).setCorrect(true);
        trials.get(8).setCorrect(true);
        data = new SubjectData(info, trials);
        analyser = new AnalyseData(data);
        
    }

    
    @Test
    public void numberOfBinsIsCorrect() {
        int expResult = 3;
        int[] result = analyser.calculateHistogramValues();
        assertEquals(expResult, result.length);
    }
    
    @Test
    public void numberOfItemsInBinsIsCorrect() {
        int[] expResult = new int[]{1,6,2};
        int[] result = analyser.calculateHistogramValues();
        for(int i=0; i<result.length; i++) {
            System.out.println(result[i]);
            assertEquals(expResult[i], result[i]);
        }
        
    }
    
    @Test
    public void responseRatesNumberOfConditionsIsRight() {
        int expResult = 4;
        int result = analyser.calculateResponseRates().size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void responseRatesOneTrialCorrect() {
        int expResult = 100;
        int result = analyser.calculateResponseRates().get("meV");
        assertEquals(expResult, result);
    }
    
    @Test
    public void responseRatesTrialNotInExperimentIsNotIncluded() {
        assertEquals(null, analyser.calculateResponseRates().get("B"));
    }
    
    @Test
    public void responseRatesOneOfTwoIsCorrect() {
        int expResult = 50;
        int result = analyser.calculateResponseRates().get("meS");
        assertEquals(expResult, result);
    }
    
    @Test
    public void reactionTimesForCondsOnlyIncludesCondsInExperiment() {
        int expResult = 4;
        int result = analyser.reactionTimesForConditions().size();
        assertEquals(expResult, result);
    }
}
