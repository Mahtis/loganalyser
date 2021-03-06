package ohha.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ohha.domain.ExperimentInfo;
import ohha.domain.SubjectData;
import ohha.domain.Trial;

/**
 * Class to analyse data to draw a histogram (IN DEVELOPMENT).
 *
 * The class is still very much in development, so many things are still not
 * done.
 *
 * @author mikkotiainen
 */
public class AnalyseData {

    private SubjectData data;
    private int nBins;
    private double binSize;
    private List<Integer> reactionTimes;

    /**
     * Creates a new AnalyseData object. Sets the reactionTimes by default to be
     * the first response on a trial.
     *
     * @param data SubjectData to be analyzed.
     */
    public AnalyseData(SubjectData data) {
        this.data = data;
        reactionTimes = getReactionTimes(0);
    }

    /**
     * Extracts wanted reaction times from the subject data.
     *
     * @param i the number of the response for which the reactions time are
     * wanted. Note that the first response is 0.
     * @return List of reaction times in integer form.
     */
    private List<Integer> getReactionTimes(int i) {
        reactionTimes = new ArrayList<>();
        for (Trial trial : data.getTrials()) {
            int rt = trial.getReactionTimes().get(i);
            reactionTimes.add(rt);
        }
        return reactionTimes;
    }

    /**
     * Calculate values from reaction times to be used to draw a histogram.
     *
     * @return Array of values for each histogram bin.
     */
    public int[] calculateHistogramValues() {
        Collections.sort(reactionTimes);
        nBins = (int) Math.sqrt(reactionTimes.size());
        int min = Collections.min(reactionTimes);
        int max = Collections.max(reactionTimes);
        binSize = (double) (max - min) / nBins;
        int[] bins = new int[nBins];
        int curBin = 0;
        for (int rt : reactionTimes) {
            if (rt <= min + binSize * (curBin + 1)) {
                bins[curBin]++;
            } else {
                curBin++;
                bins[curBin]++;
            }
        }
        return bins;
    }

    /**
     * Calculates the percentage correct response rates for each condition.
     * @return A map of condition and its response rate.
     */
    public Map<String, Integer> calculateResponseRates() {
        Map<String, Integer> corrects = new HashMap<>();
        Map<String, Integer> wrongs = new HashMap<>();
        Map<String, Integer> rates = new HashMap<>();
        List<Trial> trials = data.getTrials();
        ExperimentInfo info = data.getInfo();
        for (String condition : info.getConditions()) {
            corrects.put(condition, 0);
            wrongs.put(condition, 0);
        }
        for (Trial trial : trials) {
            String condition = trial.getCondition();
            if (!corrects.containsKey(condition)) {
                continue;
            }
            if (trial.isCorrect()) {
                corrects.put(condition, corrects.get(condition) + 1);
            } else {
                wrongs.put(condition, wrongs.get(condition) + 1);
            }
        }
        for (String condition : info.getConditions()) {
            int sumOfTrials = corrects.get(condition) + wrongs.get(condition);
            int condRate = 0;
            if (sumOfTrials > 0) {
                condRate = (corrects.get(condition) * 100) / sumOfTrials;
            }
            rates.put(condition, condRate);
        }
        return rates;
    }

    /**
     * Separates trials according to condition.
     * @return Map of conditions as keys to their respective trials.
     */
    public Map<String, List<Trial>> reactionTimesForConditions() {
        Map<String, List<Trial>> condRts = new HashMap<>();
        List<Trial> trials = data.getTrials();
        ExperimentInfo info = data.getInfo();
        for (String condition : info.getConditions()) {
            condRts.put(condition, new ArrayList<>());
        }
        for (Trial trial : trials) {
            String condition = trial.getCondition();
            if (condRts.containsKey(condition)) {
                condRts.get(condition).add(trial);
            }
        }
        return condRts;
    }

}
