package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Cucumber_Feature_Files/03_Admin_Verify_NewEmployee_Info.feature", glue = {"stepDefinition","hooks"})
public class Admin_Verify_NewEmployee_Info {

}
