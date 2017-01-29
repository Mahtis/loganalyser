package ohha.logic;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ohha.domain.ExperimentInfo;
import ohha.domain.ResponseMapping;

public class ExperimentInfoIO {

    public ExperimentInfoIO() {
    }

    public ExperimentInfo loadFromFile(String filename) {
        List<String> lines = FileReader.readFile(filename);
        if (!lines.isEmpty()) {
            ExperimentInfo info = new ExperimentInfo();
            //button codes and definitions located on lines 0 and 1
            List<String> respCodes = Arrays.asList(lines.get(0).split("\t"));
            info.setResponseCodes(respCodes);
            List<String> respNames = Arrays.asList(lines.get(1).split("\t"));
            info.setResponseNames(respNames);
            // conditions and response mapping on lines 2 ->
            List<String> conds = new ArrayList<>();
            for (int i = 2; i < lines.size(); i++) {
                List<String> line = Arrays.asList(lines.get(i).split("\t"));
                // new response mapping with condition from column 0
                ResponseMapping mapping = new ResponseMapping(line.get(0));
                // set this also to ExperimentInfo for easier access.
                conds.add(line.get(0));
                // number of responses in column 1
                mapping.setnOfResponses(Integer.parseInt(line.get(1)));
                // correct response for each response in columns 2->
                List<List<String>> corrResps = new ArrayList<>();
                for (int j = 2; j < line.size(); j++) {
                    // multiple correct responses for each response-condition are separated by ,
                    // e.g. 1,2 2   1,2,3 correct for the first is 1 or 2
                    // correct for second is 2, correct for third is 1, 2 or 3.
                    // One resp per condition would be "cond    1   1" and the
                    // list would be mapping.get(0).get(0) == 1, whereas the above
                    // could be mapping.get(2).get(2) == 3.
                    corrResps.add(Arrays.asList(line.get(j).split(",")));
                }
                mapping.setCorrectResponses(corrResps);
                info.addResponseMapping(mapping);
            }
            info.setConditions(conds);
            return info;
        }
        return null;
    }

    public void saveToFile(ExperimentInfo info, String location, String expName) throws IOException {
        List<String> lines = new ArrayList<>();
        List<ResponseMapping> mappings = info.getResponseMappings();
        List<String> respCodes = info.getResponseCodes();
        List<String> respNames = info.getResponseNames();
        StringBuilder codeLine = new StringBuilder();
        for (String code : respCodes) {
            codeLine.append(code);
            codeLine.append("\t");
        }
        lines.add(codeLine.toString().trim());
        StringBuilder nameLine = new StringBuilder();
        for (String name : respNames) {
            nameLine.append(name);
            nameLine.append("\t");
        }
        lines.add(nameLine.toString().trim());
        for (ResponseMapping mapping : mappings) {
            StringBuilder line = new StringBuilder();
            line.append(mapping.getCondition());
            line.append("\t");
            line.append(mapping.getnOfResponses());
            line.append("\t");
            for(String corr : mapping.getCorrectResponses().get(mappings.indexOf(mapping))) {
                line.append(corr);
                line.append(",");
            }
            line.deleteCharAt(line.length()-1);
        }
        Path file = Paths.get(location + expName + ".txt");
        Files.write(file, lines, Charset.forName("UTF-8"));
//Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
    }

}
