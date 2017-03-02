package ohha.gui;

import ohha.gui.expwindow.ExperimentSelectionHandler;
import ohha.gui.expwindow.ExperimentRespNameHandler;
import ohha.gui.expwindow.ExperimentConditionHandler;
import ohha.gui.expwindow.ExperimentRespMappingHandler;
import ohha.gui.expwindow.ExperimentRespCodeHandler;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import ohha.domain.ExperimentInfo;
import ohha.gui.expwindow.ExperimentNameHandler;
import ohha.gui.expwindow.ExperimentSaveHandler;

/**
 * A window for selecting ExperimentInformation.
 *
 * @author mikkotiainen
 */
public class ExperimentWindow extends JFrame {

    private ExperimentInfo info;
    private JPanel panel;
    private JLabel loadText;
    private JButton loadButton;
    private JButton saveButton;
    private JLabel nameTitle;
    private JLabel conditionTitle;
    private JLabel respCodeTitle;
    private JLabel respNameTitle;
    private JLabel respMappingTitle;
    private JLabel name;
    private JLabel conditions;
    private JLabel respCodes;
    private JLabel respNames;
    private JLabel respMappings;
    private JButton nameButton;
    private JButton condButton;
    private JButton respCodeButton;
    private JButton respNameButton;
    private JButton respMappingButton;
    private JScrollPane scrollMappings;

    private MainView parent;

    /**
     * Creates a new ExperimentWindow with the given ExperimentInfo and parent
     * window.
     *
     * @param info ExperimentInfo for the new window. Can be null.
     * @param parent Parent view, to where any new loaded or set ExperimentInfo
     * can be passed on to.
     */
    public ExperimentWindow(ExperimentInfo info, MainView parent) {
        super("Experiment selector");

        this.parent = parent;
        if (info == null) {
            this.info = new ExperimentInfo();
        } else {
            this.info = info;
        }
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 3));
        loadText = new JLabel();
        nameTitle = new JLabel();
        conditionTitle = new JLabel();
        respCodeTitle = new JLabel();
        respNameTitle = new JLabel();
        respMappingTitle = new JLabel();
        name = new JLabel();
        conditions = new JLabel();
        respCodes = new JLabel();
        respNames = new JLabel();
        respMappings = new JLabel();
        loadButton = new JButton();
        loadButton.addActionListener(new ExperimentSelectionHandler(this));
        saveButton = new JButton();
        saveButton.addActionListener(new ExperimentSaveHandler(this));
        nameButton = new JButton();
        nameButton.addActionListener(new ExperimentNameHandler(this));
        condButton = new JButton();
        condButton.addActionListener(new ExperimentConditionHandler(this));
        respCodeButton = new JButton();
        respCodeButton.addActionListener(new ExperimentRespCodeHandler(this));
        respNameButton = new JButton();
        respNameButton.addActionListener(new ExperimentRespNameHandler(this));
        respMappingButton = new JButton();
        respMappingButton.addActionListener(new ExperimentRespMappingHandler(this));
        scrollMappings = new JScrollPane(respMappings);

        panel.add(loadText);
        panel.add(loadButton);
        panel.add(saveButton);
        panel.add(nameTitle);
        panel.add(name);
        panel.add(nameButton);
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
        this.setVisible(true);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(500, 250));
        this.pack();

    }

    @Override
    public void paint(Graphics g) {
        if (info.getName() == null) {
            loadText.setText("No Experiment loaded");
        } else {
            loadText.setText(info.getName() + " loaded");
        }
        if (info.getName() == null) {
            name.setText("no name");
        } else {
            name.setText(info.getName());
        }
        if (info.getConditions() == null) {
            conditions.setText("[]");
        } else {
            conditions.setText(info.getConditions().toString());
        }
        if (info.getResponseCodes() == null) {
            respCodes.setText("[]");
        } else {
            respCodes.setText(info.getResponseCodes().toString());
        }
        if (info.getResponseNames() == null) {
            respNames.setText("[]");
        } else {
            respNames.setText(info.getResponseNames().toString());
        }
        if (info.getResponseMappings() == null) {
            respMappings.setText("[]");
        } else {
            respMappings.setText(info.getResponseMappings().toString());
        }
        loadButton.setText("Load Experiment");
        saveButton.setText("Save Experiment");
        nameButton.setText("Experiment Name");
        condButton.setText("Conditions");
        respCodeButton.setText("Codes");
        respNameButton.setText("Names");
        respMappingButton.setText("Correct");
        conditionTitle.setText("Conditions: ");
        respCodeTitle.setText("Response codes");
        respNameTitle.setText("Response names");
        respMappingTitle.setText("Correct responses:");
    }

    public ExperimentInfo getInfo() {
        return info;
    }

    public void setInfo(ExperimentInfo info) {
        this.info = info;
        parent.setInfo(info);
        parent.getExpLabel().setText(info.getName());
        this.repaint();
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
