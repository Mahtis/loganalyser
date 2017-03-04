package ohha.gui.mainviewhandlers;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
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
        List<Object> files = parent.getList().getSelectedValuesList();
        int notData = 0;
        for (Object o : files) {
            if (o.getClass() != SubjectData.class) {
                notData++;
            }
        }
        if (notData == 0) {
            String path = FileSelectorUtil.selectSaveFolder(parent);
            if (path != null) {
                process(path);
            }
        } else {
            JOptionPane.showMessageDialog(parent, notData + " of the selected files are not processed log data, please select only processed data.");
        }
    }

    @Override
    public void process(String input) {
        List<Object> files = parent.getList().getSelectedValuesList();
        for (Object o : files) {
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

}
