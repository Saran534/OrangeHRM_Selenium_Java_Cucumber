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

public class NewEmployee_LeaveEntitlementDays_StepDef {
	
	WebDriver driver = Before_After_Hooks.driver;
	static String empid_value;
	
	/*Scenario: Admin Adding New User*/
	@Given("admin logs in using username {string} and password {string} and see there dashboard")
	public void admin_logs_in_using_username_and_password_and_see_there_dashboard(String usname, String uspass) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
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
		assertEquals(dbapper, "Dashboard", "Dashboard Screen Not Appeared:");
		
		//Printing Username:
		WebElement usernametxt = driver.findElement(By.xpath("//div[@class='oxd-topbar-header-userarea']/ul/li/span/p"));
		String unametext = usernametxt.getText();
		System.out.println("Current Admin UserName: "+unametext);
	}
	
	@Given("admin adding newuser with login credentials")
	public void admin_adding_newuser_with_login_credentials() {
		
		//Select PIM Module From SideBar Menu Panel
		WebElement pimmodule = driver.findElement(By.xpath("//span[text()='PIM']"));	
		pimmodule.click();
		
		/*Adding New User From Add Employee Menu*/
		//Add Employee Menu:
		WebElement addemp = driver.findElement(By.xpath("//a[text()='Add Employee']"));	
		addemp.click();
		
		//Verifying Add Employee Screen Appeared:
		WebElement addempdiv = driver.findElement(By.xpath("//h6[text()='Add Employee']"));
		String addempdivtxt = addempdiv.getText();
		if (addempdivtxt.equals("Add Employee")) {
			System.out.println("Add Employee Screen Appeared Successfully:");
			
			//Add Employee First Name:
			WebElement fname = driver.findElement(By.name("firstName"));
			fname.sendKeys("Tim");
			
			//Add Employee Last Name:
			WebElement lname = driver.findElement(By.name("lastName"));
			lname.sendKeys("Cook");
			
			//Add Employee Employee Id:
			WebElement empid = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
			empid_value = empid.getAttribute("value");
			System.out.println("Employee ID: "+empid_value);
			
			/*Create Login Details*/
			//Create Login Details - Toggle Button:
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement cld_togglebtn = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")));
			cld_togglebtn.click();
			
			//Username:
			WebElement unamefield = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]"));
			unamefield.sendKeys("tim_cook");
			
			//Status Radio Button:
			WebElement enableradiobtn = driver.findElement(By.xpath("//label[normalize-space()='Enabled']/input"));
			boolean eradiobtn = enableradiobtn.isSelected();
				if (eradiobtn==true) {
					System.out.println("By Default Radio Button Is Selected On Enabel:");
				} else {
					System.out.println("Radio Buutton Is Not Selected On Enabel:");
				}
			
			//Password:
			WebElement passwordfield = driver.findElement(By.xpath("(//input[@type='password'])[1]"));
			passwordfield.sendKeys("tim_cook123");
			
			//Confirmation Password:
			WebElement cpasswordfield = driver.findElement(By.xpath("(//input[@type='password'])[2]"));
			cpasswordfield.sendKeys("tim_cook123");
			
			//Save Button:
			WebElement savebtn = driver.findElement(By.xpath("//button[normalize-space()='Save']"));
			savebtn.click();
			
			//Saved Successful Toast Message:
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement toastmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='oxd-toaster_1']/div/div/div/p[2]")));
			System.out.println(toastmsg.getText());
			
		} else {
			System.out.println("Add Employee Screen Not Appeared:");
		}
	}
	
	@Given("admin verifying newuser record availabel on employee lists")
	public void admin_verifying_newuser_record_availabel_on_employee_lists() {
		
		//Employee Information From Employee List Menu:
		WebElement emplist = driver.findElement(By.xpath("//a[text()='Employee List']"));
		emplist.click();
		
		//Employee Name:
		WebElement empname1 = driver.findElement(By.xpath("(//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--active']/input)[1]"));
		empname1.sendKeys("Tim");
		
		//Employee Name DropDown Lists:
		List<WebElement> empname_ddlist = driver.findElements(By.xpath("(//div[@class='oxd-autocomplete-wrapper'])[1]/div[2]/div/span"));
		for (WebElement empnamedd : empname_ddlist) {
			String empnameddlist = empnamedd.getText();
				if (empnameddlist.equals("Tim Cook")) {
					empnamedd.click();
					break;
				}
		}
		
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
				
				//Employee List - Employee ID:
				WebElement emptabel_id = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[2]"));
			    String emp_ID = emptabel_id.getText();
			    
			    //Employee List - Employee Name:
			    WebElement emptabel_name = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[3]"));
			    String emp_Name = emptabel_name.getText();
				
			    //Employee List - Employee Last Name:
				WebElement emptabel_lastname = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[4]"));
				String emp_LastName = emptabel_lastname.getText();
				
				System.out.println("Employee ID: "+emp_ID+"\tEmployee Name: "+emp_Name+"\tEmployee LastName: "+emp_LastName);
				
			} else {
				System.out.println("No Records Found:");
			}
	}
	
	@Then("admin logout from application")
	public void admin_logout_from_application() {
		
		// === Admin Logout ===
		WebElement admininfo = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		admininfo.click();	
			
		WebElement adminlogout = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		adminlogout.click();
	}

