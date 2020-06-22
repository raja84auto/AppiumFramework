package mobile.AppiumFramework.tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import mobile.AppiumFramework.Base;
import mobile.AppiumFramework.pages.CheckoutPage;
import mobile.AppiumFramework.pages.HomePage;
import mobile.AppiumFramework.pages.ProductsPage;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;


public class EcommerceTest extends Base{

	@Test
	public void validateTotal() throws IOException, InterruptedException {

		AndroidDriver<AndroidElement> driver=capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		HomePage homePage = new HomePage(driver);
		homePage.fillHomePage();
		
		//ProductsPage productsPage = new ProductsPage(driver);		
		
		//CheckoutPage checkoutPage = new CheckoutPage(driver);
		
		
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(4000);

		int count=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
		double sum=0;
		for(int i=0;i<count;i++){
			String amount1= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
			double amount=getAmount(amount1);
			sum=sum+amount;//280.97+116.97
		}

		System.out.println(sum+"sum of products");
		String total=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		total= total.substring(1);
		double totalValue=Double.parseDouble(total);
		System.out.println(totalValue+"Total value of products");
		Assert.assertEquals(sum, totalValue); 
	}

	public static double getAmount(String value) {
		value= value.substring(1);
		double amount2value=Double.parseDouble(value);
		return amount2value;
	}
	
}
