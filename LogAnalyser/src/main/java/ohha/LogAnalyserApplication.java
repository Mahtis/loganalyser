package ohha;

import java.awt.Dimension;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ohha.domain.ExperimentInfo;
import ohha.domain.Trial;
import ohha.gui.MainWindow;
import ohha.logic.DrawHistogram;
import ohha.logic.ExperimentInfoIO;
import ohha.logic.LogParser;
import ohha.logic.LogWriter;

/**
 * Class to start the application.
 * @author mikkotiainen
 */
public class LogAnalyserApplication {

    /**
     * Starts the application on run.
     * @param args Not used.
     * @throws IOException Throws possible IOException.
     */
    public static void main(String[] args) throws IOException {
        //MainWindow window = new MainWindow();
        
        JFrame win = new JFrame("Histogram");
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 500));
        
        
        int[] bins = new int[] {7,10,20,50,35,28,18,10,8,3};
        DrawHistogram d = new DrawHistogram(bins);

        win.add(panel);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(d);
        d.setPreferredSize(panel.getPreferredSize());
        win.setVisible(true);
        win.pack();
        /*
        String logfile = "src/main/resources/subj1_exp1.log";
        String expFile = "src/main/resources/Experiment2.json";
        String destination = "src/main/resources/test2.txt";
        ExperimentInfoIO expIO = new ExperimentInfoIO();
        ExperimentInfo info = expIO.loadFromJson(expFile);

        LogParser parser = new LogParser(logfile, info, 3);
        List<Trial> trials = parser.parseIntoTrials();
        LogWriter.writeLogfile(trials, destination);
        
        int sum = 0;
        for (Trial trial : trials) {
            if (trial.isCorrect()) {
                sum++;
            }
            System.out.println(trial);
        }
        System.out.println(sum);
        */
    }
}
