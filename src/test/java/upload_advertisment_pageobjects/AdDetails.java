package upload_advertisment_pageobjects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import upload_advertisment_config.SelectorsData;
import upload_advertisment_excelreader.ExcelReader;
import upload_advertisment_testcases.BaseTest;
import upload_advertisment_utils.Utility;

public class AdDetails extends BaseTest {

	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(how = How.XPATH, using = "//div//span[@class='radio-label']")
	List<WebElement> radioBtnFree;

	@FindBy(how = How.XPATH, using = "//input[@type='file']")
	WebElement uploadImgs;
	// @FindAll(@FindBy(how = How.XPATH, using =
	// "//ol[@id='MediaUploadedImages']//li"))
	// List<WebElement> uploadImgs;

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

	static String pathFile = SelectorsData.getProperty("staticPath");

	public AdDetails() {
		PageFactory.initElements(driver, this);
	}

	public boolean clickRadioBtn() {
		radioBtnFree = new ArrayList();
		radioBtnFree = driver.findElements(By.xpath("//div//span[@class='radio-label']"));
		// System.out.println(radioBtnFree.size());
		WebElement free = null;
		boolean checkLabel;
		for (WebElement getRadio : radioBtnFree) {
			// System.out.println(getRadio.getText());
			if (getRadio.getText().equals("Please Contact")) {
				if (!(getRadio.isSelected())) {
					// System.out.println("clicked");
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

	/*@SuppressWarnings("unlikely-arg-type")
	 * uses Auto it script for execution to upload multiple images
	public String uploadImage() throws InterruptedException {
		int x = uploadImgs.getLocation().getX();
		if (x != 0) {
			// System.out.println(uploadImgs.isDisplayed());
			((JavascriptExecutor) driver).executeScript("scroll(0,650);");
			// Thread.sleep(3000);
			String nameOfSheet = ExcelReader.getActiveSheet();
			// System.out.println(nameOfSheet);
			String fromSelector = SelectorsData.getProperty("courseSelectionQA").trim();
			String fromSelectorBA = SelectorsData.getProperty("courseSelectionBA").trim();
			String fromSelectorDev = SelectorsData.getProperty("courseSelectionDev").trim();
			String fromSelectorAWS = SelectorsData.getProperty("courseSelectionAWS").trim();
			String fromSelectorML = SelectorsData.getProperty("courseSelectionML").trim();

			driver.findElement(By.id("ImageUploadButton")).click();
			// folder name should be similar to pathFile + courseSelectionName.toLowerCase()
			// + "Images" so that the following code works
			if (nameOfSheet.contentEquals(fromSelector)) {
				System.out.println(pathFile + fromSelector.toLowerCase() + "Images");
				Utility.uploadFiles(pathFile + fromSelector.toLowerCase() + "Images");
			} else if (nameOfSheet.contentEquals(fromSelectorBA)) {
				Utility.uploadFiles(pathFile + fromSelectorBA.toLowerCase() + "Images");
			} else if (nameOfSheet.contentEquals(fromSelectorDev)) {
				Utility.uploadFiles(pathFile + fromSelectorDev.toLowerCase() + "Images");
			}else if (nameOfSheet.contentEquals(fromSelectorAWS)) {
				Utility.uploadFiles(pathFile + fromSelectorAWS.toLowerCase() + "Images");
			}else if (nameOfSheet.contentEquals(fromSelectorML)) {
				Utility.uploadFiles(pathFile + fromSelectorML.toLowerCase() + "Images");
			}
			Thread.sleep(1000);
		}
		WebElement imgUploaded = driver
				.findElement(By.xpath("//li[@class= 'thumbnail selected']//div[@class='image' ]"));
		String checkImg = imgUploaded.getAttribute("class");
		// System.out.println(checkImg);
		return checkImg;
	}
*/
	
	
	public void detailsToEnter(String title, String description, String code) throws InterruptedException {
		this.title.sendKeys(title);
		this.description.sendKeys(description);
		boolean checkPath = false;
		try {
			if (checkPath = driver.findElement(By.xpath("//select[@name = 'postingLocation']")).isDisplayed()) {
				System.out.println(checkPath);
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
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("downshift-0-item-0")));
				driver.findElement(By.id("downshift-0-item-0")).click();
			//System.out.println(code);
		} else if (checkPath == false) {
			js.executeScript("scroll(0, 1100);");
			this.code.clear();
			this.code.sendKeys(code);
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("downshift-0-item-0")));
				driver.findElement(By.id("downshift-0-item-0")).click();
			//System.out.println(code);
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

}
