package stepDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import hooks.Before_After_Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Admin_Login_Runner_StepDef {
	
	WebDriver driver = Before_After_Hooks.driver;
	
	/*Scenario: Successful Login By Admin User*/
	@Given("the admin user enters valid credentials with username {string} and password {string}")
	public void the_admin_user_enters_valid_credentials_with_username_and_password(String usname, String uspassw) {
		
		//=== Admin Login ===
		//Username:
		WebElement uname = driver.findElement(By.name("username"));
		uname.sendKeys(usname);
		
		//Password:
		WebElement psswrd = driver.findElement(By.name("password"));
		psswrd.sendKeys(uspassw);
	}
	
	@When("the admin user clicks the login button")
	public void the_admin_user_clicks_the_login_button() {
		
		//Login Button:
		WebElement loginbtn = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginbtn.click();
	}
	
	@Then("the admin user should see the dashboard screen")
	public void the_admin_user_should_see_the_dashboard_screen() {
		
		//Verify Dashboard Screen Appeared:
		WebElement dashbrdappear = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));
		String dbapper = dashbrdappear.getText();
		assertEquals(dbapper, "Dashboard", "Dashboard Screen Not Viewed:");
	}
	
	@Then("the admin user name should be visible on the login info panel")
	public void the_admin_user_name_should_be_visible_on_the_login_info_panel() {
		
		//Printing Username:
		WebElement usernametxt = driver.findElement(By.xpath("//div[@class='oxd-topbar-header-userarea']/ul/li/span/p"));
		String unametext = usernametxt.getText();
		System.out.println("Current Admin UserName: " + unametext);
	}
	
	@Then("the admin user logs out")
	public void the_admin_user_logs_out() {
		
		// === Admin Logout ===
		WebElement admininfo = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));
		admininfo.click();

		WebElement adminlogout = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		adminlogout.click();
	}
	
}
