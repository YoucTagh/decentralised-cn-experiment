package fr.minesstetienne.ci.dcn.experiment;

import fr.minesstetienne.ci.dcn.dto.ExperimentResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author YoucTagh
 */
public class ExportResultHTML {

    private final static String templatePath = "/export-html-template/template.html";

    public static void exportExperimentResultsToHTML() {
        ExperimentResult result = Util.importExperimentResult();
        try {
            Stream<String> stream = Files.lines(Paths.get(ExportResultHTML.class.getResource(templatePath).toURI()));
            StringBuilder sb = new StringBuilder();
            stream.forEach(str -> sb.append(str).append("\n"));
            for (int i = 0; i < result.getAnHTMLRepresentation().size(); i++) {

                // Replace HTML cells
                int startIndex = sb.indexOf("${html-selected_" + i + "}");
                sb.replace(startIndex, startIndex + 18, String.valueOf(result.getAnHTMLRepresentation().get(i).getNumberOfRepresentationSelectedURI()));

                startIndex = sb.indexOf("${html-sameas_" + i + "}");
                sb.replace(startIndex, startIndex + 16, String.valueOf(result.getAnHTMLRepresentation().get(i).getNumberOfRepresentationSameAsURI()));

                startIndex = sb.indexOf("${html-average_" + i + "}");
                sb.replace(startIndex, startIndex + 17, result.getAnHTMLRepresentation().get(i).getAverageTimeOfExecution() + " ms");

                // Replace RDF cells
                startIndex = sb.indexOf("${rdf-selected_" + i + "}");
                sb.replace(startIndex, startIndex + 17, String.valueOf(result.getAnRDFRepresentation().get(i).getNumberOfRepresentationSelectedURI()));

                startIndex = sb.indexOf("${rdf-sameas_" + i + "}");
                sb.replace(startIndex, startIndex + 15, String.valueOf(result.getAnRDFRepresentation().get(i).getNumberOfRepresentationSameAsURI()));

                startIndex = sb.indexOf("${rdf-average_" + i + "}");
                sb.replace(startIndex, startIndex + 16, result.getAnRDFRepresentation().get(i).getAverageTimeOfExecution() + " ms");

                // Replace SHACL cells
                startIndex = sb.indexOf("${shacl-selected_" + i + "}");
                sb.replace(startIndex, startIndex + 19, String.valueOf(result.getAnRDFValidSHACLRepresentation().get(i).getNumberOfRepresentationSelectedURI()));

                startIndex = sb.indexOf("${shacl-sameas_" + i + "}");
                sb.replace(startIndex, startIndex + 17, String.valueOf(result.getAnRDFValidSHACLRepresentation().get(i).getNumberOfRepresentationSameAsURI()));

                startIndex = sb.indexOf("${shacl-average_" + i + "}");
                sb.replace(startIndex, startIndex + 18, result.getAnRDFValidSHACLRepresentation().get(i).getAverageTimeOfExecution() + " ms");
            }

            writeResultToFile(sb);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeResultToFile(StringBuilder data) {

        File file = null;
        FileWriter filewriter = null;
        try {
            file = new File("data/results.html");

            filewriter = new FileWriter(file);
            filewriter.write(data.toString());
            filewriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (filewriter != null) {
                    filewriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
