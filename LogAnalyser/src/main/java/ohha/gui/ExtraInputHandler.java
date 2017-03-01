package ohha.gui;

import java.awt.event.ActionListener;

/**
 *
 * @author mikkotiainen
 */
public interface ExtraInputHandler extends ActionListener {
    public String getInput();
    public void setInput(String input);
}
