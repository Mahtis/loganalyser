package ohha.logic;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple class for reading a file into a list of strings.
 * 
 * @author mikkotiainen
 * 
 */
public class FileReader {

    /**
     * Reads the given file into a list of Strings.
     * @param filename Full path of the file to be read.
     * @return List of file contents.
     */
    public static List<String> readFile(String filename) {
        try {
            Path path = Paths.get(filename);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
