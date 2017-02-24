
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
    
    @Before
    public void setUp() {
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
    public void loadingSimpleJsonSucceeds() {
        String filename = "src/main/resources/Experiment2.json";
        ExperimentInfo info2 = ExperimentInfoIO.loadFromJson(filename);
        int condSize = info2.getConditions().size();
        String name = info2.getName();
        int codeSize = info2.getResponseCodes().size();
        assertEquals(4, condSize);
        assertEquals("exp2", name);
        assertEquals(2, codeSize);
    }
    
    @Test
    public void loadingNoNameExpResultsInEmptyName() {
        assertEquals("empty", info.getName());
    }
    
    
    @Test
    public void savingSimpleJsonCreatesAFile() throws FileNotFoundException, IOException {
        String location = "src/main/resources/";
        String exp = "JUnitTest";
        ExperimentInfoIO.saveToJson(info, location, exp);
        assertTrue(Files.exists(Paths.get("src/main/resources/JUnitTest.json")));
        Files.deleteIfExists(Paths.get("src/main/resources/JUnitTest.json"));
    }
    
    @Test
    public void jsonStaysSameAfterSaveAndLoad() throws FileNotFoundException, IOException {
        String location = "src/main/resources/";
        String exp = "JUnitTest";
        ExperimentInfoIO.saveToJson(info, location, exp);
        ExperimentInfo info2 = ExperimentInfoIO.loadFromJson(location + exp + ".json");
        assertTrue(info.getConditions().containsAll(info2.getConditions()));
        assertTrue(info.getResponseCodes().containsAll(info2.getResponseCodes()));
        assertTrue(info.getResponseNames().containsAll(info2.getResponseNames()));
        Files.deleteIfExists(Paths.get("src/main/resources/JUnitTest.json"));
    }
    
    @Test
    public void savingAnEmptyJsonExpResultsInNothing() throws IOException {
        String location = "src/main/resources/";
        String exp = "EmptyTest";
        assertFalse(ExperimentInfoIO.saveToJson(new ExperimentInfo(), location, exp));
        Files.deleteIfExists(Paths.get("src/main/resources/EmptyTest.json"));
    }
    
}
