package mobile.AppiumFramework.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
	
	
	@Override
	public void onTestFailure(ITestResult result) {
		// failed mobile front end test screenshot - code todo
		System.out.println("Listener reult - Test failed" +result.getName()); // failed test method name
		
	}


}
