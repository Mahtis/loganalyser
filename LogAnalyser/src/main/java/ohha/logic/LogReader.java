package ohha.logic;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Class for reading logfiles.

public class LogReader {
    
    public List<String> readLog(String filename) {
        try {
            Path path = Paths.get(filename);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            return lines;
        } catch(IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
}
