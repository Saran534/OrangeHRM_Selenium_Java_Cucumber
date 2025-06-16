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

public class NewEmployee_LeaveApply_StepDef {

	WebDriver driver = Before_After_Hooks.driver;
	static String empid_value;
	
	/*Scenario: Admin User Logged In And Adding New Employee*/
	@Given("admin user logged in from there credentials username {string} and password {string}")
	public void admin_user_logged_in_from_there_credentials_username_and_password(String adusname, String aduspassw) {
		
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
		assertEquals(dbapper, "Dashboard", "Dashboard Screen Not Appeared:");		
		
		WebElement usernametxt = driver.findElement(By.xpath("//div[@class='oxd-topbar-header-userarea']/ul/li/span/p"));
		String unametext = usernametxt.getText();
		System.out.println("Current Admin UserName: "+unametext);
	}
	
	@Given("admin user add new employee from Add Employee screen")
	public void admin_user_add_new_employee_from_add_employee_screen() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		/*Select PIM Module From SideBar Menu Panel*/
		WebElement pimmodule = driver.findElement(By.xpath("//span[text()='PIM']"));	
		pimmodule.click();
		
		// === Adding New User From Add Employee Tab: ===
		//Add Employee Tab:
		WebElement addemp = driver.findElement(By.xpath("//a[text()='Add Employee']"));	
		addemp.click();
		
		//Verifying Add Employee Screen View Appeared
		WebElement addempdiv = driver.findElement(By.xpath("//h6[text()='Add Employee']"));
		String addempdivtxt = addempdiv.getText();
		if (addempdivtxt.equals("Add Employee")) {

			//Add Employee - First Name:
			WebElement fname = driver.findElement(By.name("firstName"));
			fname.sendKeys("Hiroki");
			
			//Add Employee - Last Name:
			WebElement lname = driver.findElement(By.name("lastName"));
			lname.sendKeys("Totoki");
			
			//Add Employee - Employee Id:
			WebElement empid = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
			empid_value = empid.getAttribute("value");
			System.out.println("Employee ID: "+empid_value);
			
			// === Create Login Details ===
			//Toggle Button:
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement cld_togglebtn = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")));
			cld_togglebtn.click();
			
			//CLD Username:
			WebElement unamefield = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]"));
			unamefield.sendKeys("hiroki_totoki");
			
			//CLD Status Radio Button:
			WebElement enableradiobtn = driver.findElement(By.xpath("//label[normalize-space()='Enabled']/input"));
			boolean eradiobtn = enableradiobtn.isSelected();
				if (eradiobtn==true) {
					System.out.println("By Default Radio Button Is Selected On Enabel:");
				} else {
					System.out.println("Radio Buutton Is Not Selected On Enabel:");
				}
			
			//CLD Password:
			WebElement passwordfield = driver.findElement(By.xpath("(//input[@type='password'])[1]"));
			passwordfield.sendKeys("hiroki_totoki123");
			
			//Confirmation Password:
			WebElement cpasswordfield = driver.findElement(By.xpath("(//input[@type='password'])[2]"));
			cpasswordfield.sendKeys("hiroki_totoki123");
			
			//Save Button:
			WebElement savebtn = driver.findElement(By.xpath("//button[normalize-space()='Save']"));
			savebtn.click();
			
			//Saved Successful Toast Message:
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement toastmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='oxd-toaster_1']/div/div/div/p[2]")));
			System.out.println("New Employee: "+toastmsg.getText());
		} else {
			System.out.println("Add Employee Screen Not Viewed:");
		}
	}
	
	@Then("admin user add leave entitlement days for new employee")
	public void admin_user_add_leave_entitlement_days_for_new_employee() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Leave Module Menu From SideBar Menu Panel:
		WebElement leavemodule1 = driver.findElement(By.xpath("//div[@class='oxd-sidepanel-body']/ul[@class='oxd-main-menu']/li/a/span[text()='Leave']"));
		leavemodule1.click();
		
		//Entitlement Menu:
		WebElement entitle1 = driver.findElement(By.xpath("(//nav[@class='oxd-topbar-body-nav']/ul/li)[3]"));
		entitle1.click();
	
		//Add Entitlement Menu DropDown Lists:
		List<WebElement> addentitle = driver.findElements(By.xpath("//li[@class='--active oxd-topbar-body-nav-tab --parent']/ul/li/a"));
		for (WebElement addentitle_dd : addentitle) {
			String addentitle_dd_list = addentitle_dd.getText();
			if (addentitle_dd_list.equals("Add Entitlements")) {
				addentitle_dd.click();
				break;
			}
		}
		
		//Verify Add Entitlement Screen Appeared:
		WebElement addleavetext = driver.findElement(By.xpath("//div[@class='orangehrm-card-container']/p"));
		String add_leave_entitle = addleavetext.getText();
		assertEquals(add_leave_entitle, "Add Leave Entitlement", "Add Leave Entitlement Screen Not Viewed:");
		
		//Add Entitlement - Employee Name:
		WebElement empName = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
		empName.sendKeys("Hiroki");
		
		//Add Entitlement - Employee Name Lists:
		List<WebElement> empnameDD = driver.findElements(By.xpath("//div[@class='oxd-autocomplete-wrapper']/div[@role='listbox']/div/span"));
		for (WebElement empName_dd : empnameDD) {
			String dd_empname = empName_dd.getText();
			if (dd_empname.equals("Hiroki Totoki")) {
				empName_dd.click();
				break;
			}
		}
		
		//Add Entitlement - Leave Type:
		WebElement leave_type = driver.findElement(By.xpath("//div[@class='oxd-select-text oxd-select-text--active'][1]/div[text()='-- Select --']"));
		leave_type.click();
		
		//Add Entitlement - Leave Type Lists:
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
		
		//Save Button:
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
		System.out.println("Leave Entitlement Days: "+toastmsg1.getText());
		
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
		
		//Leave Record Table:
		WebElement record_Found = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[2]"));
		String RecordsFound = record_Found.getText();
			if (RecordsFound.equals("(1) Record Found")) {
				
				//Leave Record Table - Leave Type:
				WebElement Leave_Type = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[2]/div"));
				String ltype = Leave_Type.getText();
				
				//Leave Record Table - Entitlement Type:
				WebElement Entitlement_Type = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[3]/div"));
				String etype = Entitlement_Type.getText();
				
				//Leave Record Table - Valid From:
				WebElement Valid_From = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[4]/div"));
				String vfrom = Valid_From.getText();
				
				//Leave Record Table - Valid To:
				WebElement Valid_To = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[5]/div"));
				String vto = Valid_To.getText();
				
				//Leave Record Table - Days:
				WebElement Days = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div/div[6]/div"));
				String days = Days.getText();
				System.out.println("Leave Type: "+ltype+"\tEntitlement Type: "+etype+"\tValid From: "+vfrom+"\tValid To: "+vto+"\tDays: "+days);
				
			} else {
				System.out.println("Leave Entitlement Record Not Found:");
			}
	}
	
	@Then("admin user logged out")
	public void admin_user_logged_out() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// ===Admin Logout ===
		WebElement admininfo1 = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		admininfo1.click();	

		WebElement adminlogout1 = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		adminlogout1.click();	
	}

