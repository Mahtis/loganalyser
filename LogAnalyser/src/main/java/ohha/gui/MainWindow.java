package ohha.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow {
    private JFrame mainFrame;

    public MainWindow() {
        mainFrame = new JFrame("Log analyser");
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        
        
        
        FileOpenHandler panel = new FileOpenHandler();
        mainFrame.setContentPane(panel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    
    
}
