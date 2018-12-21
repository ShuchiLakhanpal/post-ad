package upload_advertisment_testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import upload_advertisment_config.DataConfig;
import upload_advertisment_pageobjects.UserHomePage;
import upload_advertisment_pageobjects.LogInPage;
import upload_advertisment_pageobjects.CAndL;
import upload_advertisment_pageobjects.CheckLogIn;

public class UserHomePageTest extends BaseTest {
	CheckLogIn openLogIn;
	LogInPage logInPage;
	UserHomePage homePage;
	CAndL cAndL;

	@BeforeMethod
	public void setUp() {
		initialSetUp();
		openLogIn = new CheckLogIn();
		logInPage = openLogIn.logInPage();
		homePage = logInPage.userDetails(DataConfig.getDataProp("email"), DataConfig.getDataProp("password"));
		cAndL = new CAndL();
	}

	@Test(priority = 1)
	public void checkUserName() {
		Assert.assertTrue(homePage.getUserName(), "Correct username not displayed");
		//System.out.println("passed First run");
	}

	@Test(priority = 2)
	public void clickClasses() {
		homePage.openClasses();
		String getTextClasses = "Classes & Lessons in Mississauga / Peel Region";
		String actual = driver.findElement(By.cssSelector("div.breadcrumb > span >h1")).getText();
		Assert.assertEquals(actual, getTextClasses, "Incorrect Page displayed");
		//System.out.println("Classes and Lessons text page displayed: " + actual);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
