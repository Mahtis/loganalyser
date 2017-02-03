package ohha.domain;

import java.util.List;
import java.util.Objects;

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

    public boolean isCorrect(List<String> resps) {
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
