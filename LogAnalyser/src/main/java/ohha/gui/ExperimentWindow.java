package ohha.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import ohha.domain.ExperimentInfo;
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

    public ExperimentWindow(ExperimentInfo info) {
        super("Experiment selector");
        this.info = info;

        panel = new JPanel();
        loadText = new JLabel();
        if (info == null) {
            loadText.setText("No Experiment loaded");
        } else {
            loadText.setText(info.getName());
        }
        loadButton = new JButton("Load Experiment");
        loadButton.addActionListener(this);

        panel.add(loadText);
        panel.add(loadButton);
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
        File file = FileOpenHandler.singleFileSelection(panel, new JsonFilter());
        if (!file.getName().endsWith(".json")) {
            JOptionPane.showMessageDialog(this, "Not a JSON file.");
        } else {
            info = ExperimentInfoIO.loadFromJson(file.getPath());
            if (info.getName() != null) {
                loadText.setText(info.getName());
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
