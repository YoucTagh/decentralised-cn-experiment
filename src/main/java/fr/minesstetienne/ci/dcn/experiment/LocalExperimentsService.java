package fr.minesstetienne.ci.dcn.experiment;

import fr.minesstetienne.ci.dcn.dto.ExperimentData;
import fr.minesstetienne.ci.dcn.dto.ExperimentResult;
import fr.minesstetienne.ci.dcn.dto.ExperimentResultItem;
import fr.minesstetienne.ci.dcn.dto.ResponseSameAsDTO;
import fr.minesstetienne.ci.dcn.service.SameAsSearchService;
import fr.minesstetienne.ci.dcn.service.UtilService;
import fr.minesstetienne.ci.dcn.service.ValidationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * @author YoucTagh
 */

public class LocalExperimentsService {

    public static void lunchAndExportExperiments() {
        ExperimentData experimentData = Util.importExperimentData();
        if (experimentData == null) {
            return;
        }
        ExperimentResult experimentResult = new ExperimentResult();
//        experimentResult = oneHTMLRepresentation(experimentData, experimentResult);
//        experimentResult = oneRDFRepresentation(experimentData,experimentResult);
        experimentResult = oneRDFValidSHACLRepresentation(experimentData, experimentResult);
        Util.exportExperimentResult(experimentResult);

    }

    private static ExperimentResult oneHTMLRepresentation(ExperimentData experimentData, ExperimentResult experimentResult) {
        return experimentResult
                .addToAnHTMLRepresentation(oneHTMLRepresentation(experimentData.getData_1_5()))
                .addToAnHTMLRepresentation(oneHTMLRepresentation(experimentData.getData_10_15()))
                .addToAnHTMLRepresentation(oneHTMLRepresentation(experimentData.getData_25_30()))
                .addToAnHTMLRepresentation(oneHTMLRepresentation(experimentData.getData_45_50()))
                .addToAnHTMLRepresentation(oneHTMLRepresentation(experimentData.getData_70_75()))
                .addToAnHTMLRepresentation(oneHTMLRepresentation(experimentData.getData_100()))
                ;
    }

