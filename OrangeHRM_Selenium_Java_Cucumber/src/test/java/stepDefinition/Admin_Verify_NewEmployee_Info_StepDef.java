package stepDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import hooks.Before_After_Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Admin_Verify_NewEmployee_Info_StepDef {

	WebDriver driver = Before_After_Hooks.driver;
	static String empid_value;
	
	/*Scenario: Admin User logged in and adds a New Employee*/
	@Given("admin user login with username {string} and password {string}")
	public void admin_user_login_with_username_and_password(String adname1, String adpassw1) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === Admin Login ===
		//Username Field:
		WebElement uname = driver.findElement(By.name("username"));
		uname.sendKeys(adname1);
		
		//Password Field:
		WebElement psswrd = driver.findElement(By.name("password"));
		psswrd.sendKeys(adpassw1);
		
		//Login Button:
		WebElement loginbtn = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginbtn.click();
		
		//Verify Dashboard Screen Appeared:
		WebElement dashbrdappear = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));		
		String dbapper = dashbrdappear.getText();
		assertEquals(dbapper, "Dashboard", "Dashboard Screen Not Viewed:");
		
		//Printing Admin Username:
		WebElement LoginUsername = driver.findElement(By.xpath("//div[@class='oxd-topbar-header-userarea']/ul/li/span/p"));	
		String LoginuserName = LoginUsername.getText();
		System.out.println("Current Admin Name: "+LoginuserName);
	}
	
	@When("admin user navigates to the Add Employee screen")
	public void admin_user_navigates_to_the_Add_Employee_screen() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Select PIM Module From Right SideBar Menu:
		WebElement pimmodule = driver.findElement(By.xpath("//span[text()='PIM']"));	
		pimmodule.click();
		
		//Add Employee Menu:
		WebElement addemp = driver.findElement(By.xpath("//a[text()='Add Employee']"));	
		addemp.click();
		
		//Verifying Add Employee Screen View Appeared:
		WebElement addempdiv = driver.findElement(By.xpath("//h6[text()='Add Employee']"));
		String addempdivtxt = addempdiv.getText();
		assertEquals(addempdivtxt, "Add Employee", "Add Employee Screen Not Viewed:");
	}
	
	@When("admin user adds a new employee with firstname {string} and lastname {string}")
	public void admin_user_adds_a_new_employee_with_firstname_and_lastname(String fsname, String lsname) {
		
		//First Name:
		WebElement fname = driver.findElement(By.name("firstName"));
		fname.sendKeys(fsname);
		
		//Last Name:
		WebElement lname = driver.findElement(By.name("lastName"));
		lname.sendKeys(lsname);
		
		//Employee Id:
		WebElement empid = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
		empid_value = empid.getAttribute("value");
		System.out.println("Employee ID: "+empid_value);
	}
	
	@When("admin user creates login credentials with username {string} and password {string}")
	public void admin_user_creates_login_credentials_with_username_and_password(String newusname, String newuspass) {
		
		// === Create Login Details ===
		//Toggle Button:
		WebElement cld_togglebtn = driver.findElement(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']"));
		cld_togglebtn.click();
		
		//Username:
		WebElement unamefield = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]"));
		unamefield.sendKeys(newusname);
		
		//Verifying Status Radio Button:
		WebElement enableradiobtn = driver.findElement(By.xpath("//label[normalize-space()='Enabled']/input"));
		boolean eradiobtn = enableradiobtn.isSelected();
		assertTrue(eradiobtn, "Radio Button Is Not Selected On Enabel:");
		
		//Password:
		WebElement passwordfield = driver.findElement(By.xpath("(//input[@type='password'])[1]"));
		passwordfield.sendKeys(newuspass);
		
		//Confirmation Password:
		WebElement cpasswordfield = driver.findElement(By.xpath("(//input[@type='password'])[2]"));
		cpasswordfield.sendKeys(newuspass);
	}
	
	@Then("new employee should be saved successfully")
	public void new_employee_should_be_saved_successfully() {
		
		//Save Button:
		WebElement savebtn = driver.findElement(By.xpath("//button[normalize-space()='Save']"));
		savebtn.click();
		
		//Saved Successful Toast Message:
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement toastmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='oxd-toaster_1']/div/div/div/p[2]")));
		System.out.println(toastmsg.getText());
	}
	
	@When("admin user navigates to the Employee List screen")
	public void admin_user_navigates_to_the_employee_list_screen() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Select PIM Module From SideBar Menu
		WebElement pimmodule = driver.findElement(By.xpath("//span[text()='PIM']"));	
		pimmodule.click();
		
		//Verify Employee Information Screen Appeared:
		WebElement empinfo = driver.findElement(By.xpath("//div[@class='oxd-table-filter-header']/div/h5"));
		String EmpInfo = empinfo.getText();
		assertEquals(EmpInfo, "Employee Information", "Employee Information Screen Not Viewed:");
	}
	
	@When("searches for the employee with firstname {string} and the generated employee ID")
	public void searches_for_the_employee_with_firstname_and_the_generated_employee_id(String fsname1) {
		
		//First Name:
		WebElement empname1 = driver.findElement(By.xpath("(//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--active']/input)[1]"));
		empname1.sendKeys(fsname1);
		
		//Last Name:
		WebElement empID_Value = driver.findElement(By.xpath("//div[@class='oxd-form-row']/div/div[2]/div/div[2]/input[@class='oxd-input oxd-input--active']"));
		empID_Value.sendKeys(empid_value);
		
		//Search Button:
		WebElement searchbtn = driver.findElement(By.xpath("//div[@class='oxd-form-actions']/button[@type='submit']"));
		searchbtn.click();
	}
	
	@Then("employee record should be displayed")
	public void employee_record_should_be_displayed() throws InterruptedException {
		
		// === Verifying Newly Added User Record Found on Employee List Table ===
		WebElement recordfound = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[1]"));
		String rflabel_txt = recordfound.getText();
		
			if (rflabel_txt.equals("(1) Record Found")) {
			   
			   //Employee List Tabel:
			   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			   WebElement emplist_tabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-card']/div/div[3]")));
			   emplist_tabel.click();
			   
			   Thread.sleep(2000);
			   //Employee_Info Nationality Dropdown:
			   WebElement national_DD = driver.findElement(By.xpath("(//div[@class='oxd-select-text oxd-select-text--active']/div[@class='oxd-select-text-input'])[1]"));
			   String national_dd = national_DD.getText();
			   System.out.println("Nationality Dropdown Value: "+national_dd);
			   
			   //Employee_Info Marital Status DropDown:
			   WebElement marital_stat = driver.findElement(By.xpath("(//div[@class='oxd-select-text-input'])[2]"));
			   String martialstatus = marital_stat.getText();
			   System.out.println("Marital Status Dropdown Value: "+martialstatus);
			   
			   // === Verifying Gender Male & Female Radio Button Are Selected ===
			   //Employee_Info Male Gender Radio Button:
			   WebElement genderMaleRadio = driver.findElement(By.xpath("//input[@type='radio' and @value='1']"));
			   boolean MaleRadioBtn = genderMaleRadio.isSelected();
			   
			   //Employee_Info Female Gender Radio Button:
			   WebElement genderFemaleRadio = driver.findElement(By.xpath("//input[@type='radio' and @value='2']"));
			   boolean FemaleRadioBtn = genderFemaleRadio.isSelected();	
		
					if (MaleRadioBtn!=true && FemaleRadioBtn!=true) {
						System.out.println("Both Male & Female Radio Button's Not Selected:");
					}
					else {	
						if (MaleRadioBtn==true) {
							System.out.println("Male Button Is Selected:");
							}
						if (FemaleRadioBtn==true) {
							System.out.println("Female Button Is Selected:");
							}
					}
				
				//Employee_Info Blood Type DropDown:
				WebElement bloodtype = driver.findElement(By.xpath("(//div[@class='oxd-select-text-input'])[3]"));
				String bloodtype_value = bloodtype.getText();
				System.out.println("BloodType: "+bloodtype_value);
			}
			else {
				System.out.println("Employee Record Not Found:");
			}
	}
	
	@Then("admin user logout of the application")
	public void admin_user_logout_of_the_application() {
		
		// === Admin Logout ===
		WebElement admininfo = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		admininfo.click();	
	
		WebElement adminlogout = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		adminlogout.click();
	}
