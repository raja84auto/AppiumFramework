package mobile.AppiumFramework.tests;

import java.net.MalformedURLException;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import mobile.AppiumFramework.Base;



public class IosAppTest extends Base {
	
	// Todo
	@Test
	public void uiCatalogTestl() throws Exception {
		
		IOSDriver<IOSElement> driver = capabilities();
		driver.findElementByAccessibilityId("Alert Views").click();
		driver.findElementByXPath("//*[@value='Text Entry']").click();
		driver.findElementByClassName("XCUIElementTextField").sendKeys("AutoTest");
		driver.findElementByName("OK").click();

		
	} 
	

}
