package upload_advertisment_pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import upload_advertisment_testcases.BaseTest;

public class CancelPaymentPage extends BaseTest {

	@FindBy(xpath = "//a[@class='cancel']")
	WebElement cancelPayment;

	public CancelPaymentPage() {
		PageFactory.initElements(driver, this);
	}

	public DisplayAd adPublished() {
		cancelPayment.click();
	//	System.out.println("cancelPayment is working");
		return new DisplayAd();
	}

}
