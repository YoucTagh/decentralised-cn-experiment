package fr.minesstetienne.ci.dcn.experiment;

/**
 * @author YoucTagh
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.minesstetienne.ci.dcn.dto.ExperimentData;
import fr.minesstetienne.ci.dcn.dto.ExperimentResult;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author YoucTagh
 */
@Component
public class Util {

    public static boolean exportExperimentData(ExperimentData experimentData) {
        try {
            Files.createDirectories(Paths.get("data/"));
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("data/experiment-data.json"), experimentData);
            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static ExperimentData importExperimentData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File("data/experiment-data.json"), ExperimentData.class);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }


    public static boolean exportExperimentResult(ExperimentResult experimentResult) {
        try {
            Files.createDirectories(Paths.get("data/"));
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("data/experiment-result.json"), experimentResult);
            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static ExperimentResult importExperimentResult() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File("data/experiment-result.json"), ExperimentResult.class);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }


}