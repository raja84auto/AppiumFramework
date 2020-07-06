package mobile.AppiumFramework.tests;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import mobile.AppiumFramework.Base;
import mobile.AppiumFramework.pages.CheckoutCartPage;
import mobile.AppiumFramework.pages.HomePage;
import mobile.AppiumFramework.pages.ProductsPage;
import mobile.AppiumFramework.utils.VideoRecorderUtils;


public class AndroidAppTest extends Base{

	VideoRecorderUtils videoRecorder = null;
	HomePage homePage = null;
	ProductsPage productsPage = null;
	CheckoutCartPage checkoutCartPage = null;
	
	public AndroidAppTest() {
		
	}
	
	@Test
	public void validateTotal() throws Exception {
		
		videoRecorder.startRecord("Android App - Test - Android Emulator");

		AndroidDriver<AndroidElement> driver=capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Home page started...!");
		Thread.sleep(5000); // For video demo purpose, sleep will be removed
		
		homePage = new HomePage(driver);
		homePage.fillHomePage();
		System.out.println("Home page filled sucessfully., and navigating to products page...!");
		Thread.sleep(5000);
				
		productsPage = new ProductsPage(driver);
		productsPage.addProducts();
		System.out.println("Products selected from products page...and navigating to checkout page...!");	
		Thread.sleep(5000);	
		
		checkoutCartPage =  new CheckoutCartPage(driver);
		checkoutCartPage.validateCartAmount();
		System.out.println("Checkout page completed ...!");
		Thread.sleep(5000);
		
		videoRecorder.stopRecord();
	}

	
	
}
