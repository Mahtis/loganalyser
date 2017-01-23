package ohha;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import ohha.domain.ExperimentInfo;
import ohha.logic.LogParser;

public class LogAnalyserApplication {
    public static void main(String[] args) throws IOException{
        String filename = "/Users/mikkotiainen/Documents/test.log";
        ExperimentInfo info = new ExperimentInfo();
        List<String> conds = Arrays.asList("meS", "meV", "deV", "deS");
        info.setConditions(conds);
        LogParser parser = new LogParser(filename, info);
        parser.ParseIntoTrials();
    }
}
