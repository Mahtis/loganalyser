package ohha;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ohha.domain.ExperimentInfo;
import ohha.domain.ResponseMapping;
import ohha.domain.Trial;
import ohha.logic.LogParser;

public class LogAnalyserApplication {

    public static void main(String[] args) throws IOException {
        String filename = "/Users/mikkotiainen/Documents/test.log";
        ExperimentInfo info = new ExperimentInfo();

        List<String> conds = Arrays.asList("meS", "meV", "deV", "deS");
        info.setConditions(conds);

        List<String> responseCodes = Arrays.asList("1", "2");
        info.setResponseNames(responseCodes);
        info.setResponseCodes(responseCodes);
        info.addResponseMapping(new ResponseMapping("meS"));
        info.addResponseMapping(new ResponseMapping("deS"));
        info.addResponseMapping(new ResponseMapping("meV"));
        info.addResponseMapping(new ResponseMapping("deV"));
        info.setSimpleCorrectResponses("meS", Arrays.asList("2"));
        info.setSimpleCorrectResponses("deS", Arrays.asList("2"));
        info.setSimpleCorrectResponses("meV", Arrays.asList("1"));
        info.setSimpleCorrectResponses("deV", Arrays.asList("1"));

        LogParser parser = new LogParser(filename, info, 3);
        List<Trial> trials = parser.ParseIntoTrials();

        int sum = 0;
        for (Trial trial : trials) {
            if (trial.isCorrect()) {
                sum++;
            }
            System.out.println(trial);
        }
        System.out.println(sum);

    }
}
