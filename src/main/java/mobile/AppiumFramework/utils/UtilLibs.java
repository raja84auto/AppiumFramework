package mobile.AppiumFramework.utils;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class UtilLibs {
	
	AndroidDriver<AndroidElement>  driver;
	
	public UtilLibs(AndroidDriver<AndroidElement> driver) {
		this.driver=driver;
		
	}
	
	public void scrollIntoView(String text) {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));").click();;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
