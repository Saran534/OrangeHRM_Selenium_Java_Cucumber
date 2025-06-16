package stepDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class Admin_ChangePassword_NewEmployee_StepDef {

	WebDriver driver = Before_After_Hooks.driver;
	
	/*Scenario: Admin Logged In And Adding New Employee*/	
	@Given("admin enters there username {string} and password {string}")
	public void admin_enters_there_username_and_password(String adusname, String aduspassw) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// === Admin Login ===
		//Username:
		WebElement uname = driver.findElement(By.name("username"));
		uname.sendKeys(adusname);
		
		//Password:
		WebElement psswrd = driver.findElement(By.name("password"));
		psswrd.sendKeys(aduspassw);
		
		//Login Button:
		WebElement loginbtn = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginbtn.click();		
		
		//Verify Dashboard Screen Appeared:
		WebElement dashbrdappear = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));		
		String dbapper = dashbrdappear.getText();
		assertEquals(dbapper, "Dashboard", "Dashboard Screen Not Viewed:");		
		
		//Printing Username:
		WebElement usernametxt = driver.findElement(By.xpath("//div[@class='oxd-topbar-header-userarea']/ul/li/span/p"));
		String unametext = usernametxt.getText();
		System.out.println("Current Admin UserName: "+unametext);
	}
	
	@Given("admin adding a new employee with login credentials")
	public void admin_adding_a_new_employee_with_login_credentials() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Select PIM Module From SideBar Menu Panel:
		WebElement pimmodule = driver.findElement(By.xpath("//span[text()='PIM']"));	
		pimmodule.click();
		
		// === Adding New User From Add Employee Menu ===
		//Add Employee Menu
		WebElement addemp = driver.findElement(By.xpath("//a[text()='Add Employee']"));	
		addemp.click();
		
		//Verifying Add Employee Screen Appeared:
		WebElement addempdiv = driver.findElement(By.xpath("//h6[text()='Add Employee']"));
		String addempdivtxt = addempdiv.getText();
		assertEquals(addempdivtxt, "Add Employee", "Add Employee Screen Not Viewed");
		
		//Employee Full Name:
		//First Name:
		WebElement fname = driver.findElement(By.name("firstName"));
		fname.sendKeys("Satya");
		
		//Last Name:
		WebElement lname = driver.findElement(By.name("lastName"));
		lname.sendKeys("Nadella");
		
		//Employee Id:
		WebElement empid = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
		String empid_value = empid.getAttribute("value");
		System.out.println("Employee ID: "+empid_value);
		
		/* Create Login Details */
		//Create Login Details - Toggle Button:
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cld_togglebtn = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")));
		cld_togglebtn.click();
		
		//Create Login Details - Username* :
		WebElement unamefield = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]"));
		unamefield.sendKeys("satya_nadella");
		
		//Create Login Details - Status Radio Button:
		WebElement enableradiobtn = driver.findElement(By.xpath("//label[normalize-space()='Enabled']/input"));
		boolean eradiobtn = enableradiobtn.isSelected();
			if (eradiobtn==true) {
				System.out.println("By Default Radio Button Is Selected On Enabel:");
			} else {
				System.out.println("Radio Buutton Is Not Selected On Enabel:");
			}
		
		//Create Login Details - Password* :
		WebElement passwordfield = driver.findElement(By.xpath("(//input[@type='password'])[1]"));
		passwordfield.sendKeys("satya_nadella123");
		
		//Create Login Details - Confirmation Password* :
		WebElement cpasswordfield = driver.findElement(By.xpath("(//input[@type='password'])[2]"));
		cpasswordfield.sendKeys("satya_nadella123");
		
		//Create Login Details - Save Button:
		WebElement savebtn = driver.findElement(By.xpath("//button[normalize-space()='Save']"));
		savebtn.click();
		
		//Saved Successful Toast Message:
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement toastmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='oxd-toaster_1']/div/div/div/p[2]")));
		System.out.println("New Employee: "+toastmsg.getText());
	
		//Employee Information From Employee List Menu:
		WebElement emplist = driver.findElement(By.xpath("//a[text()='Employee List']"));
		emplist.click();
		
		//Employee Name:
		WebElement empname1 = driver.findElement(By.xpath("(//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--active']/input)[1]"));
		empname1.sendKeys("Satya");
		
		//Employee Id:
		WebElement empidfield = driver.findElement(By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']"));
		empidfield.sendKeys(empid_value);
		
		//Search Button:
		WebElement searchbtn = driver.findElement(By.xpath("//button[normalize-space()='Search']"));
		searchbtn.click();
		
		// === Verifying Newly Added User Record Found on Employee List Table ===
		WebElement recordfound = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[1]"));
		String rflabel_txt = recordfound.getText();
		
			if (rflabel_txt.equals("(1) Record Found")) {
				
				WebElement emptabel_id = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[2]"));
			    String emp_ID = emptabel_id.getText();
			    
			    WebElement emptabel_name = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[3]"));
			    String emp_Name = emptabel_name.getText();
				
				WebElement emptabel_lastname = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[4]"));
				String emp_LastName = emptabel_lastname.getText();
				
				System.out.println("Employee ID: "+emp_ID+"\tEmployee FirstName: "+emp_Name+"\tEmployee LastName: "+emp_LastName);
				
			} else {
				System.out.println("No Records Found:");
			}

	}
	
	@Then("admin logout the application")
	public void admin_logout_the_application() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === Admin Logout ===
		WebElement admininfo = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		admininfo.click();	
	
		WebElement adminlogout = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		adminlogout.click();

	}
	
