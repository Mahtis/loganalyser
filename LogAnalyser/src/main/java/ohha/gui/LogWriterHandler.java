package ohha.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ohha.domain.SubjectData;
import ohha.logic.LogWriter;

/**
 * Simple ActionListener class to handle writeLog-button presses.
 *
 * @author mikkotiainen
 */
class LogWriterHandler implements ExtraInputHandler {

    private MainView parent;
    private String input;

    /**
     *
     * @param parent
     */
    public LogWriterHandler(MainView parent) {
        this.parent = parent;
        this.input = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // create new input window, get filename from there and run writeLogfile.
        Object o = parent.getList().getSelectedValue();
        if (o.getClass() == SubjectData.class) {
            SubjectData data = (SubjectData) o;
            new InputWindow("Enter folder where to save new log", "Set file destination", this);
        }
    }
    
    public void save() {
        Object o = parent.getList().getSelectedValue();
        if (o.getClass() == SubjectData.class) {
            SubjectData data = (SubjectData) o;
            if (!input.isEmpty()) {
                try {
                    System.out.println("got it");
                    LogWriter.writeLogfile(data.getTrials(), input + "/" + data.toString() + ".txt");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(parent, "Couldn't save the file, maybe the file path was incorrect?");
                }
            } else {
                JOptionPane.showMessageDialog(parent, "Please provide a file path.");
            }
        }
    }

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public void setInput(String input) {
        this.input = input;
    }

}
