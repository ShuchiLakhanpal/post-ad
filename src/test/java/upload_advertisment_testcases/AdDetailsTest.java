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
	static ExcelReader reader = new ExcelReader(SelectorsData.getProperty("filename"));
	CancelPaymentPage cancelPayment;
	public static int currentSheet;
	public static int currSheet = -1;

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

	@Test(dataProvider = "fillFields", invocationCount = 1) // invocationCount should be equivalent to number of sheets
	public void clickFreeRadioBtn(String getTitle, String getDescription, String getCode) throws InterruptedException {
		boolean verifyBtn = adDetails.clickRadioBtn();
		//Thread.sleep(3000);
		//String verifyImg = adDetails.uploadImage();

		adDetails.detailsToEnter(getTitle, getDescription, getCode);

		adDetails.clickCheckBox();

		adDetails.selectDays();
		
		adDetails.clickPostAd();

		cancelPayment.adPublished();
	}

	@DataProvider(name = "fillFields")
	public Iterator<Object[]> enterDataDetailsTest() {
		currSheet++;
		//System.out.println("current sheet"+ currSheet);
		return ExcelReader.getData(currSheet).iterator();
	}

	@AfterTest(enabled = true)
	public void tearDown() {
		driver.quit();
	}
}