//--------------------------------------------------------------------------------------------------------------	
	/*Scenario: New Employee Login On Application And Verifying The Leave Entitlement Days*/
	@Given("new employee login with their usrename {string} and password {string}")
	public void new_employee_login_with_their_usrename_and_password(String newusname, String newuspass) {
		
		// === New Employee ===
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
	
	@Given("new employee able to see dashboard")
	public void new_employee_able_to_see_dashboard() {
		
		//Verify Dashboard Screen Appeared:
		WebElement dashbrdappear1 = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));		
		String dbapper1 = dashbrdappear1.getText();
		assertEquals(dbapper1, "Dashboard", "Dashboard Screen Not Viewed:");

		//Printing Username:
		WebElement login_username = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']/p"));
		System.out.println("\nNew Employee Name: "+login_username.getText());
	}
	
	@Given("new employee verify the leave entitlement days")
	public void new_employee_verify_the_leave_entitlement_days() {
		
		//Select [Leave Module] - For Check Leave Entitlement:	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Leave Module Menu From SideBar Menu:
		WebElement leave = driver.findElement(By.xpath("(//div[@class='oxd-sidepanel-body']/ul[@class='oxd-main-menu']/li)[1]"));
		leave.click();
		
		//Entitlement Menu:
		WebElement entitle = driver.findElement(By.xpath("(//nav[@class='oxd-topbar-body-nav']/ul/li)[3]"));
		entitle.click();
		
		//Entitlement Menu DropDown Lists:
		List<WebElement> myentitle = driver.findElements(By.xpath("//li[@class='--active oxd-topbar-body-nav-tab --parent']/ul/li/a"));
			for (WebElement entitle_list : myentitle) {
				String entitle_dd = entitle_list.getText();
					if (entitle_dd.equals("My Entitlements")) {
						entitle_list.click();
						break;
					}
			}
		
		//Leave Entitlement Lable:
		WebElement myleavetxt = driver.findElement(By.xpath("//h5[@class='oxd-text oxd-text--h5 oxd-table-filter-title']"));
		String mletext = myleavetxt.getText();
		
			if (mletext.equals("My Leave Entitlements")) {	
				//Total Label:
				WebElement totalleave = driver.findElement(By.xpath("//div[@class='orangehrm-header-container']/span"));
				String totalleavedays = totalleave.getText();
					if (totalleavedays.equals("Total 0.00 Day(s)")) {
						System.out.println("No Leave Entitlements Days Added:");
					} else {
						System.out.println("Leave Entitlements Days Added:");
					}
					
			} else {
				System.out.println("My Leave Entitlements Not Appeared:");
			}
	}
	
	@Then("new employee logout from the application")
	public void new_employee_logout_from_the_application() {
		
		// === New Employee Logout === */
		WebElement newuserInfo = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		newuserInfo.click();	

		WebElement newuserlogout = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		newuserlogout.click();
	}

