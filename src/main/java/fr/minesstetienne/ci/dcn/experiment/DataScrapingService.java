package fr.minesstetienne.ci.dcn.experiment;

import fr.minesstetienne.ci.dcn.dto.ExperimentData;
import fr.minesstetienne.ci.dcn.dto.ResponseSameAsDTO;
import fr.minesstetienne.ci.dcn.service.SameAsSearchService;

import java.util.Random;

/**
 * @author YoucTagh
 */

public class DataScrapingService {
    public static ExperimentData scrapData() {
        ExperimentData experimentData = new ExperimentData();

        String baseURI = "http://www.wikidata.org/entity/Q";

        final int SAME_AS_URIS_PER_SET = 100;
        final int MAX_IDENTIFIER_TO_CHECK = 4700;
        int currentIdentifier = 1;

        int sleepTime;
        boolean isSetsFull = false;
        for (; currentIdentifier <= MAX_IDENTIFIER_TO_CHECK && !isSetsFull; currentIdentifier++) {

            System.out.println("Checking: " + baseURI + currentIdentifier);
            ResponseSameAsDTO sameResources = SameAsSearchService.findSameResources(baseURI + currentIdentifier);
            if (sameResources != null) {
                int numDuplicates = Integer.parseInt(sameResources.getNumDuplicates()) - 1;
                if (numDuplicates >= 1 && numDuplicates <= 5 && experimentData.getData_1_5().size() < SAME_AS_URIS_PER_SET) {
                    experimentData.addToData_1_5(sameResources);
                } else if (numDuplicates >= 10 && numDuplicates <= 15 && experimentData.getData_10_15().size() < SAME_AS_URIS_PER_SET) {
                    experimentData.addToData_10_15(sameResources);
                } else if (numDuplicates >= 25 && numDuplicates <= 30 && experimentData.getData_25_30().size() < SAME_AS_URIS_PER_SET) {
                    experimentData.addToData_25_30(sameResources);
                } else if (numDuplicates >= 45 && numDuplicates <= 50 && experimentData.getData_45_50().size() < SAME_AS_URIS_PER_SET) {
                    experimentData.addToData_45_50(sameResources);
                } else if (numDuplicates >= 70 && numDuplicates <= 75 && experimentData.getData_70_75().size() < SAME_AS_URIS_PER_SET) {
                    experimentData.addToData_70_75(sameResources);
                } else if (numDuplicates >= 100 && experimentData.getData_100().size() < SAME_AS_URIS_PER_SET) {
                    experimentData.addToData_100(sameResources);
                }
                experimentData.printListsSize();

                if (experimentData.getData_1_5().size() >= SAME_AS_URIS_PER_SET &&
                        experimentData.getData_10_15().size() >= SAME_AS_URIS_PER_SET &&
                        experimentData.getData_25_30().size() >= SAME_AS_URIS_PER_SET &&
                        experimentData.getData_45_50().size() >= SAME_AS_URIS_PER_SET &&
                        experimentData.getData_70_75().size() >= SAME_AS_URIS_PER_SET &&
                        experimentData.getData_100().size() >= SAME_AS_URIS_PER_SET) {
                    isSetsFull = true;
                }
            }


            sleepTime = new Random().nextInt(1000);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }

        }
        return experimentData;
    }
}
