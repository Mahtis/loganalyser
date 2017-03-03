package ohha.gui.expwindow;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import ohha.domain.ExperimentInfo;
import ohha.gui.ExperimentWindow;
import ohha.gui.ExtraInputHandler;
import ohha.gui.InputWindow;

/**
 *
 * @author mikkotiainen
 */
public class ExperimentConditionHandler implements ExtraInputHandler {
    
    private ExperimentWindow parent;

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
        if(input != null) {
            process(input);
        }
        //new InputWindow("Enter experiment conditions (separated with comma)","Set conditions",this);
    }
    
}
