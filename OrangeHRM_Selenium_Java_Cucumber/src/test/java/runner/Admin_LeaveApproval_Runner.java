package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Cucumber_Feature_Files/06_Admin_LeaveApproval.feature", glue = {"stepDefinition", "hooks"})
public class Admin_LeaveApproval_Runner {

}
