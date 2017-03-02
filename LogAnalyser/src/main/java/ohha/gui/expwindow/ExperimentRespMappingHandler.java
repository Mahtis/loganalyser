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
 *
 * @author mikkotiainen
 */
public class ExperimentRespMappingHandler implements ExtraInputHandler {
    
    private ExperimentWindow parent;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new SetCorrResponsesWindow(this, parent.getInfo().getConditions());
    }
    
    
    
}
