package com.clover.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/featureTests",
        plugin = {"pretty", "html:target/cucumber-report", "json:target/cucumber-report/header-links.json"},
        tags = {"@search"},
        glue = {"com/clover/stepDefinitions"},
        monochrome = true,
        strict = true)

public class runnerSearch {
}