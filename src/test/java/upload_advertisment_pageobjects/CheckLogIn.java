package upload_advertisment_pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import upload_advertisment_testcases.BaseTest;

public class CheckLogIn extends BaseTest{
	
	//define page factory or object repository
	@FindBy(xpath= "//a[contains(text(),'Sign In')]")
	//@FindBy(how = How.XPATH, using = "//a[contains(text(),'Sign In')]")
	WebElement signInBtn ;
	
	public CheckLogIn() {
		 PageFactory.initElements(driver, this);
	}
	
	public LogInPage logInPage() {
		signInBtn.click();
		return new LogInPage();
	}

}
