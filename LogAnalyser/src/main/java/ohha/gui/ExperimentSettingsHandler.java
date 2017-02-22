package ohha.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExperimentSettingsHandler implements ActionListener{
    
    private MainView parent;

    public ExperimentSettingsHandler(MainView parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ExperimentWindow expHandler = new ExperimentWindow(parent.getInfo(), parent);
//            this.setVisible(false);
//            for(Component i : this.getComponents()) {
//                i.setEnabled(false);
//            }
    }
    
}