//-----------------------------------------------------------------------------------------------------	
	/*Scenario: New User First Time Login*/
	@Given("new employee login with there credentials")
	public void new_employee_login_with_there_credentials() {
		
		// === New Employee Login ===
		//Username Field:
		WebElement uname1 = driver.findElement(By.name("username"));
		uname1.sendKeys("satya_nadella");

		//Password Field:
		WebElement psswrd1 = driver.findElement(By.name("password"));
		psswrd1.sendKeys("satya_nadella123");

		//Login Button:
		WebElement loginbtn1 = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginbtn1.click();
	}
	
	@Given("after successfull login new employee can see the dashboard")
	public void after_successfull_login_new_employee_can_see_the_dashboard() {
		
		//Verify Dashboard Screen Appeared:
		WebElement dashbrdappear1 = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));		
		String dbapper1 = dashbrdappear1.getText();
		assertEquals(dbapper1, "Dashboard", "Dashboard Screen Not Viewed:");

		//Verifying Username From Login Info:
		WebElement login_username = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']/p"));
		System.out.println("\nNew Employee Name: "+login_username.getText());
	}
	
	@Then("new employee logout the application")
	public void new_employee_logout_the_application() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === Admin Logout ===
		WebElement newuserInfo = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		newuserInfo.click();
		
		WebElement newuserlogout = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		newuserlogout.click();
	}

//-----------------------------------------------------------------------------------------------------	
	/*Scenario: New Employee Forgot Login Password*/
	@Given("new employee login with there credentials correct username and wrong password")
	public void new_employee_login_with_there_credentials_correct_username_and_wrong_password() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === New Employee Login With Wrong Password ===
		//Username Field:
		WebElement uname1 = driver.findElement(By.name("username"));
		uname1.sendKeys("satya_nadella");

		//Password Field:
		WebElement psswrd1 = driver.findElement(By.name("password"));
		psswrd1.sendKeys("satya_nadella12");//For Example User Forgot Password.

		//Login Button:
		WebElement loginbtn1 = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginbtn1.click();

	}
	
	@Then("new employee unable to login and seeing the error message of invalid login")
	public void new_employee_unable_to_login_and_seeing_the_error_message_of_invalid_login() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement invalidchk = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']/div[1]/p")));
		String invalidtxt = invalidchk.getText();
			if (invalidtxt.equals("Invalid credentials")) {
				System.out.println("Entered Invalid Credentials:");
			} else {
				System.out.println("Entered Valid Credentials:");
			}
	}

