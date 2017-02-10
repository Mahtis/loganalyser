package ohha.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileFilter;

public class FileOpenHandler extends JPanel implements ActionListener {
    private JLabel fileLabel;
    private JButton fileButton;
    private JFileChooser fileChooser;
    private List<File> files;
    private JTextArea area;
    private JScrollPane scroll;

    public FileOpenHandler() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        files = new ArrayList<>();
        
        fileLabel = new JLabel("Selected files:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.weightx = 0.0;
        this.add(fileLabel, c);
        
        fileButton = new JButton("Files");
        fileButton.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.0;
        this.add(fileButton, c);
        area = new  JTextArea(10, 20);
        area.setEditable(false);
        scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.weighty = 0.0;
        c.weightx = 0.0;
        this.add(scroll, c);
        
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new LogFilter());
        fileChooser.setMultiSelectionEnabled(true);
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            for (File file : fileChooser.getSelectedFiles()) {
                if (!file.getName().endsWith(".log")) {
                    System.out.println("THE FILE TYPE IS INCORRECT");
                } else {
                    files.add(file);
                    area.append(file.getName() + "\n");
                }
            };
        }
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
