package upload_advertisment_testcases;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import upload_advertisment_config.DataConfig;
import upload_advertisment_config.SelectorsData;
import upload_advertisment_excelreader.ExcelReader;
import upload_advertisment_listeners.ScreenshotListener;
import upload_advertisment_pageobjects.AdDetails;
import upload_advertisment_pageobjects.CAndL;
import upload_advertisment_pageobjects.CancelPaymentPage;
import upload_advertisment_pageobjects.CheckLogIn;
import upload_advertisment_pageobjects.LogInPage;
import upload_advertisment_pageobjects.UserHomePage;

@Listeners(ScreenshotListener.class)
public class AdDetailsTest extends BaseTest {

	CheckLogIn openLogIn;
	LogInPage logInPage;
	UserHomePage homePage;
	CAndL cAndL;
	AdDetails adDetails;
	static ExcelReader reader;
	CancelPaymentPage cancelPayment;
	public static int currentSheet;

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

	@Test(dataProvider = "fillFields", invocationCount = 3) // invocationCount should be equivalent to number of sheets
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

		currentSheet++;
	
		adDetails.clickPostAd();

		if (ExcelReader.getNumSheets == 1) {
			System.out.println("Number of sheets is 1 ");
			driver.close();
		} else if (currentSheet > 0 && currentSheet == ExcelReader.getNumSheets) {
			 

			ExcelReader.getData();
		}

		cancelPayment.adPublished();
	}

	@DataProvider(name = "fillFields")
	public Iterator<Object[]> enterDataDetailsTest() {
		reader = new ExcelReader(SelectorsData.getProperty("filename"), currentSheet);
		//ExcelReader.getNumberSheets();
		return ExcelReader.getData().iterator();
	}

	@AfterTest(enabled = true)
	public void tearDown() {

		driver.quit();
	}
}