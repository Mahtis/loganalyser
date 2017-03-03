package ohha.gui.expwindow;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import ohha.gui.ExperimentWindow;
import ohha.gui.ExtraInputHandler;
import ohha.logic.ExperimentInfoIO;

/**
 *
 * @author mikkotiainen
 */
public class ExperimentSaveHandler implements ExtraInputHandler {

    private ExperimentWindow parent;

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
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Select folder to save the experiment");
        chooser.setCurrentDirectory(Paths.get("").toAbsolutePath().toFile());
        int retrieval = chooser.showSaveDialog(parent);
        if (retrieval == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String path = file.getAbsolutePath();
            process(path);
        }
        //new InputWindow("Enter folder where to save experiment settings", "Set file destination", this);
    }

}
