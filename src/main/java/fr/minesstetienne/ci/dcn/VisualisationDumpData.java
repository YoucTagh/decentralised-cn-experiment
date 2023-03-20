package fr.minesstetienne.ci.dcn;

import fr.minesstetienne.ci.dcn.dto.DumpExperimentData;
import fr.minesstetienne.ci.dcn.experiment.Util;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YoucTagh
 */
public class VisualisationDumpData {


    public static void showLineChart(List<Map.Entry<Long, Long>> sortedMapList) {
        VBox vBox = new VBox();

        //Defining x axis
        NumberAxis xAxis = new NumberAxis(150, 2020, 100);
//        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("No.of Equivalence Links");

        //Defining y axis
        NumberAxis yAxis = new NumberAxis(0, 18, 1);
//        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("No.of URIs");

        LineChart linechart = new LineChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        series.setName("No.of URIs having an Equivalence Links number");

        sortedMapList.forEach(entry -> {
            series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        });


        linechart.getData().add(series);


        vBox.getChildren().add(linechart);

        Scene scene = new Scene(vBox);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMinHeight(600);
        stage.setMinWidth(800);
        stage.setTitle("Data Analytics");
        stage.show();
    }

    private static void showLineChart(HashMap<String, Long> adequateRange) {
        VBox vBox = new VBox();

        //Defining x axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("No.of Equivalence Links");

        //Defining y axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("No.of URIs");

        LineChart linechart = new LineChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        series.setName("No.of URIs having an Equivalence Links number");


        series.getData().add(new XYChart.Data("0", adequateRange.get("0")));
        series.getData().add(new XYChart.Data("[1-10]", adequateRange.get("[1-10]")));
        series.getData().add(new XYChart.Data("[11-20]", adequateRange.get("[11-20]")));
        series.getData().add(new XYChart.Data("[21-30]", adequateRange.get("[21-30]")));
        series.getData().add(new XYChart.Data("[31-40]", adequateRange.get("[31-40]")));
        series.getData().add(new XYChart.Data("[41-50]", adequateRange.get("[41-50]")));
        series.getData().add(new XYChart.Data("[51-60]", adequateRange.get("[51-60]")));
        series.getData().add(new XYChart.Data("[61-70]", adequateRange.get("[61-70]")));
        series.getData().add(new XYChart.Data("[71-80]", adequateRange.get("[71-80]")));
        series.getData().add(new XYChart.Data("[81-90]", adequateRange.get("[81-90]")));
        series.getData().add(new XYChart.Data("[91-100]", adequateRange.get("[91-100]")));
        series.getData().add(new XYChart.Data("[101-125]", adequateRange.get("[101-125]")));
        series.getData().add(new XYChart.Data("[126-150]", adequateRange.get("[126-150]")));
        series.getData().add(new XYChart.Data("[151-500]", adequateRange.get("[151-500]")));
        series.getData().add(new XYChart.Data("+500", adequateRange.get("+500")));


        Long sum = 0L;
        for (Long count : adequateRange.values()) {
            sum += count;
        }
        System.out.println(sum);
        linechart.getData().add(series);

        vBox.setStyle("-fx-background-color: transparent;");
        linechart.setStyle("-fx-background-color: transparent;");


        linechart.prefHeightProperty().bind(vBox.heightProperty());
        vBox.getChildren().add(linechart);

        Scene scene = new Scene(vBox);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMinHeight(600);
        stage.setMinWidth(800);
        stage.setTitle("Data Analytics");
        stage.show();
    }

    static void printData(HashMap<String, Long> adequateRange) {
        System.out.println("0," + adequateRange.get("0"));
        System.out.println("[1-10]," + adequateRange.get("[1-10]"));
        System.out.println("[11-20]," + adequateRange.get("[11-20]"));
        System.out.println("[21-30]," + adequateRange.get("[21-30]"));
        System.out.println("[31-40]," + adequateRange.get("[31-40]"));
        System.out.println("[41-50]," + adequateRange.get("[41-50]"));
        System.out.println("[51-60]," + adequateRange.get("[51-60]"));
        System.out.println("[61-70]," + adequateRange.get("[61-70]"));
        System.out.println("[71-80]," + adequateRange.get("[71-80]"));
        System.out.println("[81-90]," + adequateRange.get("[81-90]"));
        System.out.println("[91-100]," + adequateRange.get("[91-100]"));
        System.out.println("[101-125]," + adequateRange.get("[101-125]"));
        System.out.println("[126-150]," + adequateRange.get("[126-150]"));
        System.out.println("[151-500]," + adequateRange.get("[151-500]"));
        System.out.println("+500," + adequateRange.get("+500"));


    }

