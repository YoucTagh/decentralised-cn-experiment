package fr.minesstetienne.ci.dcn.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YoucTagh
 */
public class DumpExperimentData {
    private final List<ResponseSameAsDTO> data_1_5000;

    public DumpExperimentData() {
        this.data_1_5000 = new ArrayList<>();
    }

    public DumpExperimentData addToData_1_5000(ResponseSameAsDTO sameAsData) {
        this.data_1_5000.add(sameAsData);
        return this;
    }
    public List<ResponseSameAsDTO> getData_1_5000() {
        return data_1_5000;
    }
}
