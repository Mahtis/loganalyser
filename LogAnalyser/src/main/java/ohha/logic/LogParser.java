package ohha.logic;

import java.util.List;
import ohha.domain.ExperimentInfo;
import ohha.domain.Trial;

//Parses logfile into lists of trials.

public class LogParser {
    private LogReader reader;
    private List<String> logLines;
    private ExperimentInfo info;
    
    public LogParser(String filename, ExperimentInfo info) {
        reader = new LogReader();
        logLines = reader.readLog(filename);
        this.info = info;
    }
    
    public List<Trial> ParseIntoTrials() {
        int s = 0;
        for (String line : logLines) {    
            if (line.contains(info.getConditions().get(0))) {
                System.out.println("GOTEM   " + s);
                s++;
            }
        }
        return null;
    }
    
}