    static HashMap<String, Long> getAdequateRange(HashMap<Long, Long> resultMap) {
        HashMap<String, Long> result = new HashMap<>();


        resultMap.forEach((key, value) -> {
            if (key == 0) {
                Long currentNumberOfURIs = result.getOrDefault("0", 0L);
                currentNumberOfURIs += value;
                result.put("0", currentNumberOfURIs);
            } else if (key <= 10) {
                Long currentNumberOfURIs = result.getOrDefault("[1-10]", 0L);
                currentNumberOfURIs += value;
                result.put("[1-10]", currentNumberOfURIs);
            } else if (key <= 20) {
                Long currentNumberOfURIs = result.getOrDefault("[11-20]", 0L);
                currentNumberOfURIs += value;
                result.put("[11-20]", currentNumberOfURIs);
            } else if (key <= 30) {
                Long currentNumberOfURIs = result.getOrDefault("[21-30]", 0L);
                currentNumberOfURIs += value;
                result.put("[21-30]", currentNumberOfURIs);
            } else if (key <= 40) {
                Long currentNumberOfURIs = result.getOrDefault("[31-40]", 0L);
                currentNumberOfURIs += value;
                result.put("[31-40]", currentNumberOfURIs);
            } else if (key <= 50) {
                Long currentNumberOfURIs = result.getOrDefault("[41-50]", 0L);
                currentNumberOfURIs += value;
                result.put("[41-50]", currentNumberOfURIs);
            } else if (key <= 60) {
                Long currentNumberOfURIs = result.getOrDefault("[51-60]", 0L);
                currentNumberOfURIs += value;
                result.put("[51-60]", currentNumberOfURIs);
            } else if (key <= 70) {
                Long currentNumberOfURIs = result.getOrDefault("[61-70]", 0L);
                currentNumberOfURIs += value;
                result.put("[61-70]", currentNumberOfURIs);
            } else if (key <= 80) {
                Long currentNumberOfURIs = result.getOrDefault("[71-80]", 0L);
                currentNumberOfURIs += value;
                result.put("[71-80]", currentNumberOfURIs);
            } else if (key <= 90) {
                Long currentNumberOfURIs = result.getOrDefault("[81-90]", 0L);
                currentNumberOfURIs += value;
                result.put("[81-90]", currentNumberOfURIs);
            } else if (key <= 100) {
                Long currentNumberOfURIs = result.getOrDefault("[91-100]", 0L);
                currentNumberOfURIs += value;
                result.put("[91-100]", currentNumberOfURIs);
            } else if (key <= 125) {
                Long currentNumberOfURIs = result.getOrDefault("[101-125]", 0L);
                currentNumberOfURIs += value;
                result.put("[101-125]", currentNumberOfURIs);
            } else if (key <= 150) {
                Long currentNumberOfURIs = result.getOrDefault("[126-150]", 0L);
                currentNumberOfURIs += value;
                result.put("[126-150]", currentNumberOfURIs);
            } else if (key <= 500) {
                Long currentNumberOfURIs = result.getOrDefault("[151-500]", 0L);
                currentNumberOfURIs += value;
                result.put("[151-500]", currentNumberOfURIs);
            } else {
                Long currentNumberOfURIs = result.getOrDefault("+500", 0L);
                currentNumberOfURIs += value;
                result.put("+500", currentNumberOfURIs);
            }
        });

        return result;
    }


    public static void main(String[] args) {
        DumpExperimentData dumpExperimentData = Util.importDumpExperimentData();

        HashMap<Long, Long> resultMap = new HashMap<>();

        dumpExperimentData.getData_1_5000().forEach(responseSameAsDTO -> {
            long numberOfSameAsURIs = Long.parseLong(responseSameAsDTO.getNumDuplicates()) - 1;
            Long currentNumberOfURIs = resultMap.getOrDefault(numberOfSameAsURIs, 0L);
            currentNumberOfURIs++;
            resultMap.put(numberOfSameAsURIs, currentNumberOfURIs);
        });

        List<Map.Entry<Long, Long>> sortedMapList = resultMap.entrySet().stream().sorted(Comparator.comparingLong(Map.Entry::getKey)).collect(Collectors.toList());
//        Long sum = 0L;
//        for (Map.Entry<Long, Long> entry : resultMap.entrySet()){ //4992
//            sum += entry.getValue();
//            System.out.println(entry.getValue());
//        }
//        System.out.println(sum);

        HashMap<String, Long> adequateRange = getAdequateRange(resultMap);

        Platform.startup(() -> {
//            showLineChart(sortedMapList);
            showLineChart(adequateRange);
        });

//        printData(adequateRange);

        adequateRange.values().forEach(System.out::println);

    }

}
