package mobile.AppiumFramework;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Base {

	public static AndroidDriver<AndroidElement> capabilities() throws MalformedURLException {
		
		// Todo - Add Properties for generics app location and device details
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Mi A1");
		caps.setCapability(MobileCapabilityType.UDID, "35c00a1e9805");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		//caps.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
		
		caps.setCapability("appPackage", "aib.ibank.android");
		caps.setCapability("appActivity", "aib.ibank.android.entrypoint.InitialActivity");
			
		URL url = new URL("http://0.0.0.0:4723/wd/hub");			
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(url, caps);	
		return driver;
	}

}
