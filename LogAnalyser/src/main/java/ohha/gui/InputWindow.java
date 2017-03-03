package ohha.gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class to open a simple window for querying input from the user.
 * 
 * @author mikkotiainen
 * @deprecated This class has currently been replaced by JOptionPane usage.
 */
public class InputWindow extends JFrame implements ActionListener {
    
    private JLabel instructionLabel;
    private JTextField inputField;
    private JButton setButton;
    private JPanel panel;
    private ExtraInputHandler parent;

    /**
     * Initializes a new InputWindow.
     * @param instruction The instructions for the input.
     * @param title Title of the window.
     * @param parent The parent for whose process-method the input is passed on.
     */
    public InputWindow(String instruction, String title, ExtraInputHandler parent) {
        super(title);
        this.instructionLabel = new JLabel(instruction);
        this.parent = parent;
        this.panel = new JPanel();
        this.inputField = new JTextField(20);
        this.setButton = new JButton("set");
        setButton.addActionListener(this);
        panel.add(instructionLabel);
        panel.add(inputField);
        panel.add(setButton);
        this.add(panel);
        this.setVisible(true);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        parent.process(inputField.getText());
        this.dispose();
    }

    public JLabel getInstructionLabel() {
        return instructionLabel;
    }

    public void setInstructionLabel(JLabel instructionLabel) {
        this.instructionLabel = instructionLabel;
    }

    public JTextField getInputField() {
        return inputField;
    }

    public void setInputField(JTextField inputField) {
        this.inputField = inputField;
    }

    public JButton getSetButton() {
        return setButton;
    }

    public void setSetButton(JButton setButton) {
        this.setButton = setButton;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    
}
