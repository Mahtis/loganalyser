package ohha.logic;

import java.io.FileNotFoundException;
import java.util.List;
import ohha.domain.ExperimentInfo;
import ohha.domain.Trial;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogWriterTest {
    List<Trial> trials;
    
    @Before
    public void setUp() throws FileNotFoundException {
        String logfile = "src/main/resources/subj1_exp1.log";
        String expFile = "src/main/resources/Experiment2.json";
        ExperimentInfoIO expIO = new ExperimentInfoIO();
        ExperimentInfo info = expIO.loadFromJson(expFile);

        LogParser parser = new LogParser(logfile, info, 3);
        trials = parser.parseIntoTrials();
    }

    @Test
    public void writeLogfileIsCorrectLength() throws Exception {
        String destination = "src/main/resources/test2.txt";
        LogWriter.writeLogfile(trials, destination);
        List<String> lines = FileReader.readFile(destination);
        assertEquals(lines.size(), trials.size()+1);
    }
    
}
