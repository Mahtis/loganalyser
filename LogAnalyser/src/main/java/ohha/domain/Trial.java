package ohha.domain;

import java.util.List;

/**
 * This class holds the information of a single trial.
 * @author Mikko Tiainen
 * 
 * The information kept in the class include the subject
 * who is responding, number of the trial, condition, responses and the 
 * response/reaction times. This is basically a storage class, with no logic
 * of its own.
 * 
 */

public class Trial {
    private String subject;
    private int trialNum;
    private String condition;
    private int condTime;
    private List<String> responseNames;
    private List<String> responseCodes;
    private List<Integer> respTimes;
    private List<Integer> reactionTimes;
    private boolean correct;

    /**
     * Create a new Trial with the specified parameters.
     * @param subject The subject performing the trial.
     * @param trialNum Number of the trial in the experiment.
     * @param condition Condition for the current trial.
     * @param responseNames Spelled out names of the responses given.
     * @param responseCodes Response codes for the responses in the trial.
     * @param reactionTimes Reaction times for the responses.
     */
    public Trial(String subject, int trialNum, String condition, List<String> responseNames, List<String> responseCodes, List<Integer> reactionTimes) {
        this.subject = subject;
        this.trialNum = trialNum;
        this.condition = condition;
        this.responseNames = responseNames;
        this.responseCodes = responseCodes;
        this.reactionTimes = reactionTimes;
    }

    /**
     * Create a trial with just the subject, trial number and condition. Other 
     * values are set later.
     * @param subject The subject performing the trial.
     * @param trialNum Number of the trial in the experiment.
     * @param condition Condition for the current trial.
     */
    public Trial(String subject, int trialNum, String condition) {
        this.subject = subject;
        this.trialNum = trialNum;
        this.condition = condition;
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

    public List<String> getResponseNames() {
        return responseNames;
    }

    public void setResponseNames(List<String> responseNames) {
        this.responseNames = responseNames;
    }
    
    /**
     * Sets the name of a single response. Skips the need to have to create
     * a list to set a single response name.
     * @param i Number of the response.
     * @param responseName Name of the response.
     */
    public void setSingleResponseName(int i, String responseName) {
        if (i < responseNames.size() && i >= 0) {
            responseNames.set(i, responseName);
        }
    }

    public List<String> getResponseCodes() {
        return responseCodes;
    }

    public void setResponseCodes(List<String> responseCodes) {
        this.responseCodes = responseCodes;
    }
    
    /**
     * Set the code for a single response. Skips the need to create a list for
     * with all response codes to just alter one.
     * @param i Number of the response.
     * @param response Response code.
     */
    public void setSingleResponseCode(int i, String response) {
        if (i < responseCodes.size() && i >= 0) {
            responseCodes.set(i, response);
        }
    }

    public List<Integer> getReactionTimes() {
        return reactionTimes;
    }

    public void setReactionTimes(List<Integer> reactionTimes) {
        this.reactionTimes = reactionTimes;
    }
    
    /**
     * Set the reaction time of a single response. Skips the need to create a 
     * list to alter just one reaction time.
     * @param i Number of the response.
     * @param rt Reaction time to set.
     */
    public void setSingleReactionTime(int i, int rt) {
        if (i < reactionTimes.size() && i >= 0) {
            reactionTimes.set(i, rt);
        }
    }

    public int getCondTime() {
        return condTime;
    }

    public void setCondTime(int condTime) {
        this.condTime = condTime;
    }

    public List<Integer> getRespTimes() {
        return respTimes;
    }

    public void setRespTimes(List<Integer> respTimes) {
        this.respTimes = respTimes;
    }
    
    /**
     * Set the timing of a single response. Skips the need to create a new list
     * just to alter the timing of one response.
     * @param i Number of the response.
     * @param respTime The time of response.
     */
    public void setSingleRespTime(int i, int respTime) {
        if (i < respTimes.size() && i >= 0) {
            respTimes.set(i, respTime);
        }
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "Trial{" + "subject=" + subject + ", trialNum=" + trialNum + ", condition=" + condition + ", responses=" + responseNames + ", responseCodes=" + responseCodes + ", reactionTimes=" + reactionTimes + ", correct=" + correct + '}';
    }
    
    
    
}
