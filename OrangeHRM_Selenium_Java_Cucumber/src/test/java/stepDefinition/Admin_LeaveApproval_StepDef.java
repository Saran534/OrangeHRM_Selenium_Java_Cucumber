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

public class Admin_LeaveApproval_StepDef {
	
	WebDriver driver = Before_After_Hooks.driver;
	static String empid_value;
	
	/*Scenario: Admin User Adding New User And Adding Leave Entitlement Days*/	
	@Given("admin user logged in username {string} and password {string}")
	public void admin_user_logged_in_username_and_password(String adusname, String aduspassw) {
		
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
		
		//Printing Admin Username:
		WebElement usernametxt = driver.findElement(By.xpath("//div[@class='oxd-topbar-header-userarea']/ul/li/span/p"));
		String unametext = usernametxt.getText();
		System.out.println("Current Admin UserName: "+unametext);

	}
	
	@Given("admin user adding new user from Add Employee screen")
	public void admin_user_adding_new_user_from_add_employee_screen() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Select PIM Module From SideBar Menu Panel:
		WebElement pimmodule = driver.findElement(By.xpath("//span[text()='PIM']"));	
		pimmodule.click();
		
		// === Adding New User From Add Employee Menu: ===
		//Add Employee Menu:
		WebElement addemp = driver.findElement(By.xpath("//a[text()='Add Employee']"));	
		addemp.click();
		
		//Verifying Add Employee Screen Appeared:
		WebElement addempdiv = driver.findElement(By.xpath("//h6[text()='Add Employee']"));
		String addempdivtxt = addempdiv.getText();
		if (addempdivtxt.equals("Add Employee")) {

			//Add Employee - First Name:
			WebElement fname = driver.findElement(By.name("firstName"));
			fname.sendKeys("Roy");
			
			//Add Employee - Last Name:
			WebElement lname = driver.findElement(By.name("lastName"));
			lname.sendKeys("Jakobs");
			
			//Add Employee - Employee ID:
			WebElement empid = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
			empid_value = empid.getAttribute("value");
			System.out.println("Employee ID: "+empid_value);
			
			// === Create Login Details ===
			//Toggle Button:
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement cld_togglebtn = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")));
			cld_togglebtn.click();
			
			//Username:
			WebElement unamefield = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]"));
			unamefield.sendKeys("roy_jakobs");
			
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
			passwordfield.sendKeys("roy_jakobs123");
			
			//Confirmation Password:
			WebElement cpasswordfield = driver.findElement(By.xpath("(//input[@type='password'])[2]"));
			cpasswordfield.sendKeys("roy_jakobs123");
			
			//Save Button:
			WebElement savebtn = driver.findElement(By.xpath("//button[normalize-space()='Save']"));
			savebtn.click();
			
