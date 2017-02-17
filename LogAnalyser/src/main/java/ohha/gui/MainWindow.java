package ohha.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class holds the main frame of the application. Most of the actual logic
 * happens in the MainView-panel.
 * @author mikkotiainen
 */
public class MainWindow {
    private JFrame mainFrame;

    /**
     * Creates a new main window.
     */
    public MainWindow() {
        mainFrame = new JFrame("Log analyser");
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        
        
        
        MainView panel = new MainView();
        mainFrame.setContentPane(panel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    
}
