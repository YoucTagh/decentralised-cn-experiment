package fr.minesstetienne.ci.dcn.experiment;

import fr.minesstetienne.ci.dcn.dto.DumpExperimentData;
import fr.minesstetienne.ci.dcn.dto.ResponseSameAsDTO;
import fr.minesstetienne.ci.dcn.service.SameAsSearchService;

import java.util.Random;

/**
 * @author YoucTagh
 */

public class DataDumpScrapingService {
    public static DumpExperimentData scrapData() {
        DumpExperimentData experimentData = new DumpExperimentData();

        String baseURI = "http://www.wikidata.org/entity/Q";

        final int MAX_IDENTIFIER_TO_CHECK = 5000;
        int currentIdentifier = 1;

        int sleepTime;
        for (; currentIdentifier <= MAX_IDENTIFIER_TO_CHECK; currentIdentifier++) {

            System.out.println("Checking: " + baseURI + currentIdentifier);
            ResponseSameAsDTO sameResources = SameAsSearchService.findSameResources(baseURI + currentIdentifier);
            if (sameResources != null) {
                experimentData.addToData_1_5000(sameResources);
            }

            sleepTime = new Random().nextInt(1000);
            try {
                Thread.sleep(sleepTime);
            } catch (
                    InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        return experimentData;
    }

}
