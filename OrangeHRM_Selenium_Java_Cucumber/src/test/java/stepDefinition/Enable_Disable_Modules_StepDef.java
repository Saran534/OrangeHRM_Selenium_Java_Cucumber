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

public class Enable_Disable_Modules_StepDef {

	WebDriver driver = Before_After_Hooks.driver;

	/*Scenario: Admin user disable the all enabled modules*/
	@Given("admin login with there credentials username {string} and password {string}")
	public void admin_login_with_there_credentials_username_and_password(String adusname, String aduspassw) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// === Admin Login ====
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
		
		//Printing Username:
		WebElement usernametxt = driver.findElement(By.xpath("//div[@class='oxd-topbar-header-userarea']/ul/li/span/p"));
		String unametext = usernametxt.getText();
		System.out.println("Current Admin UserName: "+unametext);
	}
	
	@Given("admin verifying default enabled modules")
	public void admin_verifying_default_enabled_modules() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Printing SideBar Menu Panel Enabled Modules Names:
		List<WebElement> sideMenus = driver.findElements(By.xpath("//div[@class='oxd-sidepanel-body']/ul/li/a/span"));
		System.out.println("\nCurrently Enabled Side MenuPanel Modules:");	
			for (WebElement sidemenupanel : sideMenus) {
					String sidemenuslist = sidemenupanel.getText();
					System.out.println(sidemenuslist);
				}
	}
	
	
	@Given("admin navigates to admin module")
	public void admin_navigates_to_admin_module() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Selecting Admin Module From SideBar Menu Panel:
		WebElement adminModule = driver.findElement(By.xpath("//div[@class='oxd-sidepanel-body']/ul/li/a/span[text()='Admin']"));
		adminModule.click();
		
		//Configuration Menu:
		WebElement configurationTab = driver.findElement(By.xpath("(//nav[@class='oxd-topbar-body-nav']/ul/li)[7]"));
		configurationTab.click();
		
		//Configuration Menu DropDown Lists:
		List<WebElement> configur_DDList = driver.findElements(By.xpath("//ul[@class='oxd-dropdown-menu']/li/a"));
		for (WebElement configuredd_List : configur_DDList) {
			String configList = configuredd_List.getText();
				if (configList.equals("Modules")) {
					configuredd_List.click();
					break;
				}
		}
	}
	
	@Given("admin disable the all enabled modules")
	public void admin_disable_the_all_enabled_modules() {
		
		//Module Configuration Label:
		WebElement modules_screen = driver.findElement(By.xpath("//div[@class='orangehrm-card-container']/h6"));
		String modulepage = modules_screen.getText();
			if (modulepage.equals("Module Configuration")) {
			
			 //Admin Module:
			 WebElement admin_module = driver.findElement(By.xpath("(//div[@class='oxd-form-row']/div/div/div/label/span)[1]"));
			 admin_module.click();
			 
			 //PIM Module:
			 WebElement pim_module = driver.findElement(By.xpath("(//div[@class='oxd-form-row']/div/div/div/label/span)[2]"));
			 pim_module.click();
			 
			 //Leave Module:
			 WebElement leave_module = driver.findElement(By.xpath("(//div[@class='oxd-form-row']/div/div/div/label/span)[3]"));
			 leave_module.click();
			 
			 //Time Module:
			 WebElement time_module = driver.findElement(By.xpath("(//div[@class='oxd-form-row']/div/div/div/label/span)[4]"));
			 time_module.click();
			 
			 //Recruitment Module:
			 WebElement recruitment_module = driver.findElement(By.xpath("(//div[@class='oxd-form-row']/div/div/div/label/span)[5]"));
			 recruitment_module.click();
			 
			 //Performance Module:
			 WebElement performance_module = driver.findElement(By.xpath("(//div[@class='oxd-form-row']/div/div/div/label/span)[6]"));
			 performance_module.click();
			 
			 //Directory Module:
			 WebElement directory_module = driver.findElement(By.xpath("(//div[@class='oxd-form-row']/div/div/div/label/span)[7]"));
			 directory_module.click();
			 
			 //Maintance Module:
			 WebElement maintenance_module = driver.findElement(By.xpath("(//div[@class='oxd-form-row']/div/div/div/label/span)[8]"));
			 maintenance_module.click();
			 
			 //Mobile Module:
			 WebElement mobile_module = driver.findElement(By.xpath("(//div[@class='oxd-form-row']/div/div/div/label/span)[9]"));
			 mobile_module.click();
			 
			 //Claim Module:
			 WebElement claim_module = driver.findElement(By.xpath("(//div[@class='oxd-form-row']/div/div/div/label/span)[10]"));
			 claim_module.click();
			 
			 //Buzz Module:
			 WebElement buzz_module = driver.findElement(By.xpath("(//div[@class='oxd-form-row']/div/div/div/label/span)[11]"));
			 buzz_module.click();
			 
			 //Buzz Module:
			 WebElement saveBtn = driver.findElement(By.xpath("//div[@class='oxd-form-actions']/button"));
			 saveBtn.click();
			
			//Success Toast Message:
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement toastmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='oxd-toaster_1']/div/div/div/p[2]")));
			System.out.println("Modules: "+toastmsg.getText());
			 
			} else {
				System.out.println("Module Configuration Screen Not Appeared:");
			}
	}
	
	
	@Given("admin verifying enabled modules are disabled")
	public void admin_verifying_enabled_modules_are_disabled() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().refresh();
		
		//After Printing SideBar Menu Modules Names:
		List<WebElement> sideMenus = driver.findElements(By.xpath("//div[@class='oxd-sidepanel-body']/ul/li/a/span"));
		System.out.println("\nCurrently Enabled Side MenuPanel Modules:");	
			for (WebElement sidemenupanel : sideMenus) {
					String sidemenuslist = sidemenupanel.getText();
					System.out.println(sidemenuslist);
				}
	}
	
	@Then("admin logout from orangehrm application")
	public void admin_logout_from_orangehrm_application() {
		
		// === Admin Logout ===
		WebElement admininfo = driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));	
		admininfo.click();	
	
		WebElement adminlogout = driver.findElement(By.xpath("//li/a[normalize-space()='Logout']"));
		adminlogout.click();			
	}
	
}
