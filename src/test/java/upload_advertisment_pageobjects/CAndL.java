package upload_advertisment_pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import upload_advertisment_config.SelectorsData;
import upload_advertisment_testcases.BaseTest;

public class CAndL extends BaseTest {

	@FindBy(xpath = "//div[@class = 'layout-0 breadcrumbLayout clearfix']//a[@class= 'post-ad-begin']")
	WebElement postAd;

	public CAndL() {
		PageFactory.initElements(driver, this);
	}

	public AdDetails getDetailsPage() {
		try {
			if (postAd.isDisplayed()) {
				// WebDriverWait wait = new WebDriverWait(driver, 20);
				// wait.until(ExpectedConditions.elementToBeClickable(postAd));
				driver.navigate().to("https://www.kijiji.ca/" + SelectorsData.getProperty("adDetailsPage"));
				// postAd.click();

			} else if (driver.findElement(By.xpath(
					"//div[contains(text(), 'Add a profile photo to help bring the Kijji community together!')]"))
					.isDisplayed()) {
				driver.findElement(By.xpath("//button[contains(text(), 'Later')]")).click();
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.elementToBeClickable(postAd));
				postAd.click();

			}
		} catch (Exception e) {
			e.getMessage();
		}
		return new AdDetails();
	}
}
