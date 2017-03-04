package ohha.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import ohha.domain.Trial;

/**
 * Writes a List of trials into a file.
 * 
 * @author mikkotiainen
 */
public class LogWriter {
    
    /**
     * Writes a given set of trials to a specified file.
     * @param trials trials to be written.
     * @param filename file where the logfiles should be written to.
     * @throws IOException May throw exception if writing fails.
     */
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
