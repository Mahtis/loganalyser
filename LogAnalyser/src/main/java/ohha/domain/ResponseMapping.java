package ohha.domain;

import java.util.List;
import java.util.Objects;

/**
 * This class maps the correct responses of a condition.
 *
 * @author Mikko Tiainen
 *
 * A condition can have multiple separate responses and each response
 * "iteration", so to speak, can have multiple correct answers.
 *
 */
public class ResponseMapping {

    private String condition;
    private List<List<String>> correctResponses;
    private int nOfResponses;

    public ResponseMapping(String condition) {
        this.condition = condition;
        nOfResponses = 0;
    }

    public ResponseMapping(String condition, List<List<String>> correctResponses, int nOfResponses) {
        this.condition = condition;
        this.correctResponses = correctResponses;
        this.nOfResponses = nOfResponses;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isCorrect(int nResp, String resp) {
        return correctResponses.get(nResp).contains(resp);
    }

    /**
     * Checks whether the condition was answered correctly.
     *
     * Note that all responses have to be answered correctly for the method to
     * return true.
     *
     * @param resps list of responses.
     * @return if all responses are correct.
     */
    public boolean isCorrect(List<String> resps) {
        if (resps.size() != correctResponses.size()) {
            return false;
        }
        for (int i = 0; i < correctResponses.size(); i++) {
            if (!correctResponses.get(i).contains(resps.get(i))) {
                return false;
            }
        }
        return true;
    }

    public List<List<String>> getCorrectResponses() {
        return correctResponses;
    }

    public void setCorrectResponses(List<List<String>> correctResponses) {
        this.correctResponses = correctResponses;
        this.nOfResponses = correctResponses.size();
    }

    public int getnOfResponses() {
        return nOfResponses;
    }

    public void setnOfResponses(int nOfResponses) {
        this.nOfResponses = nOfResponses;
    }

    /**
     * Equality of mappings is based solely on the condition.
     *
     * So if two mappings have the same condition, they are considered to be the
     * same mapping. In essence, one condition should only have mapping.
     *
     * @param obj ResponseMapping object that the current is compared to.
     * @return true if both specify the same condition.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResponseMapping other = (ResponseMapping) obj;
        if (!Objects.equals(this.condition, other.condition)) {
            return false;
        }
        return true;
    }

}
