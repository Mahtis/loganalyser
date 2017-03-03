package ohha.gui.expwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import ohha.domain.ExperimentInfo;
import ohha.gui.ExperimentWindow;
import ohha.gui.MainView;
import ohha.logic.ExperimentInfoIO;

/**
 *
 * @author mikkotiainen
 */
public class ExperimentSelectionHandler implements ActionListener {

    private ExperimentWindow parent;
    private ExperimentInfo info;

    public ExperimentSelectionHandler(ExperimentWindow parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File file = MainView.singleFileSelection(parent, new JsonFilter());
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
