package upload_advertisment_pageobjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import upload_advertisment_config.SelectorsData;
import upload_advertisment_testcases.BaseTest;

public class AdDetails extends BaseTest {

	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(how = How.XPATH, using = "//div//span[@class='radio-label']")
	List<WebElement> radioBtnFree;
	/*
	 * @FindBy(xpath="//label//span[@class = 'radio-label' and text()= 'Free']")
	 * WebElement radioBtnFree;
	 */
	@FindBy(how = How.XPATH, using = "//div//input[@type='file']")
	WebElement uploadImgs;

	@FindBy(how = How.ID, using = "postad-title")
	WebElement title;

	@FindBy(how = How.ID, using = "pstad-descrptn")
	WebElement description;

	@FindBy(xpath = "//textarea[@id='location']")
	WebElement code;

	@FindBy(css = ".day-selector.calculate-total")
	WebElement dropDown;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement postAd;

	@FindBy(xpath = "//a[@class='cancel']")
	WebElement cancelPayment;

	public AdDetails() {
		PageFactory.initElements(driver, this);
	}

	public boolean clickRadioBtn() {
		radioBtnFree = new ArrayList();
		radioBtnFree = driver.findElements(By.xpath("//div//span[@class='radio-label']"));
		//System.out.println(radioBtnFree.size());
		WebElement free = null;
		boolean checkLabel;
		for (WebElement getRadio : radioBtnFree) {
			// System.out.println(getRadio.getText());
			if (getRadio.getText().equals("Please Contact")) {
				if (!(getRadio.isSelected())) {
					//System.out.println("clicked");
					free = driver.findElement(By.xpath("//div//span[@class='radio-label'and text()='Please Contact']"));
					WebDriverWait checkBtnClicked = new WebDriverWait(driver, 2000);
					getRadio.click();
					checkBtnClicked.until(ExpectedConditions.visibilityOf(free));
					break;
				}
			}

		}
		checkLabel = free.isDisplayed();
		return checkLabel;
	}

	public String uploadImage() throws InterruptedException {
		int x = uploadImgs.getLocation().getX();
		if (x != 0) {
			System.out.println(uploadImgs.isDisplayed());
			((JavascriptExecutor) driver).executeScript("scroll(0,550);");
			// Thread.sleep(3000);
			if(SelectorsData.getProperty("courseSelection").equalsIgnoreCase("QA")) {
				String imgPath = SelectorsData.getProperty("qaImg");
			    uploadImgs.sendKeys(imgPath);
			}else if(SelectorsData.getProperty("courseSelection").equalsIgnoreCase("BA")) {
				String imgPath = SelectorsData.getProperty("baImg");
			    uploadImgs.sendKeys(imgPath);
			}
			Thread.sleep(3000);
		}
		WebElement imgUploaded = driver
				.findElement(By.xpath("//li[@class= 'thumbnail selected']//div[@class='image' ]"));
		String checkImg = imgUploaded.getAttribute("class");
		//System.out.println(checkImg);
		return checkImg;
	}

	public void detailsToEnter(String title, String description, String code) throws InterruptedException {
		this.title.sendKeys(title);
		this.description.sendKeys(description);
		//Thread.sleep(2000);
		boolean checkPath = false;
		try {
			if (checkPath = driver.findElement(By.xpath("//select[@name = 'postingLocation']")).isDisplayed()) {
				checkPath = true;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		if (checkPath == true) {
			js.executeScript("scroll(0, 1100);");
			driver.findElement(By.xpath("//button[text()='Change']")).click();
			this.code.clear();
			this.code.sendKeys(code);

			if (code.equalsIgnoreCase("L5M")) {
				String getLocation = SelectorsData.getProperty("mississaugaLocation");
				this.code.clear();
				this.code.sendKeys(getLocation);
			} else if (code.equalsIgnoreCase("L6S")) {
				String getLocation = SelectorsData.getProperty("bramptonLocation");
				this.code.clear();
				this.code.sendKeys(getLocation);
			}
		} else if (driver.findElement(By.xpath("//h3[text() = 'Location']")).isDisplayed()) {
			js.executeScript("scroll(0, 1100);");
			if (code.equalsIgnoreCase("L5M")) {
				String getLocation = SelectorsData.getProperty("mississaugaLocation");
				this.code.clear();
				this.code.sendKeys(getLocation);
				WebDriverWait wait = new WebDriverWait(driver, 5000);
				WebElement locationDisplay = driver.findElement(By.xpath("//textarea[@name='location']"));
				wait.until(ExpectedConditions.textToBePresentInElement(locationDisplay,SelectorsData.getProperty("mississaugaLocation")));
			} else if (code.equalsIgnoreCase("L6S")) {
				String getLocation = SelectorsData.getProperty("bramptonLocation");
				this.code.clear();
				this.code.sendKeys(getLocation);
				WebDriverWait wait = new WebDriverWait(driver, 5000);
				WebElement locationDisplay = driver.findElement(By.xpath("//textarea[@name='location']"));
				wait.until(ExpectedConditions.textToBePresentInElement(locationDisplay,SelectorsData.getProperty("bramptonLocation")));
			}
		}

	}

	public void clickCheckBox() {
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement topAdCheck = driver.findElement(By.xpath("//label[@for='TopAdInPromoteMyAds']"));
		js.executeScript("arguments[0].scrollIntoView()", topAdCheck);
		if (topAdCheck.isDisplayed()) {
			topAdCheck.click();
		}
	}

	public void selectDays() {
		Select numDays = new Select(dropDown);
		// System.out.println(dropDown.getTagName());
		numDays.selectByVisibleText("3 days");
	}

	public CancelPaymentPage clickPostAd() {
		int x = postAd.getLocation().getX();
		if (x != 0) {
			postAd.click();
		}
		return new CancelPaymentPage();
	}

	/*
	 * public DisplayAd adPublished() { //cancelPayment.click(); return new
	 * DisplayAd(); }
	 */

}
