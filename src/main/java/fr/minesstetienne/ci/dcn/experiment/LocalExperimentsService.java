package fr.minesstetienne.ci.dcn.experiment;

import fr.minesstetienne.ci.dcn.dto.ExperimentData;
import fr.minesstetienne.ci.dcn.dto.ExperimentResult;
import fr.minesstetienne.ci.dcn.dto.ExperimentResultItem;
import fr.minesstetienne.ci.dcn.dto.ResponseSameAsDTO;

import java.util.List;
import java.util.Random;

/**
 * @author YoucTagh
 */

class LocalExperimentsService {

    public static void lunchAndExportExperiments() {
        ExperimentData experimentData = Util.importExperimentData();
        if (experimentData == null) {
            return;
        }
        ExperimentResult experimentResult = new ExperimentResult();
        experimentResult = oneHTMLRepresentation(experimentData, experimentResult);
        Util.exportExperimentResult(experimentResult);

    }

    private static ExperimentResult oneHTMLRepresentation(ExperimentData experimentData, ExperimentResult experimentResult) {
        return experimentResult
                .addToAnHTMLRepresentation(oneHTMLRepresentation(experimentData.getData_1_5()))
                .addToAnHTMLRepresentation(oneHTMLRepresentation(experimentData.getData_10_15()))
                .addToAnHTMLRepresentation(oneHTMLRepresentation(experimentData.getData_25_30()))
                .addToAnHTMLRepresentation(oneHTMLRepresentation(experimentData.getData_45_50()))
                .addToAnHTMLRepresentation(oneHTMLRepresentation(experimentData.getData_70_75()))
                .addToAnHTMLRepresentation(oneHTMLRepresentation(experimentData.getData_100()));
    }

    private static ExperimentResultItem oneHTMLRepresentation(List<ResponseSameAsDTO> data) {

        int numberOfRepresentationSelectedURI = 0;
        int numberOfRepresentationSameAsURI = 0;
        Random random = new Random();
        long startTime = System.currentTimeMillis();

        boolean alreadyFoundAnswer;
        for (ResponseSameAsDTO responseSameAsDTO : data) {
            alreadyFoundAnswer = false;
            for (int j = 0; !alreadyFoundAnswer && j < responseSameAsDTO.getDuplicates().size() - 1; j++) {
                String sameAsURI = responseSameAsDTO.getDuplicates().get(j);
                if (random.nextBoolean()) {
                    numberOfRepresentationSelectedURI++;
                    alreadyFoundAnswer = true;
                } else if (random.nextBoolean()) {
                    numberOfRepresentationSameAsURI++;
                    alreadyFoundAnswer = true;
                }
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long experimentTime = endTime - startTime;
        return new ExperimentResultItem()
                .setNumberOfRepresentationSelectedURI(numberOfRepresentationSelectedURI)
                .setNumberOfRepresentationSameAsURI(numberOfRepresentationSameAsURI)
                .setAverageTimeOfExecution(experimentTime / data.size());
    }


}
