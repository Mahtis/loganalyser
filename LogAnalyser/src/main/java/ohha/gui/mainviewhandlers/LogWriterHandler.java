package ohha.gui.mainviewhandlers;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import ohha.domain.SubjectData;
import ohha.gui.ExtraInputHandler;
import ohha.gui.FileSelectorUtil;
import ohha.gui.MainView;
import ohha.logic.LogWriter;

/**
 * Simple ActionListener class to handle writeLog-button presses.
 *
 * @author mikkotiainen
 */
public class LogWriterHandler implements ExtraInputHandler {

    private MainView parent;

    /**
     * Initializes a LogWriterHandler for the given parent MainView.
     * 
     * @param parent MainView that the handler is part of.
     */
    public LogWriterHandler(MainView parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // create new input window, get filename from there and run writeLogfile.
        Object o = parent.getList().getSelectedValue();
        if (o.getClass() == SubjectData.class) {
            String path = FileSelectorUtil.selectSaveFolder(parent);
            if (path != null) {
                process(path);
            }
        }
    }

    @Override
    public void process(String input) {
        Object o = parent.getList().getSelectedValue();
        if (o.getClass() == SubjectData.class) {
            SubjectData data = (SubjectData) o;
            if (!input.isEmpty()) {
                try {
                    LogWriter.writeLogfile(data.getTrials(), input + "/" + data.toString() + ".txt");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(parent, "Couldn't save the file, maybe the file path was incorrect?");
                }
            } else {
                JOptionPane.showMessageDialog(parent, "Please provide a file path.");
            }
        }
    }

}