//------------------------------------------------------------------------------------------------------------------------
	/*Scenario: New employee logs in and adds personal info in the My Info module*/
	@Given("new employee login with username {string} and password {string}")
	public void new_employee_login_with_username_and_password(String newusname, String newuspass) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === New Employee Login ===
		//Username Field:
		WebElement uname1 = driver.findElement(By.name("username"));
		uname1.sendKeys(newusname);

		//Password Field:
		WebElement psswrd1 = driver.findElement(By.name("password"));
		psswrd1.sendKeys(newuspass);

		//Login Button:
		WebElement loginbtn1 = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginbtn1.click();
	}
	
	@Given("new employee successfully views the dashboard screen")
	public void new_employee_successfully_views_the_dashboard_screen() {
		
		//Verify The Dashboard Screen Appeared:
		WebElement dashbrdappear1 = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));		
		String dbapper1 = dashbrdappear1.getText();
		assertEquals(dbapper1, "Dashboard", "Dashboard Screnn Not Viewed:");

		//Verifying Username From Login_Info Screen Appeared:
		WebElement login_username = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']/p"));
		System.out.println("\nNew EmployeeName: "+login_username.getText());
	}
	
	@Given("new employee navigates to the My Info module")
	public void new_employee_navigates_to_the_my_info_module() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Select My Info Module From SideBar Menu:
		WebElement myinfomodule = driver.findElement(By.xpath("//span[text()='My Info']"));
		myinfomodule.click();
	}
	
	@Given("new employee adds their details on the Personal Information screen")
	public void new_employee_adds_their_details_on_the_personal_information_screen() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement pdlabel = driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']"));
		String personaldetailtxt = pdlabel.getText();
			if (personaldetailtxt.equals("Personal Details")) {
				
				Thread.sleep(2000);
				//Employee_Info Nationality DropDown:
				WebElement nationalitydd = driver.findElement(By.xpath("(//div[@class='oxd-select-text-input'])[1]"));
				nationalitydd.click();
				
				//Employee_Info Nationality DropDown Lists:
				List<WebElement> nationality = driver.findElements(By.xpath("//div[@class='oxd-select-dropdown --positon-bottom']/div/span"));
					for (WebElement national_dd : nationality) {
						String national_ddlist = national_dd.getText();
							if (national_ddlist.equals("Indian")) {
								national_dd.click();
								break;
							}
					}

				String nationalityvalue = nationalitydd.getText();
				System.out.println(nationalityvalue);
				
				//Employee_Info Marital Status DropDown:
				WebElement martial_stat = driver.findElement(By.xpath("(//div[@class='oxd-select-text-input'])[2]"));
				martial_stat.click();
				
				//Employee_Info Marital Status DropDown Lists:
				List<WebElement> msoptions = driver.findElements(By.xpath("//div[@class='oxd-select-dropdown --positon-bottom']/div/span"));
					for (WebElement marital_dd : msoptions) {
						String marital_ddlist = marital_dd.getText();
							if (marital_ddlist.equals("Single")) {
								marital_dd.click();
								break;
							}
					}
				
				String martialstatvalue = martial_stat.getText();
				System.out.println(martialstatvalue);
				
				//Employee_Info Gender Male Radio Button
				WebElement gender_male = driver.findElement(By.xpath("(//div[@class='oxd-radio-wrapper']/label/span)[1]"));
				gender_male.click();
				
				boolean genderMale_select = driver.findElement(By.xpath("(//input[@type='radio'])[1]")).isSelected();
				if (genderMale_select==true) {
					System.out.println("Male");
				}
				
				//Employee_Info Blood Type DropDown:
				WebElement bloodty = driver.findElement(By.xpath("(//div[@class='oxd-select-text-input'])[3]"));
				bloodty.click();
				
				List<WebElement> bloodoption = driver.findElements(By.xpath("//div[@class='oxd-select-dropdown --positon-bottom']/div/span"));
				for (WebElement blood_dd : bloodoption) {
					String blood_ddlist = blood_dd.getText();
						if (blood_ddlist.equals("A+")) {
							blood_dd.click();
							break;
						}
				}
				
				String bloodtyvalue = bloodty.getText();
				System.out.println(bloodtyvalue);
			} else {
				System.out.println("Personal Details Screen Not Appeared:");
			}		
	}
	
	@Then("new employee saves the personal information")
	public void new_employee_saves_the_personal_information() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Employee_Info Personal Info Save Button:
		WebElement savebtn1 = driver.findElement(By.xpath("(//div[@class='oxd-form-actions']/button)[1]"));
		savebtn1.click();
		
		//Employee_Info Custom Field Save Button:
		WebElement savebtn2 = driver.findElement(By.xpath("(//div[@class='oxd-form-actions']/button)[2]"));
		savebtn2.click();
	}
	
	@Then("new employee logs out of the application")
	public void new_employee_logs_out_of_the_application() {
		
		// === New User Logout ===
		WebElement newuserInfo = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		newuserInfo.click();	
				
		WebElement newuserlogout = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		newuserlogout.click();
	}

