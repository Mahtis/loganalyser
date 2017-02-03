
package ohha.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import ohha.domain.ExperimentInfo;
import ohha.domain.ResponseMapping;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExperimentInfoIOTest {
    
    private ExperimentInfo info;
    
    private ExperimentInfoIO io;
    
    @Before
    public void setUp() {
        io = new ExperimentInfoIO();
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
    }

    @Test
    public void loadingASimpleFileSucceeds() {
        String filename = "src/main/resources/ExpTest.txt";
        ExperimentInfoIO instance = new ExperimentInfoIO();
        System.out.println(instance.loadFromFile(filename));
        int expResult = 4;
        int result = instance.loadFromFile(filename).getConditions().size();
        assertEquals(expResult, result);
    }


    @Test
    public void savingAFileSucceeds() throws Exception {
        String location = "src/main/resources/";
        String expName = "test3";
        ExperimentInfoIO instance = new ExperimentInfoIO();
        instance.saveToFile(info, location, expName);
        assertTrue(Files.exists(Paths.get("src/main/resources/test3.txt")));
        Files.deleteIfExists(Paths.get("src/main/resources/test3.txt"));
    }
    
    @Test
    public void fileStaysSameAfterSaveAndLoad() throws Exception {
        String location = "src/main/resources/";
        String expName = "test3";
        ExperimentInfoIO instance = new ExperimentInfoIO();
        instance.saveToFile(info, location, expName);
        ExperimentInfo info2 = instance.loadFromFile("src/main/resources/test3.txt");
        assertTrue(info.getConditions().containsAll(info2.getConditions()));
        assertTrue(info.getResponseCodes().containsAll(info2.getResponseCodes()));
        assertTrue(info.getResponseNames().containsAll(info2.getResponseNames()));
        Files.deleteIfExists(Paths.get("src/main/resources/test3.txt"));
    }
    
    @Test
    public void fileWithSameFilenameIsOverWritten() throws Exception {
        String location = "src/main/resources/";
        String expName = "test3";
        ExperimentInfoIO instance = new ExperimentInfoIO();
        instance.saveToFile(info, location, expName);
        int len = Files.readAllLines(Paths.get("src/main/resources/test3.txt")).size();
        instance.saveToFile(info, location, expName);
        int len2 = Files.readAllLines(Paths.get("src/main/resources/test3.txt")).size();
        assertEquals(len, len2);
        Files.deleteIfExists(Paths.get("src/main/resources/test3.txt"));
    }
    
    @Test
    public void loadingNonExistingFileResultsInNull() {
        String filename = "src/main/resources/derpderp.derp";
        ExperimentInfoIO instance = new ExperimentInfoIO();
        System.out.println(instance.loadFromFile(filename));
        ExperimentInfo result = instance.loadFromFile(filename);
        assertEquals(null, result);
    }
    
    @Test
    public void loadingSimpleJsonSucceeds() {
        String filename = "src/main/resources/Experiment2.json";
        ExperimentInfo info2 = io.loadFromJson(filename);
        int condSize = info2.getConditions().size();
        assertEquals(condSize, 4);
    }
    
    @Test
    public void savingSimpleJsonCreatesAFile() throws FileNotFoundException, IOException {
        String location = "src/main/resources/";
        String exp = "JUnitTest";
        io.saveToJson(info, location, exp);
        assertTrue(Files.exists(Paths.get("src/main/resources/JUnitTest.json")));
        Files.deleteIfExists(Paths.get("src/main/resources/JUnitTest.json"));
    }
    
    @Test
    public void jsonStaysSameAfterSaveAndLoad() throws FileNotFoundException, IOException {
        String location = "src/main/resources/";
        String exp = "JUnitTest";
        io.saveToJson(info, location, exp);
        ExperimentInfo info2 = io.loadFromJson(location + exp + ".json");
        assertTrue(info.getConditions().containsAll(info2.getConditions()));
        assertTrue(info.getResponseCodes().containsAll(info2.getResponseCodes()));
        assertTrue(info.getResponseNames().containsAll(info2.getResponseNames()));
        Files.deleteIfExists(Paths.get("src/main/resources/JUnitTest.json"));
    }
    
    @Test
    public void savingAnEmptyJsonExpResultsInNothing() throws IOException {
        String location = "src/main/resources/";
        String exp = "EmptyTest";
        assertFalse(io.saveToJson(new ExperimentInfo(), location, exp));
        Files.deleteIfExists(Paths.get("src/main/resources/EmptyTest.json"));
    }
    
}
