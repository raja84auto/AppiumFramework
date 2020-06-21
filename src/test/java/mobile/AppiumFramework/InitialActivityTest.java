 package mobile.AppiumFramework;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class InitialActivityTest extends Base {
	
public static void main(String[] args) throws MalformedURLException {
		
		AndroidDriver<AndroidElement> driver = capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Locators - xpath, id, classname, androidUIautomator
		// driver.findElementByXPath("//android.widget.TextView[@text='Media']").click();;
		
	}

}
