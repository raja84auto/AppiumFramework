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
import mobile.AppiumFramework.utils.VideoRecorderUtils;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;


public class EcommerceTest extends Base{

	VideoRecorderUtils videoRecorder = null;
	
	@Test
	public void validateTotal() throws Exception {
		
		videoRecorder.startRecord("ECommerceMobileApp - Test - Video");

		AndroidDriver<AndroidElement> driver=capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Hope page started...!");
		Thread.sleep(10000);
		
		HomePage homePage = new HomePage(driver);
		homePage.fillHomePage();
		System.out.println("Home page filled sucessfully., and navigated to next page...!");
		Thread.sleep(10000);
		
		
		//ProductsPage productsPage = new ProductsPage(driver);		
		
		//CheckoutPage checkoutPage = new CheckoutPage(driver);
		
		
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		System.out.println("Products selected from products page...and navigating to next page...!");
		Thread.sleep(10000);
		
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
		System.out.println("Checkout page - sum of products from checkout page...!");
		
		Thread.sleep(10000);		
		System.out.println("");
		System.out.println("########### Test passed and Recorded done ##############");
		Thread.sleep(10000);
		videoRecorder.stopRecord();
	}

	public static double getAmount(String value) {
		value= value.substring(1);
		double amount2value=Double.parseDouble(value);
		return amount2value;
	}
	
}