			//Saved Successful Toast Message:
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement toastmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='oxd-toaster_1']/div/div/div/p[2]")));
			System.out.println("New Employee: "+toastmsg.getText());
		} else {
			System.out.println("Add Employee Screen Not Appeared:");
		}

	}
	
	@Then("admin user leave entitlement days for new user")
	public void admin_user_leave_entitlement_days_for_new_user() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Select Leave Module Menu From SideBar Menu Panel:
		WebElement leavemodule1 = driver.findElement(By.xpath("//div[@class='oxd-sidepanel-body']/ul[@class='oxd-main-menu']/li/a/span[text()='Leave']"));
		leavemodule1.click();
		
		//Entitlement Menu:
		WebElement entitle1 = driver.findElement(By.xpath("(//nav[@class='oxd-topbar-body-nav']/ul/li)[3]"));
		entitle1.click();
		
		//Add Entitlement Menu:
		WebElement addentitle = driver.findElement(By.xpath("(//nav[@class='oxd-topbar-body-nav']/ul/li)[3]/ul/li[1]"));
		addentitle.click();
		
		//Verify Add Leave Entitlement Screen Appeared:
		WebElement addleavetext = driver.findElement(By.xpath("//div[@class='orangehrm-card-container']/p"));
		String add_leave_entitle = addleavetext.getText();
		assertEquals(add_leave_entitle, "Add Leave Entitlement", "Add Leave Entitlement Screen Not Viewed:");
		
		//Add Entitlement - Employee Name:
		WebElement empName = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
		empName.sendKeys("Roy");
		
		//Add Entitlement - Employee Name DropDown Lists:
		List<WebElement> empnameDD = driver.findElements(By.xpath("//div[@class='oxd-autocomplete-wrapper']/div[@role='listbox']/div/span"));
		for (WebElement empName_dd : empnameDD) {
			String dd_empname = empName_dd.getText();
			if (dd_empname.equals("Roy Jakobs")) {
				empName_dd.click();
				break;
			}
		}
		
		//Add Employee - Leave Type:
		WebElement leave_type = driver.findElement(By.xpath("//div[@class='oxd-select-text oxd-select-text--active'][1]/div[text()='-- Select --']"));
		leave_type.click();
		
		//Add Employee - Leave Type Lists:
		List<WebElement> leavetype_DD = driver.findElements(By.xpath("//div[@class='oxd-select-wrapper']/div[@role='listbox']/div/span"));
		for (WebElement leave_type_dd : leavetype_DD) {
			String leavetype_dd = leave_type_dd.getText();
				if (leavetype_dd.equalsIgnoreCase("CAN - Personal")) {
					leave_type_dd.click();
					break;
				}
		}
		
		//Entitlement Field:
		WebElement entitlement1 = driver.findElement(By.xpath("(//div[@class='oxd-grid-3 orangehrm-full-width-grid'])[3]/div[3]/div/div[2]/input"));
		entitlement1.sendKeys("12");
		
		//Entitlement Save Button:
		WebElement savebtn11 = driver.findElement(By.xpath("(//div[@class='oxd-form-actions']/button)[2]"));
		savebtn11.click();
		
		//Update Entitlement Dialog Pop-Up:
		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dialogmsg = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='document']/div/p)[1]")));
		String dialogtext = dialogmsg.getText();
		System.out.println(dialogtext);
		
		//Update Entitlement Dialog Pop-Up Save Button:
		WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dialogmsg_savebtn = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='document']/div[@class='orangehrm-modal-footer']/button)[2]")));
		dialogmsg_savebtn.click();
		
		//Success Toast Message:
		WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement toastmsg1 = wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='oxd-toaster_1']/div[@aria-live='assertive']/div[@class='oxd-toast-start']/div[2]/p)[2]")));
		System.out.println("Leave Entitlements Days: "+toastmsg1.getText());
		
		//Verify Leave Entitlement Screen Appeared:
		WebElement leave_entitle = driver.findElement(By.xpath("//h5[@class='oxd-text oxd-text--h5 oxd-table-filter-title']"));
		String lentitle_txt = leave_entitle.getText();
		assertEquals(lentitle_txt, "Leave Entitlements", "Leave Entitlement Screen Not Viewed:");
		
		//Leave Entitlement - Employee Name:
		WebElement le_empName = driver.findElement(By.xpath("//div[@class='oxd-autocomplete-wrapper']/div/input"));
		String EmpName = le_empName.getText();
		
		//Leave Entitlement - Leave Type:
		WebElement le_LeaveType = driver.findElement(By.xpath("(//div[@class='oxd-select-wrapper']/div/div[@class='oxd-select-text-input'])[1]"));
		String LeaveType = le_LeaveType.getText();
		
		//Leave Entitlement - Leave Period:
		WebElement le_LeavePeriod = driver.findElement(By.xpath("(//div[@class='oxd-select-wrapper'])[2]/div/div[1]"));
		String LeavePeriod = le_LeavePeriod.getText();
		System.out.println("EmployeeName: "+EmpName+"\tLeaveType: "+LeaveType+"\tLeavePeriod: "+LeavePeriod);


		WebElement record_Found = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[2]"));
		String RecordsFound = record_Found.getText();
		
			if (RecordsFound.equals("(1) Record Found")) {
				
				WebElement Leave_Type = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[2]/div"));
				String ltype = Leave_Type.getText();
	
				WebElement Entitlement_Type = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[3]/div"));
				String etype = Entitlement_Type.getText();
				
				WebElement Valid_From = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[4]/div"));
				String vfrom = Valid_From.getText();
				
				WebElement Valid_To = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[5]/div"));
				String vto = Valid_To.getText();
				
				WebElement Days = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[6]/div"));
				String days = Days.getText();
				System.out.println("Leave Type: "+ltype+"\tEntitlement Type: "+etype+"\tValid From: "+vfrom+"\tValid To: "+vto+"\tDays: "+days);
				
			} else {
				System.out.println("Leave Entitlement Record Not Found:");
			}
	}
	
	@Then("admin user logout from a orangehrm application")
	public void admin_user_logout_from_a_orangehrm_application() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === Admin Logout ===
		WebElement admininfo1 = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		admininfo1.click();	
	
		WebElement adminlogout1 = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		adminlogout1.click();	
	}

