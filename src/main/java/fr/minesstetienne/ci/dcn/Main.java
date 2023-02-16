package fr.minesstetienne.ci.dcn;

import fr.minesstetienne.ci.dcn.dto.ExperimentData;
import fr.minesstetienne.ci.dcn.experiment.DataScrapingService;
import fr.minesstetienne.ci.dcn.experiment.ExportResultHTML;
import fr.minesstetienne.ci.dcn.experiment.LocalExperimentsService;
import fr.minesstetienne.ci.dcn.experiment.Util;

import java.util.Scanner;

/**
 * @author YoucTagh
 */
public class Main {

    public static void scrapAndExportData() {
        System.out.println("Start scraping and exporting the Data");
        ExperimentData experimentData = DataScrapingService.scrapData();
        boolean isExported = Util.exportExperimentData(experimentData);
        if (isExported) {
            System.out.println("Export with success!");
        }
        System.out.println("The data has been scraped and exported");
    }

    public static void lunchLocalExperiments() {
        System.out.println("Start the local experiments");
        LocalExperimentsService.lunchAndExportExperiments();
        System.out.println("The local experiments done");
    }

    public static void createAnHTMLOutput() {
        System.out.println("Start creating HTML output");
        ExportResultHTML.exportExperimentResultsToHTML();
        System.out.println("Creating HTML output done");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Please make a choice [0-3]:");
        System.out.println("1: Scrap and Export Data");
        System.out.println("2: Lunch the Local Experiment");
        System.out.println("3: Create an HTML output from the experiment results");
        System.out.println("0: Quit");
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println();
        System.out.println("Welcome to the Decentralised Content Negotiation Experiment Menu.");
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        do {
            printMenu();
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Wrong input");
                continue;
            }
            switch (choice) {
                case 1:
                    scrapAndExportData();
                    break;
                case 2:
                    lunchLocalExperiments();
                    break;
                case 3:
                    createAnHTMLOutput();
                    break;
            }

        } while (choice != 0);
    }

}
