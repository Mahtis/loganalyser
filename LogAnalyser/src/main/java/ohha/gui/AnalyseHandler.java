package ohha.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import ohha.domain.Trial;
import ohha.logic.AnalyseData;
import ohha.logic.DrawHistogram;
import ohha.logic.DrawResponseRates;
import ohha.logic.DrawTimeSeries;

/**
 * Simple ActionListener class to handle analyze-button presses. For now
 * it runs all created analyses for the first dataset on the list.
 * @author mikkotiainen
 */
public class AnalyseHandler implements ActionListener {

    private MainView parent;

    /**
     * Initialize a new AnalyseHandler.
     * @param parent The MainView that the handled button is part of.
     */
    public AnalyseHandler(MainView parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AnalyseData analyse = new AnalyseData(parent.getData().get(0));
        int[] bins = analyse.calculateHistogramValues();
        for (int i : bins) {
            System.out.println(i);
        }
        AnalysisWindow win = new AnalysisWindow(500, 500, "Histogram", new DrawHistogram(bins));

        System.out.println("RATES:");
        Map<String, Integer> rates = analyse.calculateResponseRates();
        AnalysisWindow win2 = new AnalysisWindow(500, 500, "Rates", new DrawResponseRates(rates));

        AnalysisWindow win3 = new AnalysisWindow(500, 500, "Time series", new DrawTimeSeries(parent.getData().get(0).getTrials(), 10));
        
        Map<String, List<Trial>> condRts = analyse.reactionTimesForConditions();
        for (String cond : condRts.keySet()) {
            new AnalysisWindow(500, 500, cond, new DrawTimeSeries(condRts.get(cond), 9));
        }
    }

}
