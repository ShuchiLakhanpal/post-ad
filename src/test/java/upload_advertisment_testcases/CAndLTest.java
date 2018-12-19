package upload_advertisment_testcases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import upload_advertisment_config.DataConfig;
import upload_advertisment_pageobjects.AdDetails;
import upload_advertisment_pageobjects.CAndL;
import upload_advertisment_pageobjects.CheckLogIn;
import upload_advertisment_pageobjects.LogInPage;
import upload_advertisment_pageobjects.UserHomePage;

public class CAndLTest extends BaseTest{
	
	CheckLogIn openLogIn;
	LogInPage logInPage;
	UserHomePage homePage;
	CAndL cAndL;
	AdDetails adDetails;
	
	@BeforeMethod
	public void setUp() {
			 initialSetUp();
			 openLogIn = new CheckLogIn();
			 //logInPage = new LogInPage();
			 logInPage = openLogIn.logInPage();
	//		 logInPage = new LogInPage();
			 homePage = logInPage.userDetails(DataConfig.getDataProp("email"), DataConfig.getDataProp("password")); 
			 cAndL = new CAndL();
			 cAndL = homePage.openClasses();
			 adDetails = new AdDetails();
	}
	
	
	@Test(priority = 1)
	public void openAdDetails() {
		adDetails = cAndL.getDetailsPage();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.urlToBe("https://www.kijiji.ca/p-post-ad.html?categoryId=4"));
	}
	
	@AfterTest 
	 public void tearDown() {
		 driver.quit();
	 }
}




