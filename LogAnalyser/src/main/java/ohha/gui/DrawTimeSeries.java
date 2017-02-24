package ohha.gui;

import java.awt.Component;
import java.awt.Graphics;
import java.util.List;
import ohha.domain.Trial;

/**
 * Draws all trials in order and displays mean values for given window.
 * @author mikkotiainen
 */
public class DrawTimeSeries extends Component {
    
    private List<Trial> trials;
    private int meanWindow;

    /**
     * Initialize a new draw with the given list of trials and mean window.
     * @param trials Trials to be displayed.
     * @param meanWindow Window for means, suggested to be something between 10 and half of trials size.
     */
    public DrawTimeSeries(List<Trial> trials, int meanWindow) {
        this.trials = trials;
        this.meanWindow = meanWindow;
    }
    
    @Override
    public void paint(Graphics g) {
        int panelHeight = this.getParent().getHeight();
        int gap = this.getParent().getWidth() / trials.size();
        int[] minMax = findMinMax();
        int min = minMax[0];
        int max = minMax[1];
        double scaler = (double) panelHeight / (max - min);
        int x = -gap;
        int meanCount = 0;
        int meanSum = 0;
        for (Trial trial : trials) {
            x += gap;
            int rt = trial.getReactionTimes().get(0);
            if (meanCount == meanWindow) {
                int mean = meanSum / meanCount;
                int linePos = (int) (panelHeight - (mean * scaler - min * scaler));
                g.drawLine((x - meanCount * gap), linePos, x - 1, linePos);
                meanCount = 0;
                meanSum = 0;
            }
            meanCount++;
            meanSum += rt;
            int y = (int) Math.floor(panelHeight - (rt * scaler - min * scaler));
            System.out.println(y + " scaler: " + scaler + " min: " + min + " max: " + max);
            if (trial.isCorrect()) {
                g.drawString("o", x, y);
            } else {
                g.drawString("x", x, y);
            }
        }
    }
    
    private int[] findMinMax() {
        int max = 0;
        int min = trials.get(0).getReactionTimes().get(0);
        for (Trial trial : trials) {
            int rt = trial.getReactionTimes().get(0);
            if (rt > max) {
                max = rt;
            }
            if (rt < min) {
                min = rt;
            }
        }
        return new int[] {min, max};
    }
    
}
