package fr.minesstetienne.ci.dcn.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YoucTagh
 */
public class ExperimentData {
    private final List<ResponseSameAsDTO> data_1_5;
    private final List<ResponseSameAsDTO> data_10_15;
    private final List<ResponseSameAsDTO> data_25_30;
    private final List<ResponseSameAsDTO> data_45_50;
    private final List<ResponseSameAsDTO> data_70_75;
    private final List<ResponseSameAsDTO> data_100;

    public ExperimentData() {
        this.data_1_5 = new ArrayList<>();
        this.data_10_15 = new ArrayList<>();
        this.data_25_30 = new ArrayList<>();
        this.data_45_50 = new ArrayList<>();
        this.data_70_75 = new ArrayList<>();
        this.data_100 = new ArrayList<>();
    }

    public ExperimentData addToData_1_5(ResponseSameAsDTO sameAsData) {
        this.data_1_5.add(sameAsData);
        return this;
    }

    public ExperimentData addToData_10_15(ResponseSameAsDTO sameAsData) {
        this.data_10_15.add(sameAsData);
        return this;
    }

    public ExperimentData addToData_25_30(ResponseSameAsDTO sameAsData) {
        this.data_25_30.add(sameAsData);
        return this;
    }

    public ExperimentData addToData_45_50(ResponseSameAsDTO sameAsData) {
        this.data_45_50.add(sameAsData);
        return this;
    }

    public ExperimentData addToData_70_75(ResponseSameAsDTO sameAsData) {
        this.data_70_75.add(sameAsData);
        return this;
    }

    public ExperimentData addToData_100(ResponseSameAsDTO sameAsData) {
        this.data_100.add(sameAsData);
        return this;
    }

    public List<ResponseSameAsDTO> getData_1_5() {
        return data_1_5;
    }

    public List<ResponseSameAsDTO> getData_10_15() {
        return data_10_15;
    }

    public List<ResponseSameAsDTO> getData_25_30() {
        return data_25_30;
    }

    public List<ResponseSameAsDTO> getData_45_50() {
        return data_45_50;
    }

    public List<ResponseSameAsDTO> getData_70_75() {
        return data_70_75;
    }

    public List<ResponseSameAsDTO> getData_100() {
        return data_100;
    }

    public void printContent() {
        System.out.println("Data [1-5]:");
        data_1_5.forEach(System.out::println);
        System.out.println("Data [10-15]:");
        data_10_15.forEach(System.out::println);
        System.out.println("Data [25-30]:");
        data_25_30.forEach(System.out::println);
        System.out.println("Data [45-50]:");
        data_45_50.forEach(System.out::println);
        System.out.println("Data [70-75]:");
        data_70_75.forEach(System.out::println);
        System.out.println("Data [100]:");
        data_100.forEach(System.out::println);
    }

    public void printListsSize() {
        System.out.print("Sizes: [1-5] = " + data_1_5.size());
        System.out.print(" | [10-15] = " + data_10_15.size());
        System.out.print(" | [25-30] = " + data_25_30.size());
        System.out.print(" | [45-50] = " + data_45_50.size());
        System.out.print(" | [70-75] = " + data_70_75.size());
        System.out.println(" | [100] = " + data_100.size());
    }
}
