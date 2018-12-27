package upload_advertisment_testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import upload_advertisment_config.DriverConfig;
import upload_advertisment_pageobjects.LogInPage;
import upload_advertisment_pageobjects.CheckLogIn;

public class CheckLogInTest extends BaseTest{
	CheckLogIn openLogIn;
	LogInPage logInPage;
 
	public CheckLogInTest() {
		super();
	}
	
	 
	 @BeforeMethod
	 public void setUp() {
		 initialSetUp();
		 openLogIn = new CheckLogIn();//object is created so that we can access the non static methods in OpenLogInPage.java
	 }
	 
	 @Test(priority=1)
	 public void clickSignInBtn() {
		 logInPage = openLogIn.logInPage();
		 String expectedUrl = "https://www.kijiji.ca/t-login.html";
		 Assert.assertEquals(driver.getCurrentUrl(),expectedUrl, "Incorrect title");
	
	 }
	
	 @AfterTest
	 public void tearDown() {
		 driver.quit();
	 }

}
