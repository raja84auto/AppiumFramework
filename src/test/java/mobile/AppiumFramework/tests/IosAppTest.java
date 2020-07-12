package mobile.AppiumFramework.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import mobile.AppiumFramework.Base;
import mobile.AppiumFramework.utils.VideoRecorderUtils;



public class IosAppTest extends Base {
	
	VideoRecorderUtils videoRecorder = null;
	
	@BeforeTest
	public void KillNodes() throws IOException, InterruptedException {
		KillAllNodes();
	}
	
	
	@Test
	public void uiCatalogTestl() throws Exception {
		
		service = startAppiumServer();
		videoRecorder.startRecord("IOS App Test - iPhone 8 Simulator");
		Thread.sleep(5000);
		
		IOSDriver<IOSElement> driver = capabilities();
		driver.findElementByAccessibilityId("Alert Views").click();
		driver.findElementByXPath("//*[@value='Text Entry']").click();
		driver.findElementByClassName("XCUIElementTextField").sendKeys("Welcome to Automation Test :) ");
		driver.findElementByName("OK").click();
		Thread.sleep(5000);
		
		videoRecorder.stopRecord();
		service.stop();
	} 
	

}
