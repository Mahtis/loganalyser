package ohha.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class holds the specifications of a single experiment; the conditions,
 * responses and how correct responses are mapped (using ResponseMapping class).
 * 
 * @author Mikko Tiainen
 * 
 */

public class ExperimentInfo {
    private String name;
    private List<String> conditions;
    private List<String> responseCodes;
    private List<String> responseNames;

    private List<ResponseMapping> responseMappings;

    public ExperimentInfo(List<String> conditions, List<String> responseCodes, List<String> responseNames, List<ResponseMapping> responseMappings) {
        this.conditions = conditions;
        this.responseCodes = responseCodes;
        this.responseNames = responseNames;
        this.responseMappings = responseMappings;
    }

    public ExperimentInfo() {
        conditions = new ArrayList<>();
        responseCodes = new ArrayList<>();
        responseNames = new ArrayList<>();
        responseMappings = new ArrayList<>();
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }

    public List<String> getResponseCodes() {
        return responseCodes;
    }

    public void setResponseCodes(List<String> responseCodes) {
        this.responseCodes = responseCodes;
    }

    public List<String> getResponseNames() {
        return responseNames;
    }

    public void setResponseNames(List<String> responseNames) {
        this.responseNames = responseNames;
    }
    
    /**
     * Returns the name of the response based on its code.
     * @param code code of the response option.
     * @return name of the response with the given code.
     */
    public String getResponseNameForCode(String code) {
        return responseNames.get(responseCodes.indexOf(code));
    }

    /**
     * Returns the response mapping of a given condition.
     * 
     * @param condition name of the condition for which the ResponseMapping
     * is wanted for.
     * @return ResponseMapping object for the given condition or null if one
     * is not found.
     */
    public ResponseMapping getConditionMapping(String condition) {
        if (conditions.contains(condition)) {
            ResponseMapping mapping = responseMappings.get(conditions.indexOf(condition));
            if (mapping.getCondition().equals(condition)) {
                return mapping;
            } else {
                for (ResponseMapping m : responseMappings) {
                    if (m.getCondition().equals(condition)) {
                        return m;
                    }
                }
            }
        }
        return null;
    }

    public List<ResponseMapping> getResponseMappings() {
        return responseMappings;
    }

    public void setResponseMappings(List<ResponseMapping> responseMappings) {
        this.responseMappings = responseMappings;
    }

    public boolean addResponseMapping(ResponseMapping mapping) {
        if (responseMappings.contains(mapping)) {
            return false;
        }
        responseMappings.add(mapping);
        return true;
    }

    /**
     * Get the number of responses set for a given condition.
     * @param cond condition to get the number of responses for.
     * @return number of responses wanted.
     */
    public int getNumOfResponsesForCond(String cond) {
        return getConditionMapping(cond).getnOfResponses();
    }

    public void setCorrectResponses(String cond, List<List<String>> correctResponses) {
        getConditionMapping(cond).setCorrectResponses(correctResponses);
    }

    /**
     * If a condition has only one response, the correct responses can be given
     * as a single list.
     * @param cond condition for the responses.
     * @param correctResponses correct responses for the condition.
     */
    public void setSimpleCorrectResponses(String cond, List<String> correctResponses) {
        ResponseMapping mapping = getConditionMapping(cond);
        if (!responseMappings.contains(mapping)) {
            addResponseMapping(mapping);
        }
        mapping.setCorrectResponses(Arrays.asList(correctResponses));
    }

    /**
     * Check whether a given response to a given condition is correct.
     * 
     * This method utilizes the ResponseMapping-class to check for the correct
     * response. Note that this overloaded version of the method only checks
     * the correctness of the nResp response.
     * 
     * @param cond condition of the response.
     * @param respCode code of the response.
     * @param nResp number of response which correctness is checked.
     * @return true if correct.
     */
    public boolean isCorrect(String cond, String respCode, int nResp) {
        ResponseMapping mapping = getConditionMapping(cond);
        return mapping.isCorrect(nResp, respCode);
    }

    public boolean isCorrect(String cond, List<String> respCodes) {
        return getConditionMapping(cond).isCorrect(respCodes);
    }

    @Override
    public String toString() {
        return "ExperimentInfo{" + "conditions=" + conditions + ", responseCodes=" + responseCodes + ", responseNames=" + responseNames + ", responseMappings=" + responseMappings + '}';
    }

}
