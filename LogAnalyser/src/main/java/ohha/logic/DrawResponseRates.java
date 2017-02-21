package ohha.logic;

import java.awt.Component;
import java.awt.Graphics;
import java.util.Map;

public class DrawResponseRates extends Component {
    private Map<String, Integer> rates;

    public DrawResponseRates(Map<String, Integer> rates) {
        this.rates = rates;
    }
    
    @Override
    public void paint(Graphics g) {
        int panelHeight = this.getParent().getHeight();
        int barWidth = this.getParent().getWidth() / (rates.size() * 2);
        int gap = barWidth / 2;
        int scaler = panelHeight / 100;
        int y = 0;
        int x = - barWidth + gap;
        
        drawVerticalAxis(g, panelHeight, scaler);
        
        for (String condition : rates.keySet()) {
            x += barWidth + gap;
            int barHeight = rates.get(condition) * scaler;
            y = panelHeight - barHeight;
            g.drawRect(x, y, barWidth, barHeight);
            g.drawString(condition, x, panelHeight - 10);
        }
    }
    
    private void drawVerticalAxis(Graphics g, int panelHeight, int scaler) {
        int numPos = panelHeight;
        for (int i = 0; i <= 100; i += 10) {
            g.drawString(String.valueOf(i) + "-", 0, numPos);
            numPos -= scaler * 10;
        }
    }
}
