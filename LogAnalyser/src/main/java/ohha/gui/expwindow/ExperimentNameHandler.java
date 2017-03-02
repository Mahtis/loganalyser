package ohha.gui.expwindow;

import java.awt.event.ActionEvent;
import ohha.domain.ExperimentInfo;
import ohha.gui.ExperimentWindow;
import ohha.gui.ExtraInputHandler;
import ohha.gui.InputWindow;

/**
 *
 * @author mikkotiainen
 */
public class ExperimentNameHandler implements ExtraInputHandler {
    
    private ExperimentWindow parent;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new InputWindow("Enter a name for the experiment","Set experiment name",this);
    }
    
    
}
