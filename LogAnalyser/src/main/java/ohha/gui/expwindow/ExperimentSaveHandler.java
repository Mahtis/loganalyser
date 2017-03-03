package ohha.gui.expwindow;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import ohha.gui.ExperimentWindow;
import ohha.gui.ExtraInputHandler;
import ohha.gui.FileSelectorUtil;
import ohha.logic.ExperimentInfoIO;

/**
 * An ActionListener class to allow user to save an ExperimentInfo as a json-file.
 * The class utilizes the JFileChooser-class for selecting the save directory.
 *
 * @author mikkotiainen
 */
public class ExperimentSaveHandler implements ExtraInputHandler {

    private ExperimentWindow parent;

    /**
     * Initializes an ExperimentSaveHandler with the given ExperimentWindow
     * parent.
     *
     * @param parent ExperimentWindow whose ExperimentInfo is saved.
     */
    public ExperimentSaveHandler(ExperimentWindow parent) {
        this.parent = parent;
    }

    @Override
    public void process(String input) {
        if (new File(input).isDirectory()) {
            try {
                ExperimentInfoIO.saveToJson(parent.getInfo(), input + "/", parent.getInfo().getName());
                JOptionPane.showMessageDialog(parent, "File saved at: " + input + "/" + parent.getInfo().getName() + ".json");
                parent.getLoadText().setText("saved");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parent, "Couldn't save the file at: " + input + "/" + parent.getInfo().getName() + ".json. Check that the file path is correct.");
                parent.getLoadText().setText("not saved");
            }
        } else {
            JOptionPane.showMessageDialog(parent, "Please provide a valid directory.");
            parent.getLoadText().setText("not saved");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String path = FileSelectorUtil.selectSaveFolder(parent);
        if (path != null) {
            process(path);
        }
    }

}
