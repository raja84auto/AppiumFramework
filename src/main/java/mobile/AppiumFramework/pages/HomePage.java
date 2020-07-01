package mobile.AppiumFramework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.AppiumFramework.utils.UtilLibs;

public class HomePage {	
	
	AndroidDriver<AndroidElement> driver;
	UtilLibs utilLibs = null;
	
	public HomePage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(id="android:id/text1")
	public WebElement dropdownCountry;
	
	
	@AndroidFindBy(xpath="//*[@text='Algeria']")
	public WebElement selectCountry;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	public WebElement yourName;
	
	@AndroidFindBy(xpath="//*[@text='Female']")
	public WebElement genderFemale;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement btnLetsShop;
	
	
	public void fillHomePage() {
		yourName.sendKeys("AIB Automation Team");
		genderFemale.click();
		dropdownCountry.click();
		utilLibs = new UtilLibs(driver);
		utilLibs.scrollIntoView("Algeria");		
		btnLetsShop.click();
	}
	
	
}
