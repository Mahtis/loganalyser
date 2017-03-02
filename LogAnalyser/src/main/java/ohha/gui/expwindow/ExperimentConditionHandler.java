package ohha.gui.expwindow;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new InputWindow("Enter experiment conditions (separated with comma)","Set conditions",this);
    }
    
}
