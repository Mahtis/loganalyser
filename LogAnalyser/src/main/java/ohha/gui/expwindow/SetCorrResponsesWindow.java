package ohha.gui.expwindow;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import ohha.gui.ExtraInputHandler;

/**
 * Creates a window for setting the correct responses for an ExperimentInfo. The
 * window is dynamically generated based on the number of conditions in the
 * Experiment. Because of this, using a JOptionPane was not possible.
 *
 * @author mikkotiainen
 */
public class SetCorrResponsesWindow extends JFrame implements ActionListener {

    private JLabel instructionLabel;
    private JLabel instructionLabel2;
    private JButton setButton;
    private JPanel panel;
    private ExtraInputHandler parent;
    private List<String> conditions;
    private List<JLabel> condNames;
    private List<JTextField> inputs;

    /**
     * Initializes a new SetCorrResponsesWindow with the given parent and
     * conditions.
     *
     * @param parent The parent handler of this window.
     * @param conditions List of the conditions in the edited ExperimentInfo.
     */
    public SetCorrResponsesWindow(ExtraInputHandler parent, List<String> conditions) {
        this.parent = parent;
        this.conditions = conditions;
        initComponents();
        this.setVisible(true);
        this.pack();
    }

    private void initComponents() {
        this.panel = new JPanel(new GridLayout(0, 2));
        this.instructionLabel = new JLabel("<HTML> Set correct responses for each condition <br> use 0 for no response </HTML>", SwingConstants.CENTER);
        this.instructionLabel2 = new JLabel("<HTML> Separate codes for one response with comma <br> and separate responses with semicolon </HTML>", SwingConstants.CENTER);
        panel.add(instructionLabel);
        panel.add(instructionLabel2);

        condNames = new ArrayList<>();
        inputs = new ArrayList<>();
        for (int i = 0; i < conditions.size(); i++) {
            condNames.add(new JLabel(conditions.get(i), SwingConstants.CENTER));
            inputs.add(new JTextField(20));
            panel.add(condNames.get(i));
            panel.add(inputs.get(i));
        }
        this.setButton = new JButton("save");
        setButton.addActionListener(this);
        panel.add(setButton);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = "";
        for (int i = 0; i < conditions.size(); i++) {
            input += conditions.get(i) + ":" + inputs.get(i).getText() + "!";
        }
        if (!input.isEmpty()) {
            input = input.substring(0, input.length() - 1);
            parent.process(input);
        }
        this.dispose();
    }

}
