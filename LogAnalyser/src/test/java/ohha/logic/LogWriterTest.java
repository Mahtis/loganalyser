package ohha.logic;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void writtenLogfileIsCorrectLength() throws Exception {
        String destination = "src/main/resources/test2.txt";
        LogWriter.writeLogfile(trials, destination);
        List<String> lines = FileReader.readFile(destination);
        assertEquals(lines.size(), trials.size()+1);
        Files.deleteIfExists(Paths.get(destination));
    }
    
    @Test
    public void emptyListLeadsToOneLineLogfile() throws Exception {
        String destination = "src/main/resources/oneliner.txt";
        LogWriter.writeLogfile(new ArrayList<>(), destination);
        List<String> lines = FileReader.readFile(destination);
        assertEquals(lines.size(), 1);
        Files.deleteIfExists(Paths.get(destination));
    }
    
    @Test
    public void nullTrialsResultsILogfileWithNulls() throws Exception {
        String destination = "src/main/resources/nullList.txt";
        List<Trial> test = Arrays.asList(new Trial(null, 1, null));
        LogWriter.writeLogfile(test, destination);
        List<String> lines = FileReader.readFile(destination);
        assertTrue(lines.get(1).contains("null"));
        Files.deleteIfExists(Paths.get(destination));
    }
    
}
