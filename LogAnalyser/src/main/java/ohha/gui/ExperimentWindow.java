package ohha.gui;

import java.awt.Component;
import ohha.gui.expwindow.ExperimentSelectionHandler;
import ohha.gui.expwindow.ExperimentRespNameHandler;
import ohha.gui.expwindow.ExperimentConditionHandler;
import ohha.gui.expwindow.ExperimentRespMappingHandler;
import ohha.gui.expwindow.ExperimentRespCodeHandler;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
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
    private JScrollPane scrollName;
    private JScrollPane scrollCodes;
    private JScrollPane scrollNames;
    private JScrollPane scrollMappings;
    private JScrollPane scrollConds;

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
        loadText = new JLabel("not saved", SwingConstants.CENTER);
        nameTitle = new JLabel("Name: ");
        conditionTitle = new JLabel("Conditions: ");
        respCodeTitle = new JLabel("Response codes: ");
        respNameTitle = new JLabel("Response names: ");
        respMappingTitle = new JLabel("Correct respones: ");
        name = new JLabel();
        conditions = new JLabel();
        respCodes = new JLabel();
        respNames = new JLabel();
        respMappings = new JLabel();
        loadButton = new JButton("Load Experiment");
        loadButton.addActionListener(new ExperimentSelectionHandler(this));
        saveButton = new JButton("Save Experiment");
        saveButton.addActionListener(new ExperimentSaveHandler(this));
        nameButton = new JButton("Experiment Name");
        nameButton.addActionListener(new ExperimentNameHandler(this));
        condButton = new JButton("Conditions");
        condButton.addActionListener(new ExperimentConditionHandler(this));
        respCodeButton = new JButton("Codes");
        respCodeButton.addActionListener(new ExperimentRespCodeHandler(this));
        respNameButton = new JButton("Names");
        respNameButton.addActionListener(new ExperimentRespNameHandler(this));
        respMappingButton = new JButton("Correct");
        respMappingButton.addActionListener(new ExperimentRespMappingHandler(this));
        scrollName = new JScrollPane(name);
        scrollConds = new JScrollPane(conditions);
        scrollCodes = new JScrollPane(respCodes);
        scrollNames = new JScrollPane(respNames);
        scrollMappings = new JScrollPane(respMappings);

        panel.add(loadText);
        panel.add(loadButton);
        panel.add(saveButton);
        panel.add(nameTitle);
        panel.add(scrollName);
        panel.add(nameButton);
        panel.add(conditionTitle);
        panel.add(scrollConds);
        panel.add(condButton);
        panel.add(respCodeTitle);
        panel.add(scrollCodes);
        panel.add(respCodeButton);
        panel.add(respNameTitle);
        panel.add(scrollNames);
        panel.add(respNameButton);
        panel.add(respMappingTitle);
        panel.add(scrollMappings);
        panel.add(respMappingButton);

        this.setContentPane(panel);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(500, 250));
        this.pack();

    }

    @Override
    public void paint(Graphics g) {
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
        for (Component component : this.getComponents()) {
            component.repaint();
        }
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

    public JLabel getLoadText() {
        return loadText;
    }

    public void setLoadText(JLabel loadText) {
        this.loadText = loadText;
    }

}
