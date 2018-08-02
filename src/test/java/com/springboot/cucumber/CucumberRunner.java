package com.springboot.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/", glue = { "com.springboot.cucumber.steps" }, tags = {
		"@RegressionTest" }, plugin = { "pretty", "html:target/cucumber-report", "json:target/cucumber.json",
				"usage:target/usage.jsonx", "junit:target/junit.xml" })
public class CucumberRunner {
}