package mobile.AppiumFramework.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsPage {
	AndroidDriver<AndroidElement> driver;
	
	public ProductsPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='3. Preference dependencies']")
	public WebElement dependencies;	

	@AndroidFindBy(className="android.widget.Button")
	public List<WebElement> buttons;
	
	@AndroidFindBy(xpath="//*[@text='ADD TO CART']")
	public WebElement AddToCartOne;	
	
	@AndroidFindBy(xpath="//*[@text='ADD TO CART']")
	public WebElement AddToCartTwo;	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement checkoutCart;
	

	public void addProducts() {
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();		
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		checkoutCart.click();			
	}
	
}
