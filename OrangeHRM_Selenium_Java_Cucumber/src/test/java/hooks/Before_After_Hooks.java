package hooks;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class Before_After_Hooks {

    public static WebDriver driver;

    @BeforeAll
    public static void openBrowser() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--start-maximized");
        driver = new ChromeDriver(option);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterAll
    public static void quitBrowser() {
            driver.quit();
    }
}
