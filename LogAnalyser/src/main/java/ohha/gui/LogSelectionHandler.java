package ohha.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class LogSelectionHandler implements ActionListener{
    
    private MainView parent;

    public LogSelectionHandler(MainView parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File[] logFiles = multiFileSelection(parent, new LogFilter());
        for (File file : logFiles) {
            if (!file.getName().endsWith(".log")) {
                JOptionPane.showMessageDialog(parent, "Not a .log-file.");
            } else {
                parent.getFiles().add(file);
                parent.getModel().addElement(file);
            }
        }
    }
    
    public static File[] multiFileSelection(Component parent, FileFilter filter) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.setMultiSelectionEnabled(true);
        int returnVal = fileChooser.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFiles();
        }
        return null;
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