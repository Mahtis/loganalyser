package ohha.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 * Simple ActionListener class to handle logselection-button presses.
 * @author Mikko
 */
public class LogSelectionHandler implements ActionListener {
    
    private MainView parent;

    /**
     * Initialize a new LogSelectionHandler.
     * @param parent The MainView that the handled button is part of.
     */
    public LogSelectionHandler(MainView parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File[] logFiles = MainView.multiFileSelection(parent, new LogFilter());
        for (File file : logFiles) {
            if (!file.getName().endsWith(".log")) {
                JOptionPane.showMessageDialog(parent, "Not a .log-file.");
            } else {
                parent.getFiles().add(file);
                parent.getModel().addElement(file);
            }
        }
    }

    
    private class LogFilter extends FileFilter {

        @Override
        public boolean accept(File pathname) {
            return pathname.getName().endsWith(".log") || pathname.isDirectory();
        }

        @Override
        public String getDescription() {
            return "Presentation logfile .log";
        }
    }
    
}
