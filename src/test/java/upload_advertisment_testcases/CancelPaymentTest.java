package upload_advertisment_testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import upload_advertisment_config.DataConfig;
import upload_advertisment_excelreader.ExcelReader;
import upload_advertisment_pageobjects.AdDetails;
import upload_advertisment_pageobjects.CAndL;
import upload_advertisment_pageobjects.CancelPaymentPage;
import upload_advertisment_pageobjects.CheckLogIn;
import upload_advertisment_pageobjects.LogInPage;
import upload_advertisment_pageobjects.UserHomePage;

public class CancelPaymentTest extends BaseTest {

	CheckLogIn openLogIn;
	LogInPage logInPage;
	UserHomePage homePage;
	CAndL cAndL;
	AdDetails adDetails;
	ExcelReader reader;
	CancelPaymentPage cancelPaymentPage;

	@BeforeMethod
	public void setUp() {
		initialSetUp();
		openLogIn = new CheckLogIn();
		logInPage = openLogIn.logInPage();
		homePage = logInPage.userDetails(DataConfig.getDataProp("email"), DataConfig.getDataProp("password"));
		cAndL = new CAndL();
		cAndL = homePage.openClasses();
		adDetails = cAndL.getDetailsPage();
		adDetails = new AdDetails();
		cancelPaymentPage = new CancelPaymentPage();

	}

	@Test(enabled = false)
	public void cancelPaymentTest() {
		cancelPaymentPage.adPublished();
		ExcelReader.getData();
	}

	@AfterTest
	public void tearDown() {

		driver.quit();
	}

}
