package ohha.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import ohha.domain.ExperimentInfo;
import ohha.domain.ResponseMapping;
import ohha.logic.ExperimentInfoIO;

/**
 * A window for selecting ExperimentInformation.
 *
 * @author mikkotiainen
 */
public class ExperimentWindow extends JFrame implements ActionListener {

    private ExperimentInfo info;
    private JPanel panel;
    private JLabel loadText;
    private JButton loadButton;
    private JButton saveButton;
    private JLabel conditionTitle;
    private JLabel respCodeTitle;
    private JLabel respNameTitle;
    private JLabel respMappingTitle;
    private JLabel conditions;
    private JLabel respCodes;
    private JLabel respNames;
    private JLabel respMappings;
    private JButton condButton;
    private JButton respCodeButton;
    private JButton respNameButton;
    private JButton respMappingButton;
    private JScrollPane scrollMappings;

    private MainView parent;

    /**
     * Creates a new ExperimentWindow with the given ExperimentInfo and parent window.
     * @param info ExperimentInfo for the new window. Can be null.
     * @param parent Parent view, to where any new loaded or set ExperimentInfo can
     * be passed on to.
     */
    public ExperimentWindow(ExperimentInfo info, MainView parent) {
        super("Experiment selector");
        this.info = info;
        this.parent = parent;

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 3));
        loadText = new JLabel();
        conditionTitle = new JLabel("Conditions: ");
        respCodeTitle = new JLabel("Response codes");
        respNameTitle = new JLabel("Response names");
        respMappingTitle = new JLabel("Correct responses:");
        if (info == null) {
            loadText.setText("No Experiment loaded");
            conditions = new JLabel("[]");
            respCodes = new JLabel("[]");
            respNames = new JLabel("[]");
            respMappings = new JLabel("[]");
        } else {
            loadText.setText(info.getName());
            conditions = new JLabel(info.getConditions().toString());
            respCodes = new JLabel(info.getResponseCodes().toString());
            respNames = new JLabel(info.getResponseNames().toString());
            respMappings = new JLabel(info.getResponseMappings().toString());
        }
        loadButton = new JButton("Load Experiment");
        loadButton.addActionListener(this);
        saveButton = new JButton("Save Experiment");
        saveButton.addActionListener(this);
        condButton = new JButton("Conditions");
        respCodeButton = new JButton("Codes");
        respNameButton = new JButton("Names");
        respMappingButton = new JButton("Correct");
        scrollMappings = new JScrollPane(respMappings);

        panel.add(loadText);
        panel.add(loadButton);
        panel.add(saveButton);
        panel.add(conditionTitle);
        panel.add(conditions);
        panel.add(condButton);
        panel.add(respCodeTitle);
        panel.add(respCodes);
        panel.add(respCodeButton);
        panel.add(respNameTitle);
        panel.add(respNames);
        panel.add(respNameButton);
        panel.add(respMappingTitle);
        panel.add(scrollMappings);
        panel.add(respMappingButton);

        this.setContentPane(panel);
        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton) {
            experimentSelection();
        }
    }

    private void experimentSelection() {
        File file = MainView.singleFileSelection(panel, new JsonFilter());
        if (!file.getName().endsWith(".json")) {
            JOptionPane.showMessageDialog(this, "Not a JSON file.");
        } else {
            info = ExperimentInfoIO.loadFromJson(file.getPath());
            if (info.getName() != null) {
                loadText.setText(info.getName());
                conditions.setText(info.getConditions().toString());
                respCodes.setText(info.getResponseCodes().toString());
                respNames.setText(info.getResponseNames().toString());
                String text = "<html>";
                for (ResponseMapping r : info.getResponseMappings()) {
                    text += r.toString() + "<br>";
                }
                text += "</html>";
                respMappings.setText(text);
                parent.setInfo(info);
            }
        }
    }

    private class JsonFilter extends FileFilter {

        @Override
        public boolean accept(File pathname) {
            return pathname.getName().endsWith(".json") || pathname.isDirectory();
        }

        @Override
        public String getDescription() {
            return "JSON file .json";
        }
    }

}
