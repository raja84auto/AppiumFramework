package mobile.AppiumFramework.utils;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import mobile.AppiumFramework.Base;

public class Listeners implements ITestListener {
	
	Base base;
	
	@Override
	public void onTestFailure(ITestResult result) {
		// failed mobile front end test screenshot - code todo
		System.out.println("Listener reult - Test failed" +result.getName()); // failed test method name
		String testName = result.getName();
		try {
			base.getScreenShot(testName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
