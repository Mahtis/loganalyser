package ohha.gui;

import java.awt.Component;
import java.io.File;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * Class that provides static functions for file selection for saving and loading.
 * @author mikkotiainen
 */
public class FileSelectorUtil {
    
    /**
     * Opens a file selector and returns the selected file.
     *
     * @param parent The parent component.
     * @param filter Custom filter to disallow selecting wrong file types.
     * @return The user selected file.
     */
    public static File singleFileSelection(Component parent, FileFilter filter) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setCurrentDirectory(Paths.get("").toAbsolutePath().toFile());
        int returnVal = fileChooser.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    /**
     * Opens a file selector and returns the selected files.
     *
     * @param parent The parent component.
     * @param filter Custom filter to disallow selecting wrong file types.
     * @return The user selected files.
     */
    public static File[] multiFileSelection(Component parent, FileFilter filter) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setCurrentDirectory(Paths.get("").toAbsolutePath().toFile());
        int returnVal = fileChooser.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFiles();
        }
        return null;
    }
    
    /**
     * Opens a file selector and returns the path of the folder for saving a file.
     * @param parent Parent component that the selector is associated with.
     * @return The path as a string.
     */
    public static String selectSaveFolder(Component parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Select save folder");
        chooser.setCurrentDirectory(Paths.get("").toAbsolutePath().toFile());
        int retrieval = chooser.showSaveDialog(parent);
        if (retrieval == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String path = file.getAbsolutePath();
            return path;
        } 
        return null;
    }
    
}
