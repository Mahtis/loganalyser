package ohha.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author mikkotiainen
 */
public class AnalysisWindow extends JFrame {
    
    private int width;
    private int height;
    private Component analysis;

    public AnalysisWindow(int width, int height, String title, Component analysis) throws HeadlessException {
        super(title);
        this.width = width;
        this.height = height;
        this.analysis = analysis;
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        
        this.add(panel);
        panel.add(analysis);
        analysis.setPreferredSize(panel.getPreferredSize());
        this.setVisible(true);
        this.pack();
    }
    
    
    
}
