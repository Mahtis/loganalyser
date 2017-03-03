package ohha.gui;

import java.awt.event.ActionListener;

/**
 * This interface extends ActionListener but also demands a process-method from
 * the implementing class.
 * @author mikkotiainen
 */
public interface ExtraInputHandler extends ActionListener {
    
    /**
     * This is supposed to be a method that can be called by a secondary input
     * to prompt the implementing class to process that input. This class was
     * meant to be used in conjuction with the InputWindow-class that pops-up
     * an additional input query.
     * @param input The input to be processed (from the secondary input source,
     * i.e. InputWindow).
     */
    public void process(String input);
    
}