//--------------------------------------------------------------------------------------------------------------	
	/*Scenario: Admin Adding New Leave Entitlement Days For Newuser*/
	@Given("admin logs in using there username {string} and password {string} and see there dashboard")
	public void admin_logs_in_using_there_username_and_password_and_see_there_dashboard(String adusname, String aduspass) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === Admin Login ===
		//Username:
		WebElement uname = driver.findElement(By.name("username"));
		uname.sendKeys(adusname);
		
		//Password:
		WebElement psswrd = driver.findElement(By.name("password"));
		psswrd.sendKeys(aduspass);
		
		//Login Button:
		WebElement loginbtn = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginbtn.click();
		
		//Admin Login Dashboard Screen Appearing:
		WebElement dashbrdappear = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));		
		String dbapper = dashbrdappear.getText();
		assertEquals(dbapper, "Dashboard", "Dashboard Screen Not Appeared:");
		
		//Printing Admin User Name:
		WebElement usernametxt = driver.findElement(By.xpath("//div[@class='oxd-topbar-header-userarea']/ul/li/span/p"));
		String unametext = usernametxt.getText();
		System.out.println("\nCurrent Admin UserName: "+unametext);
	}
	
	@Given("admin navigates to leave module and adding leave entitlement days for newuser")
	public void admin_navigates_to_leave_module_and_adding_leave_entitlement_days_for_newuser() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Leave Module Menu:
		WebElement leavemodule1 = driver.findElement(By.xpath("//div[@class='oxd-sidepanel-body']/ul[@class='oxd-main-menu']/li/a/span[text()='Leave']"));
		leavemodule1.click();
		
		//Entitlement Menu - [Leave Module]
		WebElement entitle1 = driver.findElement(By.xpath("(//nav[@class='oxd-topbar-body-nav']/ul/li)[3]"));
		entitle1.click();
		
		//Entitlement Menu DropDown Lists:
		List<WebElement> addentitle = driver.findElements(By.xpath("//li[@class='--active oxd-topbar-body-nav-tab --parent']/ul/li/a"));
		for (WebElement addentitle_dd : addentitle) {
			String addentitle_dd_list = addentitle_dd.getText();
			if (addentitle_dd_list.equals("Add Entitlements")) {
				addentitle_dd.click();
				break;
			}
		}
		
		//Verify Add Leave Entitlement Screen Appeared:
		WebElement addleavetext = driver.findElement(By.xpath("//div[@class='orangehrm-card-container']/p"));
		String add_leave_entitle = addleavetext.getText();
		assertEquals(add_leave_entitle, "Add Leave Entitlement", "Add Leave Entitlement Screen Not Viewed:");
		
		//Add Leave Entitlement - Employee Name:
		WebElement empName = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
		empName.sendKeys("Tim");
		
		//Add Leave Entitlement - Employee Name DropDown Lists:
		List<WebElement> empnameDD = driver.findElements(By.xpath("//div[@class='oxd-autocomplete-wrapper']/div[@role='listbox']/div/span"));
		for (WebElement empName_dd : empnameDD) {
			String dd_empname = empName_dd.getText();
			if (dd_empname.equals("Tim Cook")) {
				empName_dd.click();
				break;
			}
		}
		
		//Add Leave Entitlement - Leave Type:
		WebElement leave_type = driver.findElement(By.xpath("//div[@class='oxd-select-text oxd-select-text--active'][1]/div[text()='-- Select --']"));
		leave_type.click();
		
		//Add Leave Entitlement - Leave Type DropDown:
		List<WebElement> leavetype_DD = driver.findElements(By.xpath("//div[@class='oxd-select-wrapper']/div[@role='listbox']/div/span"));
		for (WebElement leave_type_dd : leavetype_DD) {
			String leavetype_dd = leave_type_dd.getText();
				if (leavetype_dd.equalsIgnoreCase("CAN - Personal")) {
					leave_type_dd.click();
					break;
				}
		}
		
		//Entitlement Days Field:
		WebElement entitlement1 = driver.findElement(By.xpath("(//div[@class='oxd-grid-3 orangehrm-full-width-grid'])[3]/div[3]/div/div[2]/input"));
		entitlement1.sendKeys("12");
		
		//Save Button:
		WebElement savebtn11 = driver.findElement(By.xpath("(//div[@class='oxd-form-actions']/button)[2]"));
		savebtn11.click();
		
		//Upadte DialogBox Pop-Up:
		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dialogmsg = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='document']/div/p)[1]")));
		String dialogtext = dialogmsg.getText();
		System.out.println(dialogtext);
		
		//Upadte DialogBox Pop-Up Save Button:
		WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dialogmsg_savebtn = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='document']/div[@class='orangehrm-modal-footer']/button)[2]")));
		dialogmsg_savebtn.click();
		
		//Success Toast Message:
		WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement toastmsg1 = wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='oxd-toaster_1']/div[@aria-live='assertive']/div[@class='oxd-toast-start']/div[2]/p)[2]")));
		System.out.println(toastmsg1.getText());
		
		// Verify Leave Entitlement Screen Appeared:
		WebElement leave_entitle = driver.findElement(By.xpath("//h5[@class='oxd-text oxd-text--h5 oxd-table-filter-title']"));
		String lentitle_txt = leave_entitle.getText();
		assertEquals(lentitle_txt, "Leave Entitlements", "Leave Entitlement Screen Not Viewed:");
		
		//Leave Entitlement Employee Name:
		WebElement le_empName = driver.findElement(By.xpath("//div[@class='oxd-autocomplete-wrapper']/div/input"));
		String EmpName = le_empName.getText();
		
		//Leave Entitlement Leave Type:
		WebElement le_LeaveType = driver.findElement(By.xpath("(//div[@class='oxd-select-wrapper']/div/div[@class='oxd-select-text-input'])[1]"));
		String LeaveType = le_LeaveType.getText();
		
		//Leave Entitlement Period:
		WebElement le_LeavePeriod = driver.findElement(By.xpath("(//div[@class='oxd-select-wrapper'])[2]/div/div[1]"));
		String LeavePeriod = le_LeavePeriod.getText();
		
		System.out.println("EmployeeName: "+EmpName+"\tLeaveType: "+LeaveType+"\tLeavePeriod: "+LeavePeriod);
		
		//Leave Entitlement Record Table:
		WebElement record_Found = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[2]"));
		String RecordsFound = record_Found.getText();
			if (RecordsFound.equals("(1) Record Found")) {
				
				//Leave Type:
				WebElement Leave_Type = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[2]/div"));
				String ltype = Leave_Type.getText();
				
				//Entitlement Type:
				WebElement Entitlement_Type = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[3]/div"));
				String etype = Entitlement_Type.getText();
				
				//Valid From:
				WebElement Valid_From = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[4]/div"));
				String vfrom = Valid_From.getText();
				
				//Valid To:
				WebElement Valid_To = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[5]/div"));
				String vto = Valid_To.getText();
				
				//Days:
				WebElement Days = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[6]/div"));
				String days = Days.getText();
				System.out.println("Leave Type: "+ltype+"\tEntitlement Type: "+etype+"\tValid From: "+vfrom+"\tValid To: "+vto+"\tDays: "+days);
				
			} else {
				System.out.println("Leave Entitlement Record Not Found:");
			}

	}
	
	@Then("admin logut from application")
	public void admin_logut_from_application() {
		
		// === Admin Logout ===
		WebElement admininfo = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		admininfo.click();	
	
		WebElement adminlogout = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		adminlogout.click();
	}

