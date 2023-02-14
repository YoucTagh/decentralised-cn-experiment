package fr.minesstetienne.ci.dcn.dto;

import java.util.List;

/**
 * @author YoucTagh
 */
public class ResponseSameAsDTO {

    private String uri;
    private String numDuplicates;
    private List<String> duplicates;

    public String getUri() {
        return uri;
    }

    public ResponseSameAsDTO setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getNumDuplicates() {
        return numDuplicates;
    }

    public ResponseSameAsDTO setNumDuplicates(String numDuplicates) {
        this.numDuplicates = numDuplicates;
        return this;
    }

    public List<String> getDuplicates() {
        return duplicates;
    }

    public ResponseSameAsDTO setDuplicates(List<String> duplicates) {
        this.duplicates = duplicates;
        return this;
    }

    @Override
    public String toString() {
        return "ResponseSameAsDTO{" +
                "uri='" + uri + '\'' +
                ", numDuplicates='" + numDuplicates + '\'' +
                ", duplicates=" + duplicates +
                '}';
    }
}


