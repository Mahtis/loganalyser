package ohha.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import ohha.domain.ExperimentInfo;
import ohha.domain.Trial;

//Parses logfile into lists of trials.
public class LogParser {

    private LogReader reader;
    private List<String> logLines;
    private ExperimentInfo info;
    private List<Trial> trials;
    private int codePos;
    private int timePos;

    public LogParser(String filename, ExperimentInfo info, int codePos) {
        reader = new LogReader();
        logLines = reader.readLog(filename);
        this.info = info;
        this.codePos = codePos;
        this.timePos = codePos + 1;
        trials = new ArrayList<>();
    }

    public List<Trial> ParseIntoTrials() {
        int trialN = 1;
        List<String> conds = info.getConditions();
        Set<String> responseCodes = info.getResponseCodes();
        boolean searchingResponse = false;
        // go through each line
        for (int i = 0; i < logLines.size(); i++) {
            String line = logLines.get(i);
            String[] words = line.split("\t");
            // check if the line contains a valid condition code
            if (words.length > 3 && conds.contains(words[codePos])) {
                String cond = words[codePos];
                Trial trial = new Trial(words[0], trialN, cond, "none", "0", 0);
                trial.setCondTime(Integer.parseInt(words[timePos]));
                trials.add(trial);
                trialN++;
                searchingResponse = true;
            } // if looking for a response, then check line for valid response code
            else if (searchingResponse && words.length > 3 && responseCodes.contains(words[codePos])) {
                Trial trial = trials.get(trials.size() - 1);
                setResponseInfo(trial, words);
                searchingResponse = false;
            }
        }
        return trials;
    }

    private void setResponseInfo(Trial trial, String[] words) {
        trial.setResponseCode(words[codePos]);
        trial.setResponse(info.getResponses().get(words[codePos]));
        trial.setRespTime(Integer.parseInt(words[timePos]));
        trial.setReactionTime((trial.getCondTime() - trial.getRespTime()) / 10);
        trial.setCorrect(info.isCorrect(trial.getCondition(), trial.getResponseCode()));
    }
}
