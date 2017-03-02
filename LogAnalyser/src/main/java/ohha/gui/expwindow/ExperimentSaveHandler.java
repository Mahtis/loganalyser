package ohha.gui.expwindow;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import ohha.gui.ExperimentWindow;
import ohha.gui.ExtraInputHandler;
import ohha.gui.InputWindow;
import ohha.logic.ExperimentInfoIO;

/**
 *
 * @author mikkotiainen
 */
public class ExperimentSaveHandler implements ExtraInputHandler{
    
    private ExperimentWindow parent;

    public ExperimentSaveHandler(ExperimentWindow parent) {
        this.parent = parent;
    }

    @Override
    public void process(String input) {
        if (!input.isEmpty()) {
                try {
                    ExperimentInfoIO.saveToJson(parent.getInfo(), input + "/", parent.getInfo().getName());
                    JOptionPane.showMessageDialog(parent, "File saved at: "+ input + "/" + parent.getInfo().getName() + ".json");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(parent, "Couldn't save the file at: "+ input + "/" + parent.getInfo().getName() + ".json. Check that the file path is correct.");
                }
            } else {
                JOptionPane.showMessageDialog(parent, "Please provide a location.");
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new InputWindow("Enter folder where to save experiment settings", "Set file destination", this);
    }
    
    
}
