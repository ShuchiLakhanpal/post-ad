package upload_advertisment_testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import upload_advertisment_config.DriverConfig;
import upload_advertisment_drivermanager.DriverManager;

public class BaseTest {
	
	public static WebDriver driver ;
	
	
	
	public static void initialSetUp() {
		driver = DriverManager.getInstance();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		 driver.get(DriverConfig.getProperty("url"));
	}
	
	/*@AfterTest
	public void tearDown() {
		if(driver != null) {
		 driver.close();
		}
	 }*/
}
