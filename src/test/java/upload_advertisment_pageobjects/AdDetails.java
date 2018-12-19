package upload_advertisment_pageobjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import upload_advertisment_testcases.BaseTest;

public class AdDetails extends BaseTest {

	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@FindBy(how = How.XPATH, using = "//div//span[@class='radio-label']")
	List<WebElement> radioBtnFree;
	/* @FindBy(xpath="//label//span[@class = 'radio-label' and text()= 'Free']")
	 WebElement radioBtnFree;
*/
	@FindBy(how = How.XPATH, using = "//div//input[@type='file']")
	WebElement uploadImgs;
	
	@FindBy(how = How.ID, using="postad-title" )
	WebElement title;
	
	
	@FindBy(how = How.ID, using= "pstad-descrptn")
	WebElement description;
	
	
	@FindBy(xpath = "//textarea[@id='location']")
	WebElement code;
	
	@FindBy(css =".day-selector.calculate-total")
	WebElement dropDown;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement postAd;
	
	public AdDetails() {
		PageFactory.initElements(driver, this);
	}

	public boolean clickRadioBtn()  {
		radioBtnFree = new ArrayList();
		radioBtnFree = driver.findElements(By.xpath("//div//span[@class='radio-label']"));
		System.out.println(radioBtnFree.size());
		WebElement free = null;
		boolean checkLabel;
		for (WebElement getRadio : radioBtnFree) {		
			//System.out.println(getRadio.getText());
				if (getRadio.getText().equals("Please Contact")) {
					if(!(getRadio.isSelected())) {
					System.out.println("clicked");
					//getRadio.click();
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

	public String uploadImage() throws InterruptedException  {
		int x =uploadImgs.getLocation().getX();
		if(x != 0) {
			System.out.println(uploadImgs.isDisplayed());
			((JavascriptExecutor) driver).executeScript("scroll(0,550);");
			//Thread.sleep(3000);
			String imgPath = "C:\\Users\\shuchi\\Desktop\\kijiji_automation\\Ad Images\\www.PRAGRA.co.png";
			uploadImgs.sendKeys(imgPath);
			Thread.sleep(3000);
		}
		WebElement imgUploaded = driver.findElement(By.xpath("//li[@class= 'thumbnail selected']//div[@class='image' ]"));
		String checkImg = imgUploaded.getAttribute("class");
		System.out.println(checkImg);	
		return checkImg;
	}
	
	public void detailsToEnter(String title, String description, String code) throws InterruptedException  {
		this.title.sendKeys(title);
		this.description.sendKeys(description);
		Thread.sleep(2000);
		if(!driver.findElement(By.xpath("//select[@name = 'postingLocation']")).isDisplayed()) {
			int xCode =this.code.getLocation().getX();
			js.executeScript("scroll(0, 1000);");
			this.code.sendKeys(code);
			driver.findElement(By.xpath("//div[@class='autocompleteSuggestions-3802522726']//div[text()='Mississauga, ON L5M']")).click();
			System.out.println(xCode);
		}else {
			clickCheckBox();
		}

	}
	
	public void clickCheckBox() {
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement topAdCheck = driver.findElement(By.xpath("//label[@for='TopAdInPromoteMyAds']"));
		js.executeScript("arguments[0].scrollIntoView()",topAdCheck);
				if(topAdCheck.isDisplayed()) {
					topAdCheck.click();
					selectDays();
				}
		// }
	}
	
	public void selectDays() {
		Select numDays = new Select(dropDown);
		System.out.println(dropDown.getTagName());
		numDays.selectByVisibleText("3 days");
	}
	
	public CancelPaymentPage clickPostAd() {
		int x = postAd.getLocation().getX();
		if(x != 0) {
			postAd.click();
		}
		return new CancelPaymentPage();
	}
		
}
	
	