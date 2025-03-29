package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Feature/Application.feature", glue = { "stepdefination" }, plugin = { "pretty",
		"json:target/Cucumber.json", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, dryRun = true, monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

}