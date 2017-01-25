package ohha.domain;

import java.util.Map;

public class Trial {
    private String subject;
    private int trialNum;
    private String condition;
    private String response;
    private int responseCode;
    private int reactionTime;

    public Trial(String subject, int trialNum, String condition, String response, int responseCode, int reactionTime) {
        this.subject = subject;
        this.trialNum = trialNum;
        this.condition = condition;
        this.response = response;
        this.responseCode = responseCode;
        this.reactionTime = reactionTime;
    }
    
    public int getTrialNum() {
        return trialNum;
    }

    public void setTrialNum(int trialNum) {
        this.trialNum = trialNum;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(int reactionTime) {
        this.reactionTime = reactionTime;
    }
    
    
    
}
