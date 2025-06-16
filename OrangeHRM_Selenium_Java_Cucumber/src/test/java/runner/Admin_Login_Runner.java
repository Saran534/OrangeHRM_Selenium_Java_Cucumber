package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Cucumber_Feature_Files/01_Admin_Login.feature", glue = {"stepDefinition","hooks"})
public class Admin_Login_Runner {

}
