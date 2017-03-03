package ohha.gui.expwindow;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ohha.domain.ExperimentInfo;
import ohha.domain.ResponseMapping;
import ohha.gui.ExperimentWindow;
import ohha.gui.ExtraInputHandler;

/**
 * An ActionListener class to allow user to manually set an Experiment's correct
 * responses. Unlike the other similar ExperimentWindow-handlers, this class
 * requires a dynamically generated window and also some more complex input parsing.
 * Note regardless of the added complexity, the class still implements the 
 * ExtraInputHandler interface.
 *
 * @author mikkotiainen
 */
public class ExperimentRespMappingHandler implements ExtraInputHandler {
    
    private ExperimentWindow parent;

    /**
     * Initializes an ExperimentRespMappingHandler with the given ExperimentWindow
     * parent.
     *
     * @param parent ExperimentWindow whose ExperimentInfo is edited.
     */
    public ExperimentRespMappingHandler(ExperimentWindow parent) {
        this.parent = parent;
    }

    @Override
    public void process(String input) {
        ExperimentInfo info = new ExperimentInfo();
        List<ResponseMapping> mappings = new ArrayList<>();
        String[] correctsByCond = input.split("!");
        for (String line : correctsByCond) {
            String[] condLine = line.split(":");
            String cond = condLine[0];
            List<List<String>> corrects = new ArrayList<>();
            String[] nResponses = condLine[1].split(";");
            for (int i = 0; i < nResponses.length; i++) {
                corrects.add(Arrays.asList(nResponses[i].split(",")));
            }
            mappings.add(new ResponseMapping(cond, corrects, corrects.size()));
        }
        info.setResponseMappings(mappings);
        ExperimentInfo oldInfo = parent.getInfo();
        info.setName(oldInfo.getName());
        info.setConditions(oldInfo.getConditions());
        info.setResponseCodes(oldInfo.getResponseCodes());
        info.setResponseNames(oldInfo.getResponseNames());
        parent.setInfo(info);
        parent.getLoadText().setText("not saved");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new SetCorrResponsesWindow(this, parent.getInfo().getConditions());
    }
    
    
    
}
