
package ohha.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExperimentInfoTest {
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
    public void nullConstructorParametersCreateEmptyArrayLists() {
        ExperimentInfo info2 = new ExperimentInfo(null,null,null,null,null);
        boolean expResult = true;
        boolean result = info2.getName().equals("empty") &&
                info2.getConditions().isEmpty() &&
                info2.getResponseCodes().isEmpty() &&
                info2.getResponseNames().isEmpty() &&
                info2.getResponseMappings().isEmpty();
        assertEquals(expResult, result);
    }
    
    @Test
    public void AllParametersSetConstructorCreatesFullExperiment() {
        ExperimentInfo info2 = new ExperimentInfo(info.getName(),info.getConditions(),info.getResponseCodes(),info.getResponseNames(),info.getResponseMappings());
        boolean expResult = true;
        boolean result = info2.getName().equals(info.getName()) &&
                info2.getConditions() == info.getConditions() &&
                info2.getResponseCodes() == info.getResponseCodes() &&
                info2.getResponseNames() == info.getResponseNames() &&
                info2.getResponseMappings() == info.getResponseMappings();
        assertEquals(expResult, result);
    }

    @Test
    public void getResponseNameForCodeReturnsCorrectName() {
        String code = "1";
        String expResult = "1";
        String result = info.getResponseNameForCode(code);
        assertEquals(expResult, result);
    }
    
    @Test
    public void responseCodeWitNoNameReturnsCodeItself() {
        List<String> responseCodes = Arrays.asList("1","2");
        List<String> responseNames = Arrays.asList("SUPER");
        info.setResponseNames(responseNames);
        info.setResponseCodes(responseCodes);
        String code = "2";
        String expResult = "2";
        String result = info.getResponseNameForCode(code);
        assertEquals(expResult, result);
    }
    
    @Test
    public void invalidRespCodeReturnsInvalidAsName() {
        String code = "3";
        String expResult = "invalid code";
        String result = info.getResponseNameForCode(code);
        assertEquals(expResult, result);
    }
    
    @Test
    public void getMappingReturnsMappingCorrectly() {
        String cond = "meS";
        String cond2 = "WRONG";
        ResponseMapping expResult = info.getResponseMappings().get(0);
        ResponseMapping result = info.getConditionMapping(cond);
        ResponseMapping result2 = info.getConditionMapping(cond2);
        assertEquals(expResult, result);
        assertEquals(null, result2);
    }
    
    @Test
    public void cantAddMappingThatAlreadyExists() {
        boolean expResult = false;
        boolean result = info.addResponseMapping(new ResponseMapping("meS"));
        assertEquals(expResult, result);
    }

    @Test
    public void settingCorrectResponsesAsListOfListsWorks() {
        String cond = "meS";
        List<List<String>> correctResponses = new ArrayList<>();
        correctResponses.add(Arrays.asList("1","2"));
        info.setCorrectResponses(cond, correctResponses);
        List<List<String>> expResult = Arrays.asList(Arrays.asList("1","2"));
        List<List<String>> result = info.getConditionMapping(cond).getCorrectResponses();
        assertEquals(expResult, result);
    }

    @Test
    public void settingCorrectResponseAsSingleListoWorks() {
        String cond = "meS";
        List<String> correctResponses = Arrays.asList("1","2");
        info.setSimpleCorrectResponses(cond, correctResponses);
        List<List<String>> expResult = Arrays.asList(Arrays.asList("1","2"));
        List<List<String>> result = info.getConditionMapping(cond).getCorrectResponses();
        assertEquals(expResult, result);
    }

    @Test
    public void isCorrectWithNRespReturnsTrueWhenCorrect() {
        String cond = "meS";
        String respCode = "2";
        int nResp = 0;
        boolean expResult = true;
        boolean result = info.isCorrect(cond, respCode, nResp);
        assertEquals(expResult, result);
    }
    
    @Test
    public void isCorrectWithNRespReturnsFalseWhenIncorect() {
        String cond = "meS";
        String respCode = "1";
        int nResp = 0;
        boolean expResult = false;
        boolean result = info.isCorrect(cond, respCode, nResp);
        assertEquals(expResult, result);
    }
    
    @Test
    public void isCorrectWithNRespOtherThanFirstRespWorks() {
        String cond = "meS";
        String respCode = "1";
        List<List<String>> correctResponses = Arrays.asList(Arrays.asList("2"),Arrays.asList("1"));
        info.setCorrectResponses(cond, correctResponses);
        int nResp = 1;
        boolean expResult = true;
        boolean result = info.isCorrect(cond, respCode, nResp);
        assertEquals(expResult, result);
    }
    
}
