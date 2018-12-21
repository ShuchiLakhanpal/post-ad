package upload_advertisment_testcases;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import upload_advertisment_config.DataConfig;
import upload_advertisment_excelreader.ExcelReader;
import upload_advertisment_pageobjects.AdDetails;
import upload_advertisment_pageobjects.CAndL;
import upload_advertisment_pageobjects.CancelPaymentPage;
import upload_advertisment_pageobjects.CheckLogIn;
import upload_advertisment_pageobjects.LogInPage;
import upload_advertisment_pageobjects.UserHomePage;

public class AdDetailsTest extends BaseTest {

	CheckLogIn openLogIn;
	LogInPage logInPage;
	UserHomePage homePage;
	CAndL cAndL;
	AdDetails adDetails;
	ExcelReader reader;
	CancelPaymentPage cancelPayment;

	@BeforeMethod
	public void setUp() {
		initialSetUp();
		openLogIn = new CheckLogIn();
		logInPage = openLogIn.logInPage();
		homePage = logInPage.userDetails(DataConfig.getDataProp("email"), DataConfig.getDataProp("password"));
		cAndL = new CAndL();
		cAndL = homePage.openClasses();
		adDetails = new AdDetails();
		adDetails = cAndL.getDetailsPage();
		cancelPayment = new CancelPaymentPage();
	}

	@Test(dataProvider = "fillFields")
	public void clickFreeRadioBtn(String getTitle, String getDescription, String getCode) throws InterruptedException {
		boolean verifyBtn = adDetails.clickRadioBtn();
		Assert.assertTrue(verifyBtn, "Btn Match");
		Thread.sleep(3000);

		String verifyImg = adDetails.uploadImage();
		String classDisplayed = "image";
		Assert.assertEquals(verifyImg, classDisplayed, "not same");

		adDetails.detailsToEnter(getTitle, getDescription, getCode);

		adDetails.clickCheckBox();
		// Assert.a

		adDetails.selectDays();

		adDetails.clickPostAd();

		cancelPayment.adPublished();
	}

	@DataProvider(name = "fillFields")
	public Iterator<Object[]> enterDataDetailsTest() {
		reader = new ExcelReader("./src/test/resources/testdata/PragraAdvertisement.xlsx");
		return reader.getData().iterator();
	}

	@AfterTest
	public void tearDown() {

		driver.quit();
	}
}
