package fr.minesstetienne.ci.dcn.experiment;

import fr.minesstetienne.ci.dcn.dto.ExperimentData;
import fr.minesstetienne.ci.dcn.dto.ResponseSameAsDTO;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author YoucTagh
 */
class DataScrapingServiceTest {
    @Test
    void scapData() {
        ExperimentData experimentData = new ExperimentData();
        experimentData
                .addToData_1_5(new ResponseSameAsDTO().setUri("http://1.com").setNumDuplicates("2").setDuplicates(Stream.of("uri1", "uri2").collect(Collectors.toList())))
                .addToData_1_5(new ResponseSameAsDTO().setUri("http://2.com").setNumDuplicates("1").setDuplicates(Stream.of("uri1").collect(Collectors.toList())))
                .addToData_1_5(new ResponseSameAsDTO().setUri("http://3.com").setNumDuplicates("3").setDuplicates(Stream.of("uri1", "uri2", "uri5").collect(Collectors.toList())));

        experimentData
                .addToData_10_15(new ResponseSameAsDTO().setUri("http://4.com").setNumDuplicates("2").setDuplicates(Stream.of("uri41", "uri42").collect(Collectors.toList())))
                .addToData_10_15(new ResponseSameAsDTO().setUri("http://5.com").setNumDuplicates("1").setDuplicates(Stream.of("uri51").collect(Collectors.toList())))
                .addToData_10_15(new ResponseSameAsDTO().setUri("http://6.com").setNumDuplicates("3").setDuplicates(Stream.of("uri61", "uri62", "uri65").collect(Collectors.toList())));

        experimentData
                .addToData_25_30(new ResponseSameAsDTO().setUri("http://7.com").setNumDuplicates("2").setDuplicates(Stream.of("uri71", "uri72").collect(Collectors.toList())))
                .addToData_25_30(new ResponseSameAsDTO().setUri("http://8.com").setNumDuplicates("1").setDuplicates(Stream.of("uri81").collect(Collectors.toList())))
                .addToData_25_30(new ResponseSameAsDTO().setUri("http://9.com").setNumDuplicates("3").setDuplicates(Stream.of("uri91", "uri92", "uri95").collect(Collectors.toList())));

        experimentData
                .addToData_45_50(new ResponseSameAsDTO().setUri("http://10.com").setNumDuplicates("2").setDuplicates(Stream.of("uri10", "uri20").collect(Collectors.toList())))
                .addToData_45_50(new ResponseSameAsDTO().setUri("http://11.com").setNumDuplicates("1").setDuplicates(Stream.of("uri1").collect(Collectors.toList())));

        experimentData.addToData_70_75(new ResponseSameAsDTO().setUri("http://15.com").setNumDuplicates("2").setDuplicates(Stream.of("uri14", "uri24").collect(Collectors.toList())));


        experimentData
                .addToData_100(new ResponseSameAsDTO().setUri("http://12.com").setNumDuplicates("2").setDuplicates(Stream.of("uri541", "ur54i2").collect(Collectors.toList())))
                .addToData_100(new ResponseSameAsDTO().setUri("http://22.com").setNumDuplicates("1").setDuplicates(Stream.of("uri112").collect(Collectors.toList())))
                .addToData_100(new ResponseSameAsDTO().setUri("http://33.com").setNumDuplicates("3").setDuplicates(Stream.of("uri121", "uri212", "uri215").collect(Collectors.toList())));

    }
}