//----------------------------------------------------------------------------------------------------------------	
	/*Scenario: New User Logged In And Verify The Entitlement Days And Applying Leave*/
	@Given("new employee logged in on orangehrm application")
	public void new_employee_logged_in_on_orangehrm_application() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === New Employee Login ===
		//Username:
		WebElement uname2 = driver.findElement(By.name("username"));
		uname2.sendKeys("roy_jakobs");

		//Password:
		WebElement psswrd2 = driver.findElement(By.name("password"));
		psswrd2.sendKeys("roy_jakobs123");

		//Login Button:
		WebElement loginbtn2 = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginbtn2.click();

		//Verify Dashboard Screen Appeared:
		WebElement dashbrdappear2 = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));		
		String dbapper2 = dashbrdappear2.getText();
		assertEquals(dbapper2, "Dashboard", "Dashboard Screen Not Appeared:");

		//Printing Admin Username:
		WebElement login_username2 = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']/p"));
		System.out.println("\nNew Employee Name: "+login_username2.getText());

	}
	
	@Given("new employee navigates to leave module and verify the entitlement days added")
	public void new_employee_navigates_to_leave_module_and_verify_the_entitlement_days_added() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Select Leave Module From SideBar Menu Panel:
		WebElement leave2 = driver.findElement(By.xpath("(//div[@class='oxd-sidepanel-body']/ul[@class='oxd-main-menu']/li)[1]"));
		leave2.click();
		
		//Entitlement Menu:
		WebElement entitle2 = driver.findElement(By.xpath("(//nav[@class='oxd-topbar-body-nav']/ul/li)[3]"));
		entitle2.click();
		
		//Entitlement Menu DropDown Lists:
		List<WebElement> myentitle = driver.findElements(By.xpath("//li[@class='--active oxd-topbar-body-nav-tab --parent']/ul/li/a"));
			for (WebElement entitle_list : myentitle) {
				String entitle_dd = entitle_list.getText();
					if (entitle_dd.equals("My Entitlements")) {
						entitle_list.click();
						break;
					}
			}
		
		//Verify Leave Entitlement Screen Appeared:
		WebElement myleavetxt2 = driver.findElement(By.xpath("//h5[@class='oxd-text oxd-text--h5 oxd-table-filter-title']"));
		String mletext2 = myleavetxt2.getText();
		assertEquals(mletext2, "My Leave Entitlements", "My Leave Entitlements Screen Not Appeared:");
		
		WebElement totalleave1 = driver.findElement(By.xpath("//div[@class='orangehrm-header-container']/span"));
		String totalleavedays1 = totalleave1.getText();
			if (totalleavedays1.equals("Total 0.00 Day(s)")) {
				System.out.println("No Leave Entitlements Days Added:");
			} else {
				System.out.println("Leave Entitlements Days Added:");
			}
	}
	
	@Then("new employee applyed leave")
	public void new_employee_applyed_leave() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement apply_tab = driver.findElement(By.xpath("(//div[@class='oxd-topbar-body']/nav/ul/li)[1]/a"));
		apply_tab.click();
		
		WebElement applylev_label = driver.findElement(By.xpath("//div[@class='orangehrm-card-container']/h6"));
		String applylev_Label = applylev_label.getText();
		
			if (applylev_Label.equals("Apply Leave")) {
				
				WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement leave_balance = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='oxd-grid-item oxd-grid-item--gutters'])[2]/div/div[2]/p")));
				
				WebElement leaveType_dd = driver.findElement(By.xpath("//div[@class='oxd-select-wrapper']/div/div[@class='oxd-select-text-input']"));
				leaveType_dd.click();
				
				List<WebElement> levtype_ddlist = driver.findElements(By.xpath("//div[@class='oxd-select-dropdown --positon-bottom']/div"));
				for (WebElement ddlist : levtype_ddlist) {
					String ddlist_text = ddlist.getText();
						if (ddlist_text.equalsIgnoreCase("CAN - Personal")) {
							ddlist.click();
							break;
						} 
				}
				
				Thread.sleep(2000);
				System.out.println("Before Leave Apply - Leave Balance: "+leave_balance.getText());
				
				// === Calendar Interactions ===
				//From Date Field:
				WebElement fromdate = driver.findElement(By.xpath("(//div[@class='oxd-date-input']/i)[1]"));
				fromdate.click();
				
				//Date:
				WebDriverWait wait6 = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement calendar_date = wait6.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-calendar-wrapper']/div[@class='oxd-calendar-dates-grid']/div/div[text()='13']")));
				calendar_date.click();
				
				//Comment Box:
				WebElement comment_box = driver.findElement(By.xpath("//textarea"));
				comment_box.sendKeys("testing purpose");
				
				//Apply Button:
				WebElement applybtn = driver.findElement(By.xpath("//div[@class='oxd-form-actions']/button"));
				applybtn.click();
				
				//Success Toast Message:
				WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement savetoastmsg = wait7.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='oxd-toaster_1']/div/div/div/p[2]")));
				System.out.println("Leave Applied: "+savetoastmsg.getText());
				
				//Leave Type Field:
				WebElement leaveType_dd1 = driver.findElement(By.xpath("//div[@class='oxd-select-wrapper']/div/div[@class='oxd-select-text-input']"));
				leaveType_dd1.click();
				
				//Leave Type DropDown Lists:
				List<WebElement> levtype_ddlist1 = driver.findElements(By.xpath("//div[@class='oxd-select-dropdown --positon-bottom']/div"));
				for (WebElement ddlist1 : levtype_ddlist1) {
					String ddlist_text1 = ddlist1.getText();
						if (ddlist_text1.equalsIgnoreCase("CAN - Personal")) {
							ddlist1.click();
							break;
						} 
				}
				
				Thread.sleep(2000);
				System.out.println("After Leave Apply - Leave Balance: "+leave_balance.getText());
				
			} else {
				System.out.println("Apply Leave Screen Not Appeared:");
			}
	}
			
	@Then("new employee verify applied leave record found on leave list")
	public void new_employee_verify_applied_leave_record_found_on_leave_list() throws InterruptedException {
		
		/*After Applying Leave From Apply Leave, Need To Verify Applied Leave Record Appears On My Leave List*/
		WebElement myleavetab = driver.findElement(By.xpath("(//div[@class='oxd-topbar-body']/nav[@role='navigation']/ul/li)[2]"));
		myleavetab.click();
		
		Thread.sleep(2000);
		WebElement myleaveRecord1 = driver.findElement(By.xpath("//div[@class='orangehrm-paper-container']/div/span"));
		String MyLeave_Record1 = myleaveRecord1.getText();

			if (MyLeave_Record1.equalsIgnoreCase("(1) Record Found")) {
				
				WebElement Date = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div[@role='row']/div[2]/div"));
				String date = Date.getText();
				
				WebElement EmployeeName = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div[@role='row']/div[3]/div"));
				String empName = EmployeeName.getText();
				
				WebElement LeaveType = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div[@role='row']/div[4]/div"));
				String leaveType = LeaveType.getText();
				
				WebElement LeaveBalancedays = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div[@role='row']/div[5]/div"));
				String leaveBalance = LeaveBalancedays.getText();
				
				WebElement NumberofDays = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div[@role='row']/div[6]/div"));
				String numofdays = NumberofDays.getText();
				
				WebElement Status = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div[@role='row']/div[7]/div"));
				String status = Status.getText();
				
				System.out.println("Date: "+date+
									"Employee Name: "+empName+
									"Leave Type: "+leaveType+
									"Leave Balance (Days): "+leaveBalance+
									"Number of Days: "+numofdays+
									"Status: "+status);
			} else {
				System.out.println("No Leave Record Found:");
			}
	}

	@Then("new employee logout from the ornagehrm application")
	public void new_employee_logout_from_the_ornagehrm_application() {
		
		// === New Employee Logout ===
		WebElement newuserInfo2 = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		newuserInfo2.click();	
	
		WebElement newuserlogout2 = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		newuserlogout2.click();

	}

