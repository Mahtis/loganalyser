package ohha.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import ohha.domain.SubjectData;
import ohha.domain.Trial;
import ohha.logic.AnalyseData;

/**
 * Simple ActionListener class to handle analyze-button presses. For now it runs
 * all created analyses for the first dataset on the list.
 *
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
        List<Object> dataList = parent.getList().getSelectedValuesList();
        for (Object o : dataList) {
            try {
                SubjectData data = (SubjectData) o;
                AnalyseData analyse = new AnalyseData((SubjectData) data);
                int[] bins = analyse.calculateHistogramValues();
                new AnalysisWindow(500, 500, "Histogram", new DrawHistogram(bins));

                Map<String, Integer> rates = analyse.calculateResponseRates();
                new AnalysisWindow(500, 500, "Rates", new DrawResponseRates(rates));

                new AnalysisWindow(500, 500, "Time series", new DrawTimeSeries(data.getTrials(), 9));

                Map<String, List<Trial>> condRts = analyse.reactionTimesForConditions();
                for (String cond : condRts.keySet()) {
                    new AnalysisWindow(500, 500, cond, new DrawTimeSeries(condRts.get(cond), 9));
                }
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(parent, "One of the files was not a proper parsed logfile.");
            }
        }
    }

}
