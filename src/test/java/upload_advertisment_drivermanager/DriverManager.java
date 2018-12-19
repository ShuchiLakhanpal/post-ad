package upload_advertisment_drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import upload_advertisment_config.DriverConfig;

public class DriverManager {
	
	protected static WebDriver driver;
	
	private DriverManager() {
		System.setProperty("webdriver.chrome.driver", DriverConfig.getProperty("path"));
		//driver = new ChromeDriver();
		if(DriverConfig.getProperty("type").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(DriverConfig.getProperty("type").equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}
	}
	
	public static WebDriver getInstance() {
		if(driver == null) {
			new DriverManager();
		}
		return driver;
	}
	
	/*public static void closeDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }*/

}
