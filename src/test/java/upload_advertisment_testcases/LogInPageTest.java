package upload_advertisment_testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import upload_advertisment_config.DataConfig;
import upload_advertisment_pageobjects.UserHomePage;
import upload_advertisment_pageobjects.LogInPage;
import upload_advertisment_pageobjects.CheckLogIn;

public class LogInPageTest extends BaseTest{
	
	CheckLogIn openLogIn;
	LogInPage logInPage;
	UserHomePage homePage;
	
	public LogInPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		 initialSetUp();
		 openLogIn = new CheckLogIn();
		// logInPage = new LogInPage();
		 logInPage = openLogIn.logInPage();
//		 logInPage = new LogInPage();
		// homePage = new HomePage();
	}
	
	@Test(priority = 0)
	public void checkLogInPageTitle() {
		String titleLP =  logInPage.checkTitle();
		Assert.assertEquals(titleLP, "Kijiji Canada | Free local classifieds", "Title Not matched");
		System.out.println("Pass if match else fail");
	}
	
	@Test(priority=1)
	public void logInCredentials()  {
		//Thread.sleep(2000);
		
		homePage = logInPage.userDetails(DataConfig.getDataProp("email"), DataConfig.getDataProp("password"));
		System.out.println("Ran");
	}

	
	//@AfterTest
//	 public void tearDown() {
//		if(driver != null) {
//		 driver.close();
//		}
//	 }
	@AfterTest
	 public void tearDown() {
		 driver.quit();
	 }
}
