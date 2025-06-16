package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Cucumber_Feature_Files", glue = {"stepDefinition", "hooks"}, plugin = {"pretty","html:target/cucumber-html-report.html","json:target/cucumber.json"})
public class All_Tests_Runner {
	
}