//------------------------------------------------------------------------------------------------------------------
	/*Scenario: Admin approved the new user applied leave*/
	@Given("admin user logged in")
	public void admin_user_logged_in() {
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// === Admin Login ===
		//Username:
		WebElement uname = driver.findElement(By.name("username"));
		uname.sendKeys("Admin");
		
		//Password:
		WebElement psswrd = driver.findElement(By.name("password"));
		psswrd.sendKeys("admin123");
		
		//Login Button:
		WebElement loginbtn = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginbtn.click();		
		
		//Verify Dashboard Screen Appeared:
		WebElement dashbrdappear = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));		
		String dbapper = dashbrdappear.getText();
		assertEquals(dbapper, "Dashboard", "Dashboard Screen Not Viewed:");		
		
		//Printing Admin Username:
		WebElement usernametxt = driver.findElement(By.xpath("//div[@class='oxd-topbar-header-userarea']/ul/li/span/p"));
		String unametext = usernametxt.getText();
		System.out.println("\nCurrent Admin UserName: "+unametext);
	}
	
	@Given("admin navigates to leave module and approve new user applied leave")
	public void admin_navigates_to_leave_module_and_approve_new_user_applied_leave() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Select Leave Module From SideBar Menu:
		WebElement leavemodule1 = driver.findElement(By.xpath("//div[@class='oxd-sidepanel-body']/ul[@class='oxd-main-menu']/li/a/span[text()='Leave']"));
		leavemodule1.click();
		
		WebElement leavelist_label = driver.findElement(By.xpath("//div[@class='oxd-table-filter-header']/div/h5"));
		String leavelisttxt = leavelist_label.getText();
		
			if (leavelisttxt.equals("Leave List")) {
				
				WebElement employeeName = driver.findElement(By.xpath("(//div[@class='oxd-table-card']/div[@role='row']/div/div)[3]"));
			 	String empName = employeeName.getText();
			 		if (empName.equals("Roy Jakobs")) {
						WebElement status = driver.findElement(By.xpath("(//div[@class='oxd-table-card']/div[@role='row']/div/div)[7]"));
						String Approval_status = status.getText();
						
							if (Approval_status.contains("Pending Approval")) {
								WebElement approvalbtn = driver.findElement(By.xpath("(//div[@class='oxd-table-card']/div[@role='row']/div/div)[9]/button[1]"));
								approvalbtn.click();
							} else {
								System.out.println("Unable To Click Approve Button:");
							}
			 		} else {
			 			System.out.println("NO Record Found:");
					}
		
			} else {
				System.out.println("Leave List Screen Not Appeared:");
			}

	}
	
	@Then("admin logout the orangehrm application")
	public void admin_logout_the_orangehrm_application() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === Admin Logout ===
		WebElement admininfo1 = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		admininfo1.click();	
		
		WebElement adminlogout1 = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		adminlogout1.click();	
	}
	
