package ohha.domain;

import java.util.List;

/**
 * Class to hold data of a single subject for a specific Experiment.
 * @author mikkotiainen
 */
public class SubjectData {
    private ExperimentInfo info;
    private List<Trial> trials;
    private String subject;

    /**
     * Create a new SubjectInfo object with the given ExperimentInfo and List of Trials.
     * @param info ExperimentInfo for which the data is for.
     * @param trials List of trials for one subject.
     */
    public SubjectData(ExperimentInfo info, List<Trial> trials) {
        this.info = info;
        this.trials = trials;
        if (trials != null && !trials.isEmpty()) {
            this.subject = trials.get(0).getSubject();
        } else {
            this.subject = "none";
        }
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    @Override
    public String toString() {
        return subject + "_" + info.getName() + "_data";
    }
    
    
}
