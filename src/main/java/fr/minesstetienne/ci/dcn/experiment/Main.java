package fr.minesstetienne.ci.dcn.experiment;

import fr.minesstetienne.ci.dcn.dto.ExperimentData;

import java.util.Random;

/**
 * @author YoucTagh
 */
public class Main {

    public static void scrapAndExportData(){
        ExperimentData experimentData = DataScrapingService.scrapData();
        boolean isExported = Util.exportExperimentData(experimentData);
        if (isExported) {
            System.out.println("Export with success!");
        }
    }

    public static void lunchLocalExperiments(){
        LocalExperimentsService.lunchAndExportExperiments();
    }

    public static void main(String[] args) {
        lunchLocalExperiments();
    }
}