//-----------------------------------------------------------------------------------------------------	
	/* Scenario: Admin Logged In And Change New Password For Newuser */
	@Given("admin enters a username {string} and password {string}")
	public void admin_enters_a_username_and_password(String adusname1, String aduspass1) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// === Admin Login ===
		//Username:
		WebElement uname = driver.findElement(By.name("username"));
		uname.sendKeys(adusname1);
		
		//Password:
		WebElement psswrd = driver.findElement(By.name("password"));
		psswrd.sendKeys(aduspass1);
		
		//Login Button:
		WebElement loginbtn = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginbtn.click();
		
		//Printing Username:
		WebElement usernametxt = driver.findElement(By.xpath("//div[@class='oxd-topbar-header-userarea']/ul/li/span/p"));
		String unametext = usernametxt.getText();
		System.out.println("\nCurrent Admin UserName: "+unametext);
	}
	
	@Given("admin navigates to Admin Module and change password for new employee")
	public void admin_navigates_to_admin_module_and_change_password_for_new_employee() {
		
		//Select Admin Module From SideBar Menu Panel:
		WebElement adminmodule = driver.findElement(By.xpath("//div[@class='oxd-sidepanel-body']/ul/li/a/span[text()='Admin']"));
		adminmodule.click();
		
		//System User Screen:
		WebElement systemuserlabel = driver.findElement(By.xpath("//div[@class='oxd-table-filter-header']/div/h5"));
		String systemUsertxt = systemuserlabel.getText();
			if (systemUsertxt.equals("System Users")) {
				
				WebElement empname = driver.findElement(By.xpath("//div[@class='oxd-autocomplete-wrapper']/div/input"));
				empname.sendKeys("Satya Nadella");
				
				List<WebElement> empname_dd = driver.findElements(By.xpath("//div[@class='oxd-autocomplete-wrapper']/div[@role='listbox']/div/span"));
				for (WebElement empname_ddlist : empname_dd) {
					String empname_list = empname_ddlist.getText();
						if (empname_list.equals("Satya Nadella")) {
							empname_ddlist.click();
							break;
						}
				}
				
				WebElement savebtn = driver.findElement(By.xpath("(//div[@class='oxd-form-actions']/button)[2]"));
				savebtn.click();
				
				WebElement recordlabel = driver.findElement(By.xpath("//div[@class='orangehrm-paper-container']/div[2]/div/span"));
				String rlabel = recordlabel.getText();
					if (rlabel.equals("(1) Record Found")) {
						WebElement empname1 = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div[@role='row']/div[4]/div"));
						String Empnametxt = empname1.getText();
						
							if (Empnametxt.equals("Satya Nadella")) {
								WebElement actioneditbtn = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div[@role='row']/div[6]/div/button[2]"));
								actioneditbtn.click();
								
								WebElement edituser_label = driver.findElement(By.xpath("//div[@class='oxd-layout-context']/div/div/h6"));
								String edituser_txt = edituser_label.getText();
								
									if (edituser_txt.equals("Edit User")) {
										WebElement EmployeName = driver.findElement(By.xpath("//div[@class='oxd-autocomplete-wrapper']/div/input"));
										EmployeName.click();
										
										List<WebElement> employeeName1 = driver.findElements(By.xpath("//div[@class='oxd-autocomplete-wrapper']/div[@role='listbox']/div/span"));
										for (WebElement emp_Name1 : employeeName1) {
											String Employename1 = emp_Name1.getText();
											if (Employename1.equals("Satya Nadella")) {
												emp_Name1.click();
												break;
											}
										}
										
										WebElement changepassword = driver.findElement(By.xpath("//div[@class='oxd-checkbox-wrapper']/label/span"));
										changepassword.click();
										
										WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(10));
										WebElement passinput = wait7.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='oxd-input-group oxd-input-field-bottom-space']/div)[12]/input")));
										passinput.sendKeys("satya_nadella1234");
										
										WebElement confirmpassinput = wait7.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='oxd-input-group oxd-input-field-bottom-space']/div)[14]/input")));
										confirmpassinput.sendKeys("satya_nadella1234");
										
										WebElement savebtn1 = driver.findElement(By.xpath("//div[@class='oxd-form-actions']/button[2]"));
										savebtn1.click();
										
										WebDriverWait wait8 = new WebDriverWait(driver, Duration.ofSeconds(10));
										WebElement savetoastmsg = wait8.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='oxd-toaster_1']/div/div/div[2]/p[2]")));
										String savemsg = savetoastmsg.getText();
										System.out.println("Password Change: "+savemsg);
										
									} else {
										System.out.println("Edit User Screen Not Appeared:");
									}
							} else {
								System.out.println("Satya Nadella Employee Name Not Found:");
							}
						
					} else {
						System.out.println("No Record Found:");
					}
		
			} else {
				System.out.println("System Users Screen Not Appeared:");
			}

	}
	
	@Then("admin logout orangehrm application")
	public void admin_logout_orangehrm_application() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === Admin Logout ===
		WebElement admininfo = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		admininfo.click();	
	
		WebElement adminlogout = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		adminlogout.click();
	}

//-----------------------------------------------------------------------------------------------------	
/*Scenario: New User Login With New Credentials*/	
	@Given("new employee login with there new credentials username and password")
	public void new_employee_login_with_there_new_credentials_username_and_password() {
		
		// === New Emmployee Login With New Password ===
		//Username Field:
		WebElement uname1 = driver.findElement(By.name("username"));
		uname1.sendKeys("satya_nadella");

		//Password Field:
		WebElement psswrd1 = driver.findElement(By.name("password"));
		psswrd1.sendKeys("satya_nadella1234");

		//Login Button:
		WebElement loginbtn1 = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginbtn1.click();

	}
	
	@Given("after successfull login new employee see the dashboard")
	public void after_successfull_login_new_employee_see_the_dashboard() {
		
		//Verify Dashboard Screen Appeared:
		WebElement dashbrdappear1 = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));		
		String dbapper1 = dashbrdappear1.getText();
		assertEquals(dbapper1, "Dashboard", "Dashboard Screen Not Appeared:");

		//Printing Username:
		WebElement login_username = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']/p"));
		System.out.println("\nCurrent New UserName: "+login_username.getText());
	}
	
	@Then("new employee logout a application")
	public void new_employee_logout_a_application() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === New Employee Logout ===
		WebElement newuserInfo = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		newuserInfo.click();	
	
		WebElement newuserlogout = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		newuserlogout.click();
	}
	
}
