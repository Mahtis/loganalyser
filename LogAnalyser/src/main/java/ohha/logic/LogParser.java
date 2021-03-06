package ohha.logic;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import ohha.domain.ExperimentInfo;
import ohha.domain.Trial;

/**
 * Parses Presentation logfiles into lists of trials according to given
 * ExperimentInfo.
 * 
 * The parser is instantiated with an ExperimentInfo object, which is used for
 * searching for the conditions and response codes from the logfile. The codePos
 * refers to the column where the codes are on. It should be 3.
 * 
 * @author Mikko Tiainen
 */
public class LogParser {

    private List<String> logLines;
    private ExperimentInfo info;
    private List<Trial> trials;
    private int codePos;
    private int timePos;

    /**
     * Create a new LogParser for a given Experiment and file.
     * @param filename Name of the file to be parsed.
     * @param info ExperimentInfo to be used for parsing.
     * @param codePos Position of the codes in the logfile.
     * @throws FileNotFoundException If the file cannot be read.
     */
    public LogParser(String filename, ExperimentInfo info, int codePos) throws FileNotFoundException {
        if (!filename.contains(".log")) {
            throw new FileNotFoundException("The defined file doesn't appear to be a proper Presentation logfile (doesn't end in .log)");
        }
        logLines = FileReader.readFile(filename);
        this.info = info;
        this.codePos = codePos;
        this.timePos = codePos + 1;
        trials = new ArrayList<>();
    }

    /**
     * Reads a Presentation logfile and parses it into a Trial list.
     * The method relies on the fact that the given file is a standard
     * Presentation file. The method is currently quite long and should be
     * refactored to more manageable size.
     * @return List of Trials parsed from the logfile.
     */
    public List<Trial> parseIntoTrials() {
        int trialN = 1;
        List<String> conds = info.getConditions();
        List<String> responseCodes = info.getResponseCodes();
        boolean searchingResponse = false;
        int currResp = 0;
        int wantedResponses = 0;
        // go through each line
        for (int i = 0; i < logLines.size(); i++) {
            String line = logLines.get(i);
            String[] words = line.split("\t");
            // check if the line contains a valid condition code
            if (words.length > 3 && conds.contains(words[codePos])) {
                String cond = words[codePos];
                wantedResponses = info.getNumOfResponsesForCond(cond);
                currResp = 0;
                Trial trial = new Trial(words[0], trialN, cond);
                trial.setCondTime(Integer.parseInt(words[timePos]));
                trial = createNullResponseData(trial, wantedResponses);
                trials.add(trial);
                trialN++;
                searchingResponse = true;
                // if looking for a response, then check line for valid response code∫
            } else if (searchingResponse && 
                    words.length > 3 && 
                    responseCodes.contains(words[codePos])) {
                Trial trial = trials.get(trials.size() - 1);
                setResponseInfo(trial, words, currResp);
                currResp++;
                if (currResp == wantedResponses) {
                    currResp = 0;
                    searchingResponse = false;
                    trial.setCorrect(info.isCorrect(trial.getCondition(), trial.getResponseCodes()));
                }
            }
        }
        return trials;
    }
    
    private void setResponseInfo(Trial trial, String[] words, int i) {
        String code = words[codePos];
        trial.setSingleResponseCode(i, code);
        trial.setSingleResponseName(i, info.getResponseNameForCode(code));
        int respTime = Integer.parseInt(words[timePos]);
        trial.setSingleRespTime(i, respTime);
        trial.setSingleReactionTime(i, (respTime - trial.getCondTime()) / 10);
    }
    
    private Trial createNullResponseData(Trial trial, int wantedResponses) {
        List<String> resps = new ArrayList<>();
        List<String> codes = new ArrayList<>();
        List<Integer> rts = new ArrayList<>();
        for (int i = 0; i < wantedResponses; i++) {
            resps.add("none");
            codes.add("0");
            rts.add(0);
        }
        trial.setResponseNames(resps);
        trial.setResponseCodes(codes);
        trial.setReactionTimes(rts);
        trial.setRespTimes(rts);
        return trial;
    }
}
