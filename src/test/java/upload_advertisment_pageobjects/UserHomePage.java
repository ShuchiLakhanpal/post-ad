package upload_advertisment_pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import upload_advertisment_config.SelectorsData;
import upload_advertisment_testcases.BaseTest;

public class UserHomePage extends BaseTest {
	@FindBy(xpath = "//div[@class='root-2243122828']//button//div[@class='root-46216517 color-blue-2052828905 avatar-26778576']")
	WebElement userName;

	@FindBy(how = How.ID, using = "cat-menu-item-1")
	WebElement communityLink;

	public UserHomePage() {
		PageFactory.initElements(driver, this);
	}

	public boolean getUserName() {
		return userName.isDisplayed();

	}

	public CAndL openClasses() {
		boolean checkLinkVisible = false;
		String urlCommunityLink = "";
		try {
			if(checkLinkVisible = communityLink.isDisplayed()) {
				communityLink.click();
				driver.findElement(By.cssSelector("div#cat-menu-group-1 > ul.menuListL2-1858403043 > li:nth-child(4) > a")).click();
				//driver.navigate().to("https://www.kijiji.ca/"+ SelectorsData.getProperty("toPostAd"));
				checkLinkVisible = true;
			}
		}catch(Exception e) {
			e.getMessage();
		}
		if(checkLinkVisible = true) {
			driver.navigate().to("https://www.kijiji.ca/"+ SelectorsData.getProperty("toPostAd"));
		}
		
		return new CAndL();
	}

}
