package ohha.logic;

import java.util.Arrays;
import java.util.List;
import ohha.domain.ExperimentInfo;
import ohha.domain.Trial;

//Parses logfile into lists of trials.
public class LogParser {

    private LogReader reader;
    private List<String> logLines;
    private ExperimentInfo info;
    private List<Trial> trials;
    private int codePos;

    public LogParser(String filename, ExperimentInfo info, int codePos) {
        reader = new LogReader();
        logLines = reader.readLog(filename);
        this.info = info;
        this.codePos = codePos;
    }

    public List<Trial> ParseIntoTrials() {
        int trialN = 1;
        List<String> conds = info.getConditions();
        List<String> responseCodes = info.getResponseCodes();
        boolean searchingResponse = false;
        // go through each line
        for (int i = 0; i < logLines.size(); i++) {
            String line = logLines.get(i);
            String[] words = line.split("\t");
            // check if the line contains a valid condition code
            if (conds.contains(words[codePos])) {
                String cond = words[codePos];
                Trial trial = new Trial(words[0], trialN, cond, "none", 0, 0);
                trials.add(trial);
                trialN++;
                searchingResponse = true;
            }
            
            else if (searchingResponse && responseCodes.contains(words[codePos])) {
                Trial trial = trials.get(trials.size()-1);
                trial.setResponseCode(Integer.parseInt(words[codePos]));
                searchingResponse = false;
            }
        }
        return trials;
    }
}
