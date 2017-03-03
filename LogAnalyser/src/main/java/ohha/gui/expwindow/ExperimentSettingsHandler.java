package ohha.gui.expwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ohha.gui.ExperimentWindow;
import ohha.gui.MainView;

/**
 * Simple ActionListener class to handle Experiment-button presses.
 *
 * @author mikkotiainen
 */
public class ExperimentSettingsHandler implements ActionListener {

    private MainView parent;

    /**
     * Initialize a new ExperimentSettingsHandler.
     *
     * @param parent The MainView that the handled button is part of.
     */
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
