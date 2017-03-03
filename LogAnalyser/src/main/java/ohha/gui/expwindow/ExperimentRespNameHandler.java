package ohha.gui.expwindow;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import ohha.domain.ExperimentInfo;
import ohha.gui.ExperimentWindow;
import ohha.gui.ExtraInputHandler;

/**
 * An ActionListener class to allow user to manually set Experiment response
 * names.
 *
 * @author mikkotiainen
 */
public class ExperimentRespNameHandler implements ExtraInputHandler {

    private ExperimentWindow parent;

    /**
     * Initializes an ExperimentRespNameHandler with the given ExperimentWindow
     * parent.
     *
     * @param parent ExperimentWindow whose ExperimentInfo is edited.
     */
    public ExperimentRespNameHandler(ExperimentWindow parent) {
        this.parent = parent;
    }

    @Override
    public void process(String input) {
        List<String> names = Arrays.asList(input.split(","));
        ExperimentInfo info = new ExperimentInfo();
        info.setResponseNames(names);
        ExperimentInfo oldInfo = parent.getInfo();
        info.setName(oldInfo.getName());
        info.setConditions(oldInfo.getConditions());
        info.setResponseMappings(oldInfo.getResponseMappings());
        info.setResponseCodes(oldInfo.getResponseCodes());
        parent.setInfo(info);
        parent.getLoadText().setText("not saved");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog(parent, "<HTML> Enter names for the response codes, <br> separated by commas </HTML>", "Response names", JOptionPane.PLAIN_MESSAGE);
        if (input != null) {
            process(input);
        }
    }

}
