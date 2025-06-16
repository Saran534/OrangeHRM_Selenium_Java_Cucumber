package stepDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import hooks.Before_After_Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Admin_Adding_NewEmployee_StepDef {

	WebDriver driver = Before_After_Hooks.driver;
	static String empid_value;
	
	/*Scenario: Admin Adds A New Employee And Verifies The Record*/
	@Given("the admin user logs in with username {string} and password {string}")
	public void the_admin_user_logs_in_with_username_and_password(String usname, String uspass) {
		
		// === Admin Login ===
		//Username:
		WebElement uname = driver.findElement(By.name("username"));
		uname.sendKeys(usname);
		
		//Password:
		WebElement psswrd = driver.findElement(By.name("password"));
		psswrd.sendKeys(uspass);
		
		//Login Button:
		WebElement loginbtn = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginbtn.click();
		
		//Verify Dashboard Screen Appeared:
		WebElement dashbrdappear = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));
		String dbapper = dashbrdappear.getText();
		assertEquals(dbapper, "Dashboard", "Dashboard Screen Not Viewed:");
		
		//Printing Username:
		WebElement currentusername = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']/p"));
		String cusername = currentusername.getText();
		System.out.println("Current Admin Username: "+cusername);
	}
	
	@When("the admin user navigates to the Add Employee screen")
	public void the_admin_user_navigates_to_the_Add_Employee_screen() {
		
		//Selecting PIM Module From SideBar Menu Panel:
		WebElement pimmodule = driver.findElement(By.xpath("//span[text()='PIM']"));
		pimmodule.click();
		
		//Selecting Add Employee Screen:
		WebElement addemp = driver.findElement(By.xpath("//a[text()='Add Employee']"));
		addemp.click();
		
		//Verify Add Employee Screen Appeared:
		WebElement addempdiv = driver.findElement(By.xpath("//h6[text()='Add Employee']"));
		String addempdivtxt = addempdiv.getText();
		assertEquals(addempdivtxt, "Add Employee", "Add Employee Screen Not Viewed:");
	}
	
	@When("the admin user adding new employee with firstname {string} and lastname {string}")
	public void the_admin_user_adding_new_employee_with_firstname_and_lastname(String newfsname, String newlsname) {
		
		//First Name:
		WebElement fname = driver.findElement(By.name("firstName"));
		fname.sendKeys(newfsname);
		
		//Last Name:
		WebElement lname = driver.findElement(By.name("lastName"));
		lname.sendKeys(newlsname);
		
		//Employee ID:
		WebElement empid = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
		empid_value = empid.getAttribute("value");
		System.out.println("Employee ID: " + empid_value);
	}
	
	@When("the admin user creates login credentials with username {string} and password {string}")
	public void the_admin_user_creates_login_credentials_with_username_and_password(String newusname, String newuspass) {
		
		//Create Loggin Details Toggle Button:
		WebElement cld_togglebtn = driver.findElement(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']"));
		cld_togglebtn.click();
		
		//CLD Username:
		WebElement unamefield = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]"));
		unamefield.sendKeys(newusname);
		
		//Verify Radio Button Is Selected On Enable:
		WebElement enableradiobtn = driver.findElement(By.xpath("//label[normalize-space()='Enabled']/input"));
		boolean eradiobtn = enableradiobtn.isSelected();
		assertTrue(eradiobtn, "Radio Button Is Not Selected On Enable:");
		
		//CLD Password:
		WebElement passwordfield = driver.findElement(By.xpath("(//input[@type='password'])[1]"));
		passwordfield.sendKeys(newuspass);
		
		//CLD Confirmation Password:
		WebElement cpasswordfield = driver.findElement(By.xpath("(//input[@type='password'])[2]"));
		cpasswordfield.sendKeys(newuspass);
	}
	
	@Then("the new employee should be saved successfully")
	public void the_new_employee_should_be_saved_successfully() {
		
		//Save Button:
		WebElement savebtn = driver.findElement(By.xpath("//button[normalize-space()='Save']"));
		savebtn.click();
		
		//Success Save Toast Message:
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement toastmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='oxd-toaster_1']/div/div/div/p[2]")));
		System.out.println("New Employee: "+toastmsg.getText());
	}
	
	@When("the admin user navigates to Employee List screen")
	public void the_admin_user_navigates_to_Employee_List_screen() {
		
		//Employee List Menu:
		WebElement emplist = driver.findElement(By.xpath("//a[text()='Employee List']"));
		emplist.click();
		
		//Employee List Screen Appeared:
		WebElement emplistscreen = driver.findElement(By.xpath("//div[@class='oxd-table-filter']/div[1]/div/h5"));
		String emplist_screenview = emplistscreen.getText();
		assertEquals(emplist_screenview, "Employee Information", "Employee Information Screen Not Viewed:");
	}
	
	@When("searches for the employee with first name {string} and the generated employee ID")
	public void searches_for_the_employee_with_first_name_and_the_generated_employee_ID(String newempname) {
		
		//Employee_List Employee Username:
		WebElement empname1 = driver.findElement(By.xpath("(//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--active']/input)[1]"));
		empname1.sendKeys(newempname);
		
		//Employee_List Employee ID:
		WebElement empidfield = driver.findElement(By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']"));
		empidfield.sendKeys(empid_value);
		
		//Employee_List Save Button:
		WebElement searchbtn = driver.findElement(By.xpath("//button[normalize-space()='Search']"));
		searchbtn.click();
	}
	
	@Then("the employee record should be displayed")
	public void the_employee_record_should_be_displayed() {
		
		WebElement recordfound = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[1]"));
		String rflabel_txt = recordfound.getText();
			if (rflabel_txt.equals("(1) Record Found")) {
				
				//Employee List Table - Employee ID:
				WebElement emptabel_id = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[2]"));
				String emp_ID = emptabel_id.getText();
				
				//Employee List Table - Employee Name:
				WebElement emptabel_name = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[3]"));
				String emp_Name = emptabel_name.getText();
				
				//Employee List Table - Employee Last Name:
				WebElement emptabel_lastname = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[4]"));
				String emp_LastName = emptabel_lastname.getText();
	
				System.out.println("Employee ID: "+emp_ID + "\tEmployee FirstName: " + emp_Name + "\tEmployee LastName: " + emp_LastName);
			} else {
				System.out.println("No Records Found:");
			}
	}
	
	@Then("the admin user logout the application")
	public void the_admin_user_logout_the_application() {
		
		// === Admin Logout ===
		WebElement admininfo = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));
		admininfo.click();

		WebElement adminlogout = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		adminlogout.click();
	}
	
}
