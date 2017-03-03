package ohha.gui.expwindow;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import ohha.domain.ExperimentInfo;
import ohha.gui.ExperimentWindow;
import ohha.gui.ExtraInputHandler;

/**
 * An ActionListener class to allow user to manually set the name of the Experiment.
 *
 * @author mikkotiainen
 */
public class ExperimentNameHandler implements ExtraInputHandler {

    private ExperimentWindow parent;

    /**
     * Initializes an ExperimentNameHandler with the given ExperimentWindow
     * parent.
     *
     * @param parent ExperimentWindow whose ExperimentInfo is edited.
     */
    public ExperimentNameHandler(ExperimentWindow parent) {
        this.parent = parent;
    }

    @Override
    public void process(String input) {
        String name = input;
        if (name.isEmpty()) {
            name = "empty";
        }
        ExperimentInfo info = new ExperimentInfo();
        info.setName(name);
        ExperimentInfo oldInfo = parent.getInfo();
        info.setResponseNames(oldInfo.getResponseNames());
        info.setConditions(oldInfo.getConditions());
        info.setResponseMappings(oldInfo.getResponseMappings());
        info.setResponseCodes(oldInfo.getResponseCodes());
        parent.setInfo(info);
        parent.getLoadText().setText("not saved");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog(parent, "Set a name for the experiment", "Experiment name", JOptionPane.PLAIN_MESSAGE);
        if (input != null) {
            process(input);
        }
    }

}
