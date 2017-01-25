package ohha.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExperimentInfo {
    private List<String> conditions;
    private List<String> responseCodes;
    // this maps condition to correct button code
    private Map<String, String> correctResponses;
    // this maps the button codes to actual definitions
    private Map<String, String> responses;

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }

    public Set<String> getResponseCodes() {
        return responses.keySet();
    }

    public Map<String, String> getResponses() {
        return responses;
    }

    public void setResponses(Map<String, String> responses) {
        this.responses = responses;
    }
    
    //in case the user doesn't want to specify responses, use the codes as both
    public void setResponses(List<String> responseCodes) {
        this.responses = new HashMap<>();
        for (String code : responseCodes) {
            responses.put(code, code);
        }
    }
    
    public Map<String, String> getCorrectResponses() {
        return correctResponses;
    }

    public void setCorrectResponses(Map<String, String> correctResponses) {
        this.correctResponses = correctResponses;
    }
    
    public boolean isCorrect(String cond, String respCode) {
        return correctResponses.get(cond).equals(respCode);
    }
    
}
