package ohha.gui.mainviewhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import javax.swing.JOptionPane;
import ohha.domain.SubjectData;
import ohha.domain.Trial;
import ohha.gui.MainView;
import ohha.logic.LogParser;

/**
 * Simple ActionListener class to handle parse-button presses.
 *
 * @author Mikko
 */
public class ParseHandler implements ActionListener {

    private MainView parent;

    /**
     * Initializes a new ParseHandler.
     *
     * @param parent The MainView that the handled button is part of.
     */
    public ParseHandler(MainView parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (parent.getInfo() != null) {
            if (parent.getList().getSelectedValuesList() == null) {
                JOptionPane.showMessageDialog(parent, "Please select a logfile to parse.");
            } else {
                List<Object> files = parent.getList().getSelectedValuesList();
                for (Object o : files) {
                    File f = null;
                    try {
                        f = (File) o;
                    } catch (Exception exc) {
                        //JOptionPane.showMessageDialog(this, "Selected file is not a logfile.");
                    }
                    if (f == null) {
                        JOptionPane.showMessageDialog(parent, "Selected file is not a logfile.");
                    } else if (!f.getName().endsWith(".log")) {
                        JOptionPane.showMessageDialog(parent, "Not a .log-file.");
                    } else {
                        try {
                            LogParser parser = new LogParser(f.getPath(), parent.getInfo(), 3);
                            List<Trial> trials = parser.parseIntoTrials();
                            SubjectData subData = new SubjectData(parent.getInfo(), trials);
                            parent.getData().add(subData);
                            parent.getModel().addElement(subData);
                        } catch (FileNotFoundException ex) {
                            //Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
        } else {
            JOptionPane.showMessageDialog(parent, "No experiment defined.");
        }
    }

}
