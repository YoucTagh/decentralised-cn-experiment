package fr.minesstetienne.ci.dcn.dto;

/**
 * @author YoucTagh
 */
public class ExperimentResultItem {
    private int numberOfRepresentationSelectedURI;
    private int numberOfRepresentationSameAsURI;
    private long averageTimeOfExecution;

    public int getNumberOfRepresentationSelectedURI() {
        return numberOfRepresentationSelectedURI;
    }

    public ExperimentResultItem setNumberOfRepresentationSelectedURI(int numberOfRepresentationSelectedURI) {
        this.numberOfRepresentationSelectedURI = numberOfRepresentationSelectedURI;
        return this;
    }

    public int getNumberOfRepresentationSameAsURI() {
        return numberOfRepresentationSameAsURI;
    }

    public ExperimentResultItem setNumberOfRepresentationSameAsURI(int numberOfRepresentationSameAsURI) {
        this.numberOfRepresentationSameAsURI = numberOfRepresentationSameAsURI;
        return this;
    }

    public long getAverageTimeOfExecution() {
        return averageTimeOfExecution;
    }

    public ExperimentResultItem setAverageTimeOfExecution(long averageTimeOfExecution) {
        this.averageTimeOfExecution = averageTimeOfExecution;
        return this;
    }
}
