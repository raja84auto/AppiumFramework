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

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class Base {

	public static AndroidDriver<AndroidElement> driver = null;
	public static IOSDriver<IOSElement> iosDriver = null;
	public static AppiumDriverLocalService service = null;
	boolean serverFlag = false;
	
	
	public AppiumDriverLocalService startAppiumServer() {
		serverFlag = checkIfServerIsRunnning(4723);
		if(!serverFlag) {
			//service = AppiumDriverLocalService.buildDefaultService();
			
			service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js")));
					
//			service = AppiumDriverLocalService
//					.buildService(new AppiumServiceBuilder()
//							.usingDriverExecutable(new File("/usr/local/bin/node"))
//							.withAppiumJS(new File(“/usr/local/lib/node_modules/appium/build/lib/main.js”))
//							.withIPAddress(“127.0.0.1”).usingPort(4723));
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
	
	
	public static void startEmulator() throws IOException, InterruptedException {
		
		// /Users/rajamac/eclipse-workspace/AppiumFramework/src/main/java/resources/startEmulator.command
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"/src/main/java/resources/startEmulator.command");
		Thread.sleep(6000);
		System.out.println("Andoid Emulator has been started");
	}
	

	public void KillAllNodes() throws IOException, InterruptedException {
		// Runtime.getRuntime().exec("taskkill /F /IM node.exe"); // Windows
		// Runtime.getRuntime().exec("fkill -f :4723");
		Runtime.getRuntime().exec("sudo kill -2 $(sudo lsof -t -i:4723"); // Mac
		Thread.sleep(4000);
	}
	
	
	public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException {
		
		Properties props = new Properties();
		// /Users/rajamac/eclipse-workspace/AppiumFramework/src/main/java/Properties/global.properties
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Properties/global.properties");
		props.load(fis);
		String getApp = (String) props.get(appName);
		String getDevice = (String) props.getProperty("androidEmulator");
		
		if(getDevice.contains("AVD")) {
			startEmulator();
			
		}
		
		File appDir = new File("srcApk");
		File apkFile = new File(appDir, getApp);
		
		DesiredCapabilities caps = new DesiredCapabilities();		
		
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, getDevice);
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		caps.setCapability(MobileCapabilityType.APP, apkFile.getAbsolutePath());
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps); // debug
		
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

	
	public static void getScreenShot(String testName) throws IOException {
		File screenFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenFile, new File("./failedTestScreens/defect_"+testName+".png"));
		// FileUtils.copyFile(screenFile, new File(System.getProperty("user.dir")+"/failedTestScreens/defectScreen"+testName+".png"));
		
	}

}
