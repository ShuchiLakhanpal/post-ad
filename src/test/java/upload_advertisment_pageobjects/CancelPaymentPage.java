package upload_advertisment_pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import upload_advertisment_excelreader.ExcelReader;
import upload_advertisment_testcases.BaseTest;

public class CancelPaymentPage extends BaseTest {

	@FindBy(xpath = "//a[@class='cancel']")
	WebElement cancelPayment;
	
	//static int sheets = ExcelReader.getNumberSheets();related to if else written below but uncertain if required here!!


	public CancelPaymentPage() {
		PageFactory.initElements(driver, this);
	}

	public DisplayAd adPublished() {
		cancelPayment.click();
		/*if (ExcelReader.getNumSheets > 1 && sheets == ExcelReader.getNumSheets) {
			ExcelReader.getData();
		}	*/ //not sure if this is needed	
		return new DisplayAd();
	}

}