//-------------------------------------------------------------------------------------------------------------	
	/*Scenario: New Employee Verifying The Applied Leave Approved By Admin*/
	@Given("new employee logged in orangehrm application")
	public void new_employee_logged_in_orangehrm_application() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === New Employee Login ===
		//Username:
		WebElement uname2 = driver.findElement(By.name("username"));
		uname2.sendKeys("roy_jakobs");

		//Password:
		WebElement psswrd2 = driver.findElement(By.name("password"));
		psswrd2.sendKeys("roy_jakobs123");

		//Login Button:
		WebElement loginbtn2 = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginbtn2.click();

		//Verify Dashboard Screen Appeared:
		WebElement dashbrdappear2 = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']"));		
		String dbapper2 = dashbrdappear2.getText();
		assertEquals(dbapper2, "Dashboard", "Dashboard Screen Not Viewed:");

		//Printing New Employee Name:
		WebElement login_username2 = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']/p"));
		System.out.println("\nNew Employee Name: "+login_username2.getText());

	}
	
	@Given("new employee verify the leave has been approved by admin")
	public void new_employee_verify_the_leave_has_been_approved_by_admin() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Leave Menu:
		WebElement leave11 = driver.findElement(By.xpath("(//div[@class='oxd-sidepanel-body']/ul[@class='oxd-main-menu']/li)[1]"));
		leave11.click();
		
		WebElement Status = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div[@role='row']/div[7]/div"));
		String status1 = Status.getText();
			if (status1.contains("Pending Status")) {
				System.out.println("Leave Not Approved By Admin User");
			} else {
				System.out.println("Leave Approved By Admin User");
			}	

	}
	
	@Then("new employee logout application")
	public void new_employee_logout_application() {
		
		// === New Employee Logout ===
		WebElement newempInfo2 = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		newempInfo2.click();	
	
		WebElement newemplogout2 = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		newemplogout2.click();

	}

}
