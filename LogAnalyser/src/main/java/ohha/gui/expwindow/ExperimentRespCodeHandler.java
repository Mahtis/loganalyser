
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
public class ExperimentRespCodeHandler implements ExtraInputHandler {
    
    private ExperimentWindow parent;

    public ExperimentRespCodeHandler(ExperimentWindow parent) {
        this.parent = parent;
    }

    @Override
    public void process(String input) {
        List<String> codes = Arrays.asList(input.split(","));
        ExperimentInfo info = new ExperimentInfo();
        info.setResponseCodes(codes);
        ExperimentInfo oldInfo = parent.getInfo();
        info.setName(oldInfo.getName());
        info.setConditions(oldInfo.getConditions());
        info.setResponseMappings(oldInfo.getResponseMappings());
        info.setResponseNames(oldInfo.getResponseNames());
        parent.setInfo(info);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new InputWindow("Enter experiment response codes (separated with comma)","Set response codes",this);
    }
    
}
