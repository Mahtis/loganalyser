package ohha.gui.expwindow;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import ohha.domain.ExperimentInfo;
import ohha.gui.ExperimentWindow;
import ohha.gui.ExtraInputHandler;

/**
 * An ActionListener class to allow user to manually set Experiment conditions.
 *
 * @author mikkotiainen
 */
public class ExperimentConditionHandler implements ExtraInputHandler {

    private ExperimentWindow parent;

    /**
     * Initializes an ExperimentConditionHandler with the given ExperimentWindow
     * parent.
     *
     * @param parent ExperimentWindow whose ExperimentInfo is edited.
     */
    public ExperimentConditionHandler(ExperimentWindow parent) {
        this.parent = parent;
    }

    @Override
    public void process(String input) {
        List<String> conditions = Arrays.asList(input.split(","));
        ExperimentInfo info = new ExperimentInfo();
        info.setConditions(conditions);
        ExperimentInfo oldInfo = parent.getInfo();
        info.setName(oldInfo.getName());
        info.setResponseMappings(oldInfo.getResponseMappings());
        info.setResponseCodes(oldInfo.getResponseCodes());
        info.setResponseNames(oldInfo.getResponseNames());
        parent.setInfo(info);
        parent.getLoadText().setText("not saved");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog(parent, "<HTML> Enter experiment conditions, <br> separated by commas </HTML>", "Set conditions", JOptionPane.PLAIN_MESSAGE);
        if (input != null) {
            process(input);
        }
    }

}
