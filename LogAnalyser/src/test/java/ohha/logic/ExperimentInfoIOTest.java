
package ohha.logic;

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
    public void loadingASimpleFileSucceeds() {
        String filename = "/Users/mikkotiainen/Documents/test2.txt";
        ExperimentInfoIO instance = new ExperimentInfoIO();
        System.out.println(instance.loadFromFile(filename));
        int expResult = 4;
        int result = instance.loadFromFile(filename).getConditions().size();
        assertEquals(expResult, result);
    }


    @Test
    public void savingAFileSucceeds() throws Exception {
        String location = "/Users/mikkotiainen/Documents/";
        String expName = "test3";
        ExperimentInfoIO instance = new ExperimentInfoIO();
        instance.saveToFile(info, location, expName);
        assertTrue(Files.exists(Paths.get("/Users/mikkotiainen/Documents/test3.txt")));
    }
    
    @Test
    public void fileStaysSameAfterSaveAndLoad() throws Exception {
        String location = "/Users/mikkotiainen/Documents/";
        String expName = "test3";
        ExperimentInfoIO instance = new ExperimentInfoIO();
        instance.saveToFile(info, location, expName);
        ExperimentInfo info2 = instance.loadFromFile("/Users/mikkotiainen/Documents/test3.txt");
        assertTrue(info.getConditions().containsAll(info2.getConditions()));
        assertTrue(info.getResponseCodes().containsAll(info2.getResponseCodes()));
        assertTrue(info.getResponseNames().containsAll(info2.getResponseNames()));
    }
    
    @Test
    public void fileWithSameFilenameIsOverWritten() throws Exception {
        String location = "/Users/mikkotiainen/Documents/";
        String expName = "test3";
        ExperimentInfoIO instance = new ExperimentInfoIO();
        instance.saveToFile(info, location, expName);
        int len = Files.readAllLines(Paths.get("/Users/mikkotiainen/Documents/test3.txt")).size();
        instance.saveToFile(info, location, expName);
        int len2 = Files.readAllLines(Paths.get("/Users/mikkotiainen/Documents/test3.txt")).size();
        assertEquals(len, len2);
    }
    
    @Test
    public void loadingNonExistingFileResultsInNull() {
        String filename = "/Users/mikkotiainen/Documents/derpderp.derp";
        ExperimentInfoIO instance = new ExperimentInfoIO();
        System.out.println(instance.loadFromFile(filename));
        ExperimentInfo result = instance.loadFromFile(filename);
        assertEquals(null, result);
    }
    
}
