package ohha.gui.expwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import ohha.domain.ExperimentInfo;
import ohha.gui.ExperimentWindow;
import ohha.gui.FileSelectorUtil;
import ohha.logic.ExperimentInfoIO;

/**
 * An ActionListener class to allow user load an Experiment from file.
 * Note that this class does not implement the ExtreInputHandler interface.
 *
 * @author mikkotiainen
 */
public class ExperimentSelectionHandler implements ActionListener {

    private ExperimentWindow parent;
    private ExperimentInfo info;

    /**
     * Initializes an ExperimentSelectionHandler with the given ExperimentWindow
     * parent.
     *
     * @param parent ExperimentWindow to where the ExperimentInfo is loaded to.
     */
    public ExperimentSelectionHandler(ExperimentWindow parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File file = FileSelectorUtil.singleFileSelection(parent, new JsonFilter());
        if (!file.getName().endsWith(".json")) {
            JOptionPane.showMessageDialog(parent, "Not a JSON file.");
        } else {
            info = ExperimentInfoIO.loadFromJson(file.getPath());
            parent.setInfo(info);
            parent.getLoadText().setText("saved");
        }
    }

    private class JsonFilter extends FileFilter {

        @Override
        public boolean accept(File pathname) {
            return pathname.getName().endsWith(".json") || pathname.isDirectory();
        }

        @Override
        public String getDescription() {
            return "JSON file .json";
        }
    }

}
