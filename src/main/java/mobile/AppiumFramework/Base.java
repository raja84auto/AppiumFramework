package mobile.AppiumFramework;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {

	public static AndroidDriver<AndroidElement> driver = null;
	public static IOSDriver<IOSElement> iosDriver = null;
	public static AppiumDriverLocalService service = null;
	boolean serverFlag = false;
	
	public AppiumDriverLocalService startAppiumServer() {
		serverFlag = checkIfServerIsRunnning(4723);
		if(!serverFlag) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		
		return service;
	}
	
	public static boolean checkIfServerIsRunnning(int port) {

	    boolean isServerRunning = false;
	    ServerSocket serverSocket;
	    try {
	        serverSocket = new ServerSocket(port);
	        serverSocket.close();
	    } catch (IOException e) {
	        //If control comes here, then it means that the port is in use
	        isServerRunning = true;
	    } finally {
	        serverSocket = null;
	    }
	    return isServerRunning;
	}
	
	public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException {
		
		Properties props = new Properties();
		// /Users/rajamac/eclipse-workspace/AppiumFramework/src/main/java/Properties/global.properties
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Properties/global.properties");
		props.load(fis);
		String getApp = (String) props.get(appName);
		String getDevice = (String) props.getProperty("androidEmulator");
		
		File appDir = new File("apk");
		File apkFile = new File(appDir, getApp);
		
		DesiredCapabilities caps = new DesiredCapabilities();		
		
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, getDevice);
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		caps.setCapability(MobileCapabilityType.APP, apkFile.getAbsolutePath());
		driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), caps);		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					
		
		/* 
		 * Android - Real device
		 * 		
		 * 
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
	
	
	// Todo
	public static IOSDriver<IOSElement>  capabilities() throws MalformedURLException {
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.1");
		// ios and iphone version 10.2+
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest"); // 
		caps.setCapability(MobileCapabilityType.APP, "//Users//rajamac//Desktop//UICatalog.app");  
		
		URL url = new URL("http://0.0.0.0:4723/wd/hub");
		iosDriver = new IOSDriver<IOSElement>(url, caps);
		iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS );
		
		return iosDriver;		


	} 

	

}
