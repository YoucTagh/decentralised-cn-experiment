package fr.minesstetienne.ci.dcn.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YoucTagh
 */
public class ExperimentResult {
    private final List<ExperimentResultItem> anHTMLRepresentation;
    private final List<ExperimentResultItem> anRDFRepresentation;
    private final List<ExperimentResultItem> anRDFValidSHACLRepresentation;

    public ExperimentResult() {
        anHTMLRepresentation = new ArrayList<>();
        anRDFRepresentation = new ArrayList<>();
        anRDFValidSHACLRepresentation = new ArrayList<>();
    }

    public ExperimentResult addToAnHTMLRepresentation(ExperimentResultItem resultItem) {
        this.anHTMLRepresentation.add(resultItem);
        return this;
    }

    public ExperimentResult addToAnRDFRepresentation(ExperimentResultItem resultItem) {
        this.anRDFRepresentation.add(resultItem);
        return this;
    }

    public ExperimentResult addToRDFValidSHACLRepresentation(ExperimentResultItem resultItem) {
        this.anRDFValidSHACLRepresentation.add(resultItem);
        return this;
    }

    public List<ExperimentResultItem> getAnHTMLRepresentation() {
        return anHTMLRepresentation;
    }

    public List<ExperimentResultItem> getAnRDFRepresentation() {
        return anRDFRepresentation;
    }

    public List<ExperimentResultItem> getAnRDFValidSHACLRepresentation() {
        return anRDFValidSHACLRepresentation;
    }
}
