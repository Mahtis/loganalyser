package ohha.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileFilter;
import ohha.domain.ExperimentInfo;
import ohha.domain.SubjectData;

public class MainView extends JPanel {

    private JLabel fileLabel;
    private List<File> files;
    private List<SubjectData> data;
    private DefaultListModel model;
    private JList list;
    private JScrollPane scroll;
    private JButton fileButton;
    private JButton experimentButton;
    private JButton parseButton;
    private JButton analyseButton;
    private JButton deleteButton;
    private ExperimentInfo info;

    /**
     * Creates a new MainView.
     */
    public MainView() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        files = new ArrayList<>();
        data = new ArrayList<>();

        fileLabel = new JLabel("Selected files:");
        setElementLocation(c, 0, 0, 1, 1, 0, 0);
        this.add(fileLabel, c);

        fileButton = new JButton("Files");
        fileButton.addActionListener(new LogSelectionHandler(this));
        setElementLocation(c, 1, 0, 1, 1, 0, 0);
        this.add(fileButton, c);

        model = new DefaultListModel();
        list = new JList(model);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scroll = new JScrollPane(list);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setElementLocation(c, 0, 1, 3, 5, 0, 0);
        this.add(scroll, c);

        experimentButton = new JButton("Set Experiment");
        experimentButton.addActionListener(new ExperimentSettingsHandler(this));
        setElementLocation(c, 4, 1, 1, 1, 0, 0);
        this.add(experimentButton, c);

        parseButton = new JButton("Parse logfile");
        parseButton.addActionListener(new ParseHandler(this));
        setElementLocation(c, 4, 2, 1, 1, 0, 0);
        this.add(parseButton, c);

        analyseButton = new JButton("Analyse trials");
        analyseButton.addActionListener(new AnalyseHandler(this));
        setElementLocation(c, 4, 3, 1, 1, 0, 0);
        this.add(analyseButton, c);

        deleteButton = new JButton("Remove selected");
        deleteButton.addActionListener(new DeletionHandler(this));
        setElementLocation(c, 0, 6, 1, 1, 0, 0);
        this.add(deleteButton, c);

    }
            
    private void setElementLocation(GridBagConstraints c, int x, int y, int width, int height, double weightx, double weighty) {
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = height;
        c.weightx = weightx;
        c.weighty = weighty;
    }

    /**
     * Opens a file selector and returns the selected file.
     * @param parent The parent component.
     * @param filter Custom filter to disallow selecting wrong file types.
     * @return The user selected file.
     */
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

    /**
     * Opens a file selector and returns the selected files.
     * @param parent The parent component.
     * @param filter Custom filter to disallow selecting wrong file types.
     * @return The user selected files.
     */
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


    public ExperimentInfo getInfo() {
        return info;
    }

    public void setInfo(ExperimentInfo info) {
        this.info = info;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<SubjectData> getData() {
        return data;
    }

    public void setData(List<SubjectData> data) {
        this.data = data;
    }

    public DefaultListModel getModel() {
        return model;
    }

    public void setModel(DefaultListModel model) {
        this.model = model;
    }

    public JList getList() {
        return list;
    }

    public void setList(JList list) {
        this.list = list;
    }

}