//--------------------------------------------------------------------------------------------------------------	
	/*Scenario: New Employee Verifying New Entitlement Days*/
	@Given("new employee login with their username {string} and password {string}")
	public void new_employee_login_with_their_username_and_password(String newusname1, String newuspass1) {
		
		// === New Employee Login ===
		//Username Field:
		WebElement uname1 = driver.findElement(By.name("username"));
		uname1.sendKeys(newusname1);

		//Password Field:
		WebElement psswrd1 = driver.findElement(By.name("password"));
		psswrd1.sendKeys(newuspass1);

		//Login Button:
		WebElement loginbtn1 = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginbtn1.click();
		
		//Verify Dashboard Screen Appeared:
		WebElement dashbrdappear = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));		
		String dbapper = dashbrdappear.getText();
		assertEquals(dbapper, "Dashboard", "Dashboard Screen Not Viewed:");
		
		//Printing New Employee Name:
		WebElement usernametxt = driver.findElement(By.xpath("//div[@class='oxd-topbar-header-userarea']/ul/li/span/p"));
		String unametext = usernametxt.getText();
		System.out.println("\nNew Employee Name: "+unametext);
	}	
	
	@Given("new employee verify the leave entitlement days added")
	public void new_employee_verify_the_leave_entitlement_days_added() throws InterruptedException {
		
		/*Select [Leave Module] - For Check Leave Entitlement*/	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Leave Module:
		WebElement leave = driver.findElement(By.xpath("(//div[@class='oxd-sidepanel-body']/ul[@class='oxd-main-menu']/li)[1]"));
		leave.click();
		
		//Entitlement Menu:
		WebElement entitle = driver.findElement(By.xpath("(//nav[@class='oxd-topbar-body-nav']/ul/li)[3]"));
		entitle.click();
		
		//Entitlement Menu DropDown Lists:
		List<WebElement> myentitle = driver.findElements(By.xpath("//li[@class='--active oxd-topbar-body-nav-tab --parent']/ul/li/a"));
			for (WebElement entitle_list : myentitle) {
				String entitle_dd = entitle_list.getText();
					if (entitle_dd.equals("My Entitlements")) {
						entitle_list.click();
						break;
					}
			}	
		Thread.sleep(2000);
		WebElement myleavetxt = driver.findElement(By.xpath("//h5[@class='oxd-text oxd-text--h5 oxd-table-filter-title']"));
		String mletext = myleavetxt.getText();

			if (mletext.equals("My Leave Entitlements")) {
				//Total Label:
				WebElement totalleave = driver.findElement(By.xpath("//div[@class='orangehrm-header-container']/span"));
				String totalleavedays = totalleave.getText();
					if (totalleavedays.equals("Total 0.00 Day(s)")) {
						System.out.println("No Leave Entitlements Days Added:");
					} else {
						System.out.println("Leave Entitlements Days Added:");
					}
					
			} else {
				System.out.println("My Leave Entitlements Not Appeared:");
			}
	}
	
	@Given("new employee logout from application")
	public void new_employee_logout_from_application() {
		
		// === New Employee Logout ===
		WebElement newuserInfo = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		newuserInfo.click();	
	
		WebElement newuserlogout = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		newuserlogout.click();
	}
	
}
