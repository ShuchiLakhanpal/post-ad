package upload_advertisment_pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import upload_advertisment_testcases.BaseTest;

public class LogInPage extends BaseTest{
	
	//@FindBy(how = How.ID, using = "#LoginEmailOrNickname")

	@FindBy(name= "emailOrNickname")
	WebElement email;
	
	@FindBy(how = How.ID, using = "login-password")
	WebElement password;
	
	@FindBy(how = How.ID, using = "SignInButton")
	WebElement signIn;
	
	public LogInPage() {
		PageFactory.initElements(driver,this);
	}
	
	public String checkTitle() {
		return driver.getTitle();
	}
	
	public UserHomePage userDetails(String userEmail, String userPassword) {
		//Thread.sleep(2000);
		email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		signIn.click();
		return new UserHomePage();
	}
	
//	public HomePage homePage() {
//		signIn.click();
//		return new HomePage();
//	}

}
