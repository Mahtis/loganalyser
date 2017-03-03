
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
        parent.getLoadText().setText("saved");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog(parent, "<HTML> Enter experiment response codes, <br> separated by commas </HTML>", "Response codes", JOptionPane.PLAIN_MESSAGE);
        if(input != null) {
            process(input);
        }
        //new InputWindow("Enter experiment response codes (separated with comma)","Set response codes",this);
    }
    
}
