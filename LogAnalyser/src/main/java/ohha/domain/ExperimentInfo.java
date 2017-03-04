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

    /**
     * Initialize a new ExperimentInfo with given values.
     *
     * @param name Name of the Experiment.
     * @param conditions List of conditions in the Experiment.
     * @param responseCodes List of response codes.
     * @param responseNames List of response names.
     * @param responseMappings List of Response mappings in the Experiment.
     */
    public ExperimentInfo(String name, List<String> conditions, List<String> responseCodes, List<String> responseNames, List<ResponseMapping> responseMappings) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = "empty";
        }
        if (conditions != null) {
            this.conditions = conditions;
        } else {
            this.conditions = new ArrayList<>();
        }
        if (responseCodes != null) {
            this.responseCodes = responseCodes;
        } else {
            this.responseCodes = new ArrayList<>();
        }
        if (responseNames != null) {
            this.responseNames = responseNames;
        } else {
            this.responseNames = new ArrayList<>();
        }
        if (responseMappings != null) {
            this.responseMappings = responseMappings;
        } else {
            this.responseMappings = new ArrayList<>();
        }
    }

    /**
     * Initialize a new ExperimentInfo with no values.
     */
    public ExperimentInfo() {
        name = "empty";
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
     * Returns the name of the response based on its code. If the name for the
     * code does not exist, returns the code itself. If the code is wrong,
     * returns "invalid code".
     *
     * @param code code of the response option.
     * @return name of the response with the given code.
     */
    public String getResponseNameForCode(String code) {
        if (responseCodes.contains(code)) {
            if (responseCodes.indexOf(code) < responseNames.size()) {
                return responseNames.get(responseCodes.indexOf(code));
            }
            return code;
        }
        return "invalid code";
    }

    /**
     * Returns the response mapping of a given condition.
     *
     * @param condition name of the condition for which the ResponseMapping is
     * wanted for.
     * @return ResponseMapping object for the given condition or null if one is
     * not found.
     */
    public ResponseMapping getConditionMapping(String condition) {
        if (conditions.contains(condition)) {
            for (ResponseMapping m : responseMappings) {
                if (m.getCondition().equals(condition)) {
                    return m;
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

    /**
     * Add a new ResponseMapping object to the Experiment. Checks if a similar
     * mapping has already been declared.
     *
     * @param mapping Mapping to be added.
     * @return true if the mapping was successfully added.
     */
    public boolean addResponseMapping(ResponseMapping mapping) {
        if (mapping == null || responseMappings.contains(mapping)) {
            return false;
        }
        responseMappings.add(mapping);
        return true;
    }

    /**
     * Get the number of responses set for a given condition.
     *
     * @param cond condition to get the number of responses for.
     * @return number of responses wanted.
     */
    public int getNumOfResponsesForCond(String cond) {
        return getConditionMapping(cond).getnOfResponses();
    }

    /**
     * Set correct responses for a given condition. This method skips the need
     * to manually retrieve the specific ResponseMapping before setting the
     * correct responses.
     *
     * @param cond Condition that the correct responses are for.
     * @param correctResponses List of lists of correct responses.
     */
    public void setCorrectResponses(String cond, List<List<String>> correctResponses) {
        getConditionMapping(cond).setCorrectResponses(correctResponses);
    }

    /**
     * If a condition has only one response, the correct responses can be given
     * as a single list.
     *
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
     * response. Note that this overloaded version of the method only checks the
     * correctness of the nResp response.
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

    /**
     * Checks if a response is correct. Skips have to call for the specific
     * ResponseMapping. Checks the correctness of all given responses.
     *
     * @param cond Condition.
     * @param respCodes Response codes for the condition.
     * @return True if all responses are correct, otherwise false.
     */
    public boolean isCorrect(String cond, List<String> respCodes) {
        return getConditionMapping(cond).isCorrect(respCodes);
    }

    @Override
    public String toString() {
        return name + "\n" + conditions + "\n" + responseCodes + "\n" + responseNames + "\n" + responseMappings;
    }
}
