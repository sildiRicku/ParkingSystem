package com.example.system;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CucumberReportGenerator {

    public static void main(String[] args) {
        String reportOutputDirectory = "target/cucumber-reports";
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-reports/report1.json");
        jsonFiles.add("target/cucumber-reports/report2.json");
        // Add all relevant JSON report files

        Configuration configuration = new Configuration(new File(reportOutputDirectory), "Parking System");
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
