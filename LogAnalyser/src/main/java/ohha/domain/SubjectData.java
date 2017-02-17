package ohha.domain;

import java.util.List;

/**
 * Class to hold data of a single subject for a specific Experiment.
 * @author mikkotiainen
 */
public class SubjectData {
    private ExperimentInfo info;
    private List<Trial> trials;

    public SubjectData(ExperimentInfo info, List<Trial> trials) {
        this.info = info;
        this.trials = trials;
    }
    

    public ExperimentInfo getInfo() {
        return info;
    }

    public void setInfo(ExperimentInfo info) {
        this.info = info;
    }

    public List<Trial> getTrials() {
        return trials;
    }

    public void setTrials(List<Trial> trials) {
        this.trials = trials;
    }
    
    
}
