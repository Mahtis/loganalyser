package ohha.domain;

import java.util.List;
import java.util.Map;

public class ExperimentInfo {
    private List<String> conditions;
    private List<String> responseCodes;
    private Map<String, String> correctResponses;

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

    public Map<String, String> getCorrectResponses() {
        return correctResponses;
    }

    public void setCorrectResponses(Map<String, String> correctResponses) {
        this.correctResponses = correctResponses;
    }
    
    
    
}
