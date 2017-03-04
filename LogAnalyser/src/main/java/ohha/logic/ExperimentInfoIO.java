package ohha.logic;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import ohha.domain.ExperimentInfo;
import ohha.domain.ResponseMapping;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This is a class for loading and saving ExperimentInfo-objects.
 *
 * @author Mikko Tiainen
 *
 * The methods loadFromFile and saveToFile are deprecated, and all experiment
 * data should be handled in json form, which offers better stability and
 * readability.
 *
 */
public class ExperimentInfoIO {


    /**
     * Reads specifications for an ExperimentInfo object from a file.
     *
     * @param filename file to read info from.
     * @return ExperienceInfo object read from file.
     */
    public static ExperimentInfo loadFromJson(String filename) {
        List<String> lines = FileReader.readFile(filename);
        String join = String.join("", lines);
        JSONObject json = new JSONObject(join);
        String name = (String) json.get("name");
        List<String> conditions = jsonListToStringList(json.getJSONArray("conditions"));
        List<String> responseCodes = jsonListToStringList(json.getJSONArray("responseCodes"));
        List<String> responseNames = jsonListToStringList(json.getJSONArray("responseNames"));

        ExperimentInfo info = new ExperimentInfo();
        info.setName(name);
        info.setConditions(conditions);
        info.setResponseCodes(responseCodes);
        info.setResponseNames(responseNames);

        JSONArray responseMappings = json.getJSONArray("responseMappings");
        for (int j = 0; j < responseMappings.length(); j++) {
            JSONObject mapping = responseMappings.getJSONObject(j);
            String cond = mapping.getString("condition");
            List<List<String>> correctResponses = new ArrayList<>();
            JSONArray corrResps = mapping.getJSONArray("correctResponses");
            for (int i = 0; i < corrResps.length(); i++) {
                correctResponses.add(new ArrayList<>());
                JSONArray respN = corrResps.getJSONArray(i);
                for (int k = 0; k < respN.length(); k++) {
                    String corrForN = respN.getString(k);
                    correctResponses.get(i).add(corrForN);
                }
            }
            int nResps = mapping.getInt("nOfResponses");
            info.addResponseMapping(new ResponseMapping(cond, correctResponses, nResps));
        }
        return info;
    }

    /**
     * Saves given ExperimentInfo to a file in Json-format.
     *
     * @param info ExperimentInfo to be saved.
     * @param location folder which the file is saved.
     * @param expName name of the experiment, used as the filename.
     * @return true if the file successfully saved and the given ExperimentInfo
     * has conditions.
     */
    public static boolean saveToJson(ExperimentInfo info, String location, String expName) {
        JSONObject json = new JSONObject();
        if (info.getConditions().isEmpty()) {
            return false;
        }
        json.put("name", info.getName());
        json.put("conditions", info.getConditions());
        json.put("responseCodes", info.getResponseCodes());
        if (info.getResponseNames().isEmpty()) {
            json.put("responseNames", info.getResponseCodes());
        } else {
            json.put("responseNames", info.getResponseNames());
        }
        List<JSONObject> corrects = new ArrayList<>();
        for (ResponseMapping map : info.getResponseMappings()) {
            JSONObject curCond = new JSONObject();
            curCond.put("condition", map.getCondition());
            curCond.put("correctResponses", map.getCorrectResponses());
            curCond.put("nOfResponses", map.getnOfResponses());
            corrects.add(curCond);
        }
        json.put("responseMappings", corrects);
        try (PrintWriter out = new PrintWriter(location + expName + ".json")) {
            json.write(out, 4, 0);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    
    private static List<String> jsonListToStringList(JSONArray json) {
        List<Object> objectList = json.toList();
        List<String> strings = objectList.stream()
                .map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());
        return strings;
    }

    
}
