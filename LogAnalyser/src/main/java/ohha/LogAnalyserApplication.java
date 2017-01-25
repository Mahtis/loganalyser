package ohha;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ohha.domain.ExperimentInfo;
import ohha.domain.Trial;
import ohha.logic.LogParser;

public class LogAnalyserApplication {
    public static void main(String[] args) throws IOException{
        String filename = "/Users/mikkotiainen/Documents/test.log";
        ExperimentInfo info = new ExperimentInfo();
        
        List<String> conds = Arrays.asList("meS", "meV", "deV", "deS");
        info.setConditions(conds);
        
        List<String> responseCodes = Arrays.asList("1","2");
        info.setResponses(responseCodes);
        
        Map<String, String> corrects = new HashMap<>();
        corrects.put("meS", "2");
        corrects.put("deS", "2");
        corrects.put("meV", "1");
        corrects.put("deV", "1");
        info.setCorrectResponses(corrects);
        
        LogParser parser = new LogParser(filename, info, 3);
        List<Trial> trials = parser.ParseIntoTrials();
        
        for (Trial trial : trials) {
            System.out.println(trial);
        }
        
    }
}
