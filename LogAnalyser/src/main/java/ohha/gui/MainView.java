package ohha.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileFilter;

public class MainView extends JPanel implements ActionListener {

    private JLabel fileLabel;
    private List<File> files;
    private DefaultListModel model;
    private JList list;
    private JScrollPane scroll;
    private JButton fileButton;
    private JButton experimentButton;
    private JButton parseButton;
    private JButton analyseButton;
    private JButton deleteButton;

    public MainView() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        files = new ArrayList<>();

        fileLabel = new JLabel("Selected files:");
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.0;
        this.add(fileLabel, c);

        fileButton = new JButton("Files");
        fileButton.addActionListener(this);
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.0;
        this.add(fileButton, c);

        model = new DefaultListModel();
        list = new JList(model);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scroll = new JScrollPane(list);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 5;
        c.weighty = 0.0;
        c.weightx = 0.0;
        this.add(scroll, c);

        experimentButton = new JButton("Set Experiment");
        experimentButton.addActionListener(this);
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.0;
        this.add(experimentButton, c);

        parseButton = new JButton("Parse logfile");
        parseButton.addActionListener(this);
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.0;
        this.add(parseButton, c);

        analyseButton = new JButton("Analyse trials");
        analyseButton.addActionListener(this);
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.0;
        this.add(analyseButton, c);

        deleteButton = new JButton("Remove selected");
        deleteButton.addActionListener(this);
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.0;
        this.add(deleteButton, c);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fileButton) {
            logSelection();
        } else if (e.getSource() == experimentButton) {
            ExperimentWindow expHandler = new ExperimentWindow(null);
//            this.setVisible(false);
//            for(Component i : this.getComponents()) {
//                i.setEnabled(false);
//            }
        } else if (e.getSource() == deleteButton) {
            for (Object i : list.getSelectedValuesList()) {
                model.removeElement(i);
            }
        } else {
            model.addElement("NOT AN ELEMENT!");
        }

    }

    private void logSelection() {
        File[] logFiles = multiFileSelection(this, new LogFilter());
        for (File file : logFiles) {
            if (!file.getName().endsWith(".log")) {
                JOptionPane.showMessageDialog(this, "Not a .log-file.");
            } else {
                files.add(file);
                model.addElement(file);
            }
        }
    }

    public static File singleFileSelection(Component parent, FileFilter filter) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.setMultiSelectionEnabled(false);
        int returnVal = fileChooser.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }
    
    public static File[] multiFileSelection(Component parent, FileFilter filter) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.setMultiSelectionEnabled(true);
        int returnVal = fileChooser.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFiles();
        }
        return null;
    }

    private class LogFilter extends FileFilter {
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().endsWith(".log") || pathname.isDirectory();
        }

        @Override
        public String getDescription() {
            return "Presentation logfile .log";
        }
    }

}
