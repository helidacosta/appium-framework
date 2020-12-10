package br.com.santander.runner;

import org.junit.runner.RunWith;

import br.com.santander.core.Propriedades;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/features"},
		glue = {"br.com.santander"},
		plugin = {"pretty", "html:target/cucumber-reports/cucumber.html", 
				"json:target/cucumber-reports/cucumber.json", "rerun:target/rerun.txt"}
		)

public class CucumberRunnerTest {

}
