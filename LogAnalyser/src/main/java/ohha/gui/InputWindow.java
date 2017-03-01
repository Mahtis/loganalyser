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
 *
 * @author mikkotiainen
 */
public class InputWindow extends JFrame implements ActionListener {
    
    private JLabel instructionLabel;
    private JTextField inputField;
    private JButton setButton;
    private String input;
    private JPanel panel;
    private LogWriterHandler parent;

    public InputWindow(String instruction, String title, LogWriterHandler parent) throws HeadlessException {
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
        input = inputField.getText();
        parent.setInput(input);
        parent.save();
        this.dispose();
    }
    
    
    
}
