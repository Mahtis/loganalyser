package ohha.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileFilter;

public class FileOpenHandler extends JPanel implements ActionListener {

    private JFileChooser fileChooser;
    private List<File> files;
    private JTextArea area;
    private JScrollPane scroll;

    public FileOpenHandler() {
        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileFilter(new LogFilter());
        this.fileChooser.setMultiSelectionEnabled(true);
        this.files = new ArrayList<>();
        JLabel text = new JLabel("Selected files:");
        this.add(text);
        JButton button1 = new JButton("Files");
        button1.addActionListener(this);
        this.add(button1);
        area = new  JTextArea(10, 20);
        area.setEditable(false);
        scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scroll);
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
