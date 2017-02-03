package ohha.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExperimentInfo {

    private List<String> conditions;
    private List<String> responseCodes;
    private List<String> responseNames;
    // this maps condition to correct button code
    //private Map<String, List<String>> correctResponses;
    // this maps the button codes to actual definitions
    //private Map<String, String> responseNames;

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
    
    public String getResponseNameForCode(String code) {
        return responseNames.get(responseCodes.indexOf(code));
    }

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
        return new ResponseMapping(condition);
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

    public int getNumOfResponsesForCond(String cond) {
        return getConditionMapping(cond).getnOfResponses();
    }

    public void setCorrectResponses(String cond, List<List<String>> correctResponses) {
        getConditionMapping(cond).setCorrectResponses(correctResponses);
    }

    public void setSimpleCorrectResponses(String cond, List<String> correctResponses) {
        ResponseMapping mapping = getConditionMapping(cond);
        if (!responseMappings.contains(mapping)) {
            addResponseMapping(mapping);
        }
        mapping.setCorrectResponses(Arrays.asList(correctResponses));
    }

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
