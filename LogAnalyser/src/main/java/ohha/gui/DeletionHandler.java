package ohha.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletionHandler implements ActionListener{
    
    private MainView parent;
    

    public DeletionHandler(MainView parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Object i : parent.getList().getSelectedValuesList()) {
                parent.getModel().removeElement(i);
            }
    }
    
    
}
