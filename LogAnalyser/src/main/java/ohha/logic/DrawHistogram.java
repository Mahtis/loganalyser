
package ohha.logic;

import java.awt.Component;
import java.awt.Graphics;


/**
 * Draws a histogram from the given values.
 * @author mikkotiainen
 */
public class DrawHistogram extends Component{
    
    private int[] bins;
    private int gap;

    /**
     * Initializes the histogram with a default width of 5.
     * @param bins The bins used for drawing the histogram.
     */
    public DrawHistogram(int[] bins) {
        this.bins = bins;
        gap = 5;
        
    }
    
    @Override
    public void paint(Graphics g) {
        int panelHeight = this.getParent().getHeight();
        int barWidth = this.getParent().getWidth() / bins.length - gap * 2;
        int barHeight = 0;
        int max = findMax();
        int scaler = panelHeight / max;
        
        drawVerticalAxis(g, panelHeight, max, scaler);
        
        int y = 0;
        int x = - barWidth + gap * 3;
        for (int i = 0; i < bins.length; i++) {
            x += barWidth + gap;
            barHeight = bins[i] * scaler;
            y = panelHeight - barHeight;
            g.drawRect(x, y, barWidth, barHeight);
        }
    }
    
    private int findMax() {
        int max = 0;
        for (int i : bins) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
    
    private void drawVerticalAxis(Graphics g, int panelHeight, int max, int scaler) {
        int numPos = panelHeight;
        for (int i = 0; i <= max; i += 5) {
            g.drawString(String.valueOf(i) + "-", 0, numPos);
            numPos -= scaler * 5;
        }
    }
    
}
