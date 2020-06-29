package mobile.AppiumFramework;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Base {

	public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException {
		
		AndroidDriver<AndroidElement> driver = null;
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Properties\\global.properties");
		props.load(fis);
		String getApp = (String) props.get(appName);
		String getDevice = (String) props.getProperty("device");
		
		File appDir = new File("Apk");
		File apkFile = new File(appDir, getApp);
		
		DesiredCapabilities caps = new DesiredCapabilities();		
		
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, getDevice);
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		caps.setCapability(MobileCapabilityType.APP, apkFile.getAbsolutePath());
		driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), caps);		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		/*
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Mi A1");
		caps.setCapability(MobileCapabilityType.UDID, "35c00axxxxxx"); // 12 chars
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		//caps.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
		
		
			
		URL url = new URL("http://0.0.0.0:4723/wd/hub");			
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(url, caps);
		*/
		
		return driver;
	}

}
