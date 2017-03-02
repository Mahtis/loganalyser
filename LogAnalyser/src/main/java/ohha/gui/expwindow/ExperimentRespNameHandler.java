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
public class ExperimentRespNameHandler implements ExtraInputHandler {
    
    private ExperimentWindow parent;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new InputWindow("Enter experiment response names (separated with comma)","Set response names",this);
    }
    
    
}