//------------------------------------------------------------------------------------------------------------------------	
	/* Scenario: Admin User Verifies The New User Personal Info */
	@Given("admin user logs in with username {string} and password {string}")
	public void admin_user_logs_in_with_username_and_password(String adname, String adpass) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === Admin Login ===
		WebElement uname = driver.findElement(By.name("username"));
		uname.sendKeys(adname);
		
		WebElement psswrd = driver.findElement(By.name("password"));
		psswrd.sendKeys(adpass);
		
		WebElement loginbtn = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginbtn.click();
		
		WebElement dashbrdappear = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));		
		String dbapper = dashbrdappear.getText();
		assertEquals(dbapper, "Dashboard", "Dashboard Screen Not Viewed:");
			
		WebElement LoginUsername = driver.findElement(By.xpath("//div[@class='oxd-topbar-header-userarea']/ul/li/span/p"));	
		String LoginuserName = LoginUsername.getText();
		System.out.println("\nCurrent Admin Name: "+LoginuserName);
	}
	
	@Given("admin user redirect to the Employee List screen")
	public void admin_user_redirect_to_the_employee_list_screen() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Select PIM Module From SideBar Menu Panel:
		WebElement pimmodule = driver.findElement(By.xpath("//span[text()='PIM']"));	
		pimmodule.click();
	}
	
	@Given("admin user searches for the employee with firstname {string} and generated employee ID")
	public void admin_user_searches_for_the_employee_with_firstname_and_generated_employee_id(String fsname2) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement empinfo = driver.findElement(By.xpath("//div[@class='oxd-table-filter-header']/div/h5"));
		String EmpInfo = empinfo.getText();
			if (EmpInfo.equals("Employee Information")) {
				//Employee Name:
				WebElement empname1 = driver.findElement(By.xpath("(//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--active']/input)[1]"));
				empname1.sendKeys(fsname2);
				
				//Employee ID:
				WebElement empID_Value = driver.findElement(By.xpath("//div[@class='oxd-form-row']/div/div[2]/div/div[2]/input[@class='oxd-input oxd-input--active']"));
				empID_Value.sendKeys(empid_value);
				
				//Search Button:
				WebElement searchbtn = driver.findElement(By.xpath("//div[@class='oxd-form-actions']/button[@type='submit']"));
				searchbtn.click();
			}else {
				System.out.println("Employee Information Screen Appeared Successfully:");
			}
	}
	
	@Then("admin user verifies the new user personal information")
	public void admin_user_verifies_the_new_user_personal_information() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement recordfound = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[1]"));
		String rflabel_txt = recordfound.getText();
		
			if (rflabel_txt.equals("(1) Record Found")) {
			   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			   WebElement emplist_tabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-card']/div/div[3]")));
			   emplist_tabel.click();
			   
			   Thread.sleep(2000);
			   WebElement national_DD = driver.findElement(By.xpath("(//div[@class='oxd-select-text oxd-select-text--active']/div[@class='oxd-select-text-input'])[1]"));
			   String national_dd = national_DD.getText();
			   System.out.println("Nationality Dropdown Value: "+national_dd);
				
			   WebElement marital_stat = driver.findElement(By.xpath("(//div[@class='oxd-select-text-input'])[2]"));
			   String martialstatus = marital_stat.getText();
			   System.out.println("Marital Status Dropdown Value: "+martialstatus);
				
			   WebElement genderMaleRadio = driver.findElement(By.xpath("//input[@type='radio' and @value='1']"));
			   boolean MaleRadioBtn = genderMaleRadio.isSelected();
				
			   WebElement genderFemaleRadio = driver.findElement(By.xpath("//input[@type='radio' and @value='2']"));
			   boolean FemaleRadioBtn = genderFemaleRadio.isSelected();	
		
					if (MaleRadioBtn!=true && FemaleRadioBtn!=true) {
						System.out.println("Both Male & Female Radio Button's Not Selected:");
					}
					else {	
						if (MaleRadioBtn==true) {
							System.out.println("Male Button Is Selected:");
							}
						if (FemaleRadioBtn==true) {
							System.out.println("Female Button Is Selected:");
							}
					}
		
				WebElement bloodtype = driver.findElement(By.xpath("(//div[@class='oxd-select-text-input'])[3]"));
				String bloodtype_value = bloodtype.getText();
				System.out.println("BloodType: "+bloodtype_value);
			}
			else {
				System.out.println("Employee Record Not Found:");
			}
	}
	
	@Then("admin user logs out of the application")
	public void admin_user_logs_out_of_the_application() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === Admin User Logout ===
		WebElement admininfo = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		admininfo.click();	
	
		WebElement adminlogout = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		adminlogout.click();
	}
	
}
