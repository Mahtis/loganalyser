package ohha.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ohha.domain.SubjectData;
import ohha.domain.Trial;

/**
 * Class to analyse data to draw a histogram (IN DEVELOPMENT).
 * 
 * The class is still very much in development, so many things are still not done.
 * 
 * @author mikkotiainen
 */
public class AnalyseData {

    private SubjectData data;
    private int nBins;
    private int binSize;
    private List<Integer> reactionTimes;

    public AnalyseData(SubjectData data) {
        this.data = data;
        reactionTimes = getReactionTimes(0);
    }

    public List<Integer> getReactionTimes(int i) {
        reactionTimes = new ArrayList<>();
        for (Trial trial : data.getTrials()) {
            int rt = trial.getReactionTimes().get(i);
            reactionTimes.add(rt);
        }
        return reactionTimes;
    }

    public int[] calculateHistogramValues() {
        Collections.sort(reactionTimes);
        nBins = (int) Math.sqrt(reactionTimes.size());
        int min = Collections.min(reactionTimes);
        int max = Collections.max(reactionTimes);
        binSize = (max - min) / nBins;
        int[] bins = new int[nBins];
        int curBin = 0;
        System.out.println(min + ", " + max);
        for(int i : reactionTimes) {
            bins[curBin]++;
            if (i >= min + binSize * curBin) {
                //System.out.println(bins[curBin]);
                curBin++;
            }
        }
        return bins;
    }

}
