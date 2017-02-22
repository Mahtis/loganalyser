package ohha.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class for creating a generic window to display analysis.
 * @author mikkotiainen
 */
public class AnalysisWindow extends JFrame {
    
    private int width;
    private int height;
    private Component analysis;

    /**
     * Initializes the window with given size, title and the analysis.
     * @param width Width of the window.
     * @param height Height of the window.
     * @param title Title of the window.
     * @param analysis The analysis component to be drawn on the window.
     * @throws HeadlessException Exception.
     */
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