    private static ExperimentResultItem oneHTMLRepresentation(List<ResponseSameAsDTO> data) {

        int numberOfRepresentationSelectedURI = 0;
        int numberOfRepresentationSameAsURI = 0;
        Random random = new Random(15);
        List<MediaType> htmlMediaTypes = List.of(MediaType.TEXT_HTML, MediaType.APPLICATION_XHTML_XML);

        long startTime = System.currentTimeMillis();

        boolean alreadyFoundAnswer;
        for (ResponseSameAsDTO responseSameAsDTO : data) {

            // Delete the wikidata IRI to have more accurate answers
            responseSameAsDTO.getDuplicates().remove(responseSameAsDTO.getUri());

            // Choose randomly an IRI to be our initial IRI
            String initialIRI = responseSameAsDTO.getDuplicates().remove(random.nextInt(responseSameAsDTO.getDuplicates().size()));

            // Check Its availability
            alreadyFoundAnswer = SameAsSearchService.checkRepresentationAvailability(initialIRI, htmlMediaTypes);
            if (alreadyFoundAnswer) {
//                System.out.println("Found in initial IRI => " + initialIRI);
                numberOfRepresentationSelectedURI++;
            }

            for (int j = 0; !alreadyFoundAnswer && j < responseSameAsDTO.getDuplicates().size(); j++) {
                String sameAsURI = responseSameAsDTO.getDuplicates().get(j);

                alreadyFoundAnswer = SameAsSearchService.checkRepresentationAvailability(sameAsURI, htmlMediaTypes);

                if (alreadyFoundAnswer) {
//                    System.out.println(initialIRI + " => " + sameAsURI);
                    numberOfRepresentationSameAsURI++;
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

    private static ExperimentResult oneRDFRepresentation(ExperimentData experimentData, ExperimentResult experimentResult) {
        return experimentResult
                .addToAnRDFRepresentation(oneRDFRepresentation(experimentData.getData_1_5()))
                .addToAnRDFRepresentation(oneRDFRepresentation(experimentData.getData_10_15()))
                .addToAnRDFRepresentation(oneRDFRepresentation(experimentData.getData_25_30()))
                .addToAnRDFRepresentation(oneRDFRepresentation(experimentData.getData_45_50()))
                .addToAnRDFRepresentation(oneRDFRepresentation(experimentData.getData_70_75()))
                .addToAnRDFRepresentation(oneRDFRepresentation(experimentData.getData_100()))
                ;
    }

    private static ExperimentResultItem oneRDFRepresentation(List<ResponseSameAsDTO> data) {

        int numberOfRepresentationSelectedURI = 0;
        int numberOfRepresentationSameAsURI = 0;
        Random random = new Random(15);
        List<MediaType> rdfMediaTypes = UtilService.getSemanticAcceptedMediaTypes();

        long startTime = System.currentTimeMillis();

        boolean alreadyFoundAnswer;
        for (ResponseSameAsDTO responseSameAsDTO : data) {

            // Delete the wikidata IRI to have more accurate answers
            responseSameAsDTO.getDuplicates().remove(responseSameAsDTO.getUri());

            // Choose randomly an IRI to be our initial IRI
            String initialIRI = responseSameAsDTO.getDuplicates().remove(random.nextInt(responseSameAsDTO.getDuplicates().size()));

            // Check Its availability
            alreadyFoundAnswer = SameAsSearchService.checkRepresentationAvailability(initialIRI, rdfMediaTypes);
            if (alreadyFoundAnswer) {
//                System.out.println("Found in initial IRI => " + initialIRI);
                numberOfRepresentationSelectedURI++;
            }

            for (int j = 0; !alreadyFoundAnswer && j < responseSameAsDTO.getDuplicates().size(); j++) {
                String sameAsURI = responseSameAsDTO.getDuplicates().get(j);

                alreadyFoundAnswer = SameAsSearchService.checkRepresentationAvailability(sameAsURI, rdfMediaTypes);

                if (alreadyFoundAnswer) {
//                    System.out.println(initialIRI + " => " + sameAsURI);
                    numberOfRepresentationSameAsURI++;
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

    private static ExperimentResult oneRDFValidSHACLRepresentation(ExperimentData experimentData, ExperimentResult experimentResult) {
        return experimentResult
                .addToRDFValidSHACLRepresentation(oneRDFValidSHACLRepresentation(experimentData.getData_1_5()))
                .addToRDFValidSHACLRepresentation(oneRDFValidSHACLRepresentation(experimentData.getData_10_15()))
                .addToRDFValidSHACLRepresentation(oneRDFValidSHACLRepresentation(experimentData.getData_25_30()))
                .addToRDFValidSHACLRepresentation(oneRDFValidSHACLRepresentation(experimentData.getData_45_50()))
                .addToRDFValidSHACLRepresentation(oneRDFValidSHACLRepresentation(experimentData.getData_70_75()))
                .addToRDFValidSHACLRepresentation(oneRDFValidSHACLRepresentation(experimentData.getData_100()))
                ;
    }

    private static ExperimentResultItem oneRDFValidSHACLRepresentation(List<ResponseSameAsDTO> data) {

        int numberOfRepresentationSelectedURI = 0;
        int numberOfRepresentationSameAsURI = 0;
        Random random = new Random(15);
        List<MediaType> rdfMediaTypes = UtilService.getSemanticAcceptedMediaTypes();

        long startTime = System.currentTimeMillis();

        boolean alreadyFoundAnswer = false;
        for (ResponseSameAsDTO responseSameAsDTO : data) {

            // Delete the wikidata IRI to have more accurate answers
            responseSameAsDTO.getDuplicates().remove(responseSameAsDTO.getUri());

            // Choose randomly an IRI to be our initial IRI
            String initialIRI = responseSameAsDTO.getDuplicates().remove(random.nextInt(responseSameAsDTO.getDuplicates().size()));

            // Check Its availability
            ResponseEntity responseEntity = SameAsSearchService.checkRepresentationValidity(initialIRI, rdfMediaTypes);
            if (responseEntity != null) {
                alreadyFoundAnswer = ValidationService.isRepresentationValid((String) responseEntity.getBody(), "./profiles/example-shape-graph-1.ttl");
                if (alreadyFoundAnswer) {
//                System.out.println("Found in initial IRI => " + initialIRI);
                    numberOfRepresentationSelectedURI++;
                }
            }
            for (int j = 0; !alreadyFoundAnswer && j < responseSameAsDTO.getDuplicates().size(); j++) {
                String sameAsURI = responseSameAsDTO.getDuplicates().get(j);

                responseEntity = SameAsSearchService.checkRepresentationValidity(sameAsURI, rdfMediaTypes);
                if (responseEntity != null) {
                    alreadyFoundAnswer = ValidationService.isRepresentationValid((String) responseEntity.getBody(), "./profiles/example-shape-graph-1.ttl");
                    if (alreadyFoundAnswer) {
//                    System.out.println(initialIRI + " => " + sameAsURI);
                        numberOfRepresentationSameAsURI++;
                    }
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
