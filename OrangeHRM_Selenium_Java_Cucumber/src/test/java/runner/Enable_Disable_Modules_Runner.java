package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Cucumber_Feature_Files/08_Enable_Disable_Modules.feature", glue = {"stepDefinition","hooks"})
public class Enable_Disable_Modules_Runner {

}