//--------------------------------------------------------------------------------------------------------------	
	/*Scenario: New Employee Logged In Verify The Entitlement Days And Applying Leave*/
	@Given("new employee logged in from there credentials")
	public void new_employee_logged_in_from_there_credentials() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === New Employee Login ===
		//Username:
		WebElement uname2 = driver.findElement(By.name("username"));
		uname2.sendKeys("hiroki_totoki");

		//Password:
		WebElement psswrd2 = driver.findElement(By.name("password"));
		psswrd2.sendKeys("hiroki_totoki123");

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
	
	@Given("new employee navigates to leave module and verify the entitlement days")
	public void new_employee_navigates_to_leave_module_and_verify_the_entitlement_days() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Selecting Leave Module From SideBar Menu Panel:
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
		assertEquals(mletext2, "My Leave Entitlements", "My Leave Entitlements Screen Not Viewed:");
		
		//Total Label:
		WebElement totalleave1 = driver.findElement(By.xpath("//div[@class='orangehrm-header-container']/span"));
		String totalleavedays1 = totalleave1.getText();	
			if (totalleavedays1.equals("Total 0.00 Day(s)")) {
				System.out.println("No Leave Entitlements Days Added:");
			} else {
				System.out.println("Leave Entitlements Days Added:");
			}
	}
	
	@Then("new employee applying leave")
	public void new_employee_applying_leave() {
		
		try {
			
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
					
					//Calendar Interactions:
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
					
					//Leave Type:
					WebElement leaveType_dd1 = driver.findElement(By.xpath("//div[@class='oxd-select-wrapper']/div/div[@class='oxd-select-text-input']"));
					leaveType_dd1.click();
					
					//Leave Type Menu Lists:
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
				
				/*After Applying Leave From Apply Leave, Need To Verify Applied Leave Record Appears On My Leave List*/
				WebElement myleavetab = driver.findElement(By.xpath("(//div[@class='oxd-topbar-body']/nav[@role='navigation']/ul/li)[2]"));
				myleavetab.click();
				
				Thread.sleep(2000);
				//My Leave Record Table:
				WebElement myleaveRecord1 = driver.findElement(By.xpath("//div[@class='orangehrm-paper-container']/div/span"));
				String MyLeave_Record1 = myleaveRecord1.getText();
				
					if (MyLeave_Record1.equalsIgnoreCase("(1) Record Found")) {
						
						//My Leave Record Table - Date:
						WebElement Date = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div[@role='row']/div[2]/div"));
						String date = Date.getText();
						
						//My Leave Record Table - Employee Name:
						WebElement EmployeeName = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div[@role='row']/div[3]/div"));
						String empName = EmployeeName.getText();
						
						//My Leave Record Table - Leave Type:
						WebElement LeaveType = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div[@role='row']/div[4]/div"));
						String leaveType = LeaveType.getText();
						
						//My Leave Record Table - Leave Balance Days:
						WebElement LeaveBalancedays = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div[@role='row']/div[5]/div"));
						String leaveBalance = LeaveBalancedays.getText();
						
						//My Leave Record Table - Number Of Days:
						WebElement NumberofDays = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div[@role='row']/div[6]/div"));
						String numofdays = NumberofDays.getText();
						
						//My Leave Record Table - Status:
						WebElement Status = driver.findElement(By.xpath("//div[@class='oxd-table-card']/div[@role='row']/div[7]/div"));
						String status = Status.getText();
						
						System.out.println("Date: "+date+
											"\tEmployee Name: "+empName+
											"\tLeave Type: "+leaveType+
											"\tLeave Balance (Days): "+leaveBalance+
											"\tNumber of Days: "+numofdays+
											"\tStatus: "+status);
					} else {
						System.out.println("No Leave Record Found:");
					}
	
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	@Then("new employee logged out")
	public void new_employee_logged_out() {
		
		// === New Employee Logout ===
		WebElement newuserInfo = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		newuserInfo.click();	

		WebElement newuserlogout = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		newuserlogout.click();
	}
	
}
