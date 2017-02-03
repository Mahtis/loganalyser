package ohha.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import ohha.domain.Trial;

public class LogWriter {
    
    
    public static void writeLogfile(List<Trial> trials, String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("subj\ttrial\tcond\tcode\tresp\trt\tcorrect");
        for (Trial trial : trials) {
            StringBuilder line = new StringBuilder();
            line.append(trial.getSubject());
            line.append("\t");
            line.append(trial.getTrialNum());
            line.append("\t");
            line.append(trial.getCondition());
            line.append("\t");
            line.append(trial.getResponseCodes());
            line.append("\t");
            line.append(trial.getResponseNames());
            line.append("\t");
            line.append(trial.getReactionTimes());
            line.append("\t");
            line.append(trial.isCorrect());
            lines.add(line.toString());
        }
        Files.write(Paths.get(filename), lines);
    }
}
