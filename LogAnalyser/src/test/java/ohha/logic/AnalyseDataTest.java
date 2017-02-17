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
        trials.add(new Trial("kh1", 1, "A"));
        trials.add(new Trial("kh1", 2, "A"));
        trials.add(new Trial("kh1", 3, "A"));
        trials.add(new Trial("kh1", 4, "A"));
        trials.add(new Trial("kh1", 5, "A"));
        trials.add(new Trial("kh1", 6, "A"));
        trials.add(new Trial("kh1", 7, "A"));
        trials.add(new Trial("kh1", 8, "A"));
        trials.add(new Trial("kh1", 9, "A"));
        trials.get(0).setReactionTimes(Arrays.asList(100));
        trials.get(1).setReactionTimes(Arrays.asList(500));
        trials.get(2).setReactionTimes(Arrays.asList(500));
        trials.get(3).setReactionTimes(Arrays.asList(500));
        trials.get(4).setReactionTimes(Arrays.asList(500));
        trials.get(5).setReactionTimes(Arrays.asList(500));
        trials.get(6).setReactionTimes(Arrays.asList(500));
        trials.get(7).setReactionTimes(Arrays.asList(900));
        trials.get(8).setReactionTimes(Arrays.asList(900));
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
    
}
