package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Cucumber_Feature_Files/02_Admin_Adding_NewEmployee.feature", glue = {"stepDefinition","hooks"})
public class Admin_Adding_NewEmployee_Runner {

}
