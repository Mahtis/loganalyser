package ohha.domain;

import java.util.Map;

public class Trial {
    private String subject;
    private int trialNum;
    private String condition;
    private int condTime;
    private String response;
    private String responseCode;
    private int respTime;
    private int reactionTime;
    private boolean correct;

    public Trial(String subject, int trialNum, String condition, String response, String responseCode, int reactionTime) {
        this.subject = subject;
        this.trialNum = trialNum;
        this.condition = condition;
        this.response = response;
        this.responseCode = responseCode;
        this.reactionTime = reactionTime;
    }

    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public int getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(int reactionTime) {
        this.reactionTime = reactionTime;
    }

    public int getCondTime() {
        return condTime;
    }

    public void setCondTime(int condTime) {
        this.condTime = condTime;
    }

    public int getRespTime() {
        return respTime;
    }

    public void setRespTime(int respTime) {
        this.respTime = respTime;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "Trial{" + "subject=" + subject + ", trialNum=" + trialNum + ", condition=" + condition + ", response=" + response + ", responseCode=" + responseCode + ", reactionTime=" + reactionTime + ", correct=" + correct + '}';
    }
    
    
    
}
