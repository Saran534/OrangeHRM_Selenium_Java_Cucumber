package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Cucumber_Feature_Files/04_NewEmployee_LeaveEntitlementDays.feature", glue = {"stepDefinition","hooks"})
public class NewEmployee_LeaveEntitlementDays_Runner {

}
