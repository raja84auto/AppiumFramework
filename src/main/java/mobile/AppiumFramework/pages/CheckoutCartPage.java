package mobile.AppiumFramework.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutCartPage {

	AndroidDriver<AndroidElement> driver;
	
	public CheckoutCartPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	public List<WebElement> productList;
		
	//driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement totalAmount;
		
	public void validateCartAmount() {
		int count=productList.size();
		double sum=0;
		for(int i=0;i<count;i++){
			String amount1= productList.get(i).getText();
			double amount=getAmount(amount1);
			sum=sum+amount;//280.97+116.97
		}
		
		System.out.println(sum+"sum of products");
		String total=totalAmount.getText();
		total= total.substring(1);
		double totalValue=Double.parseDouble(total);
		System.out.println(totalValue+"Total value of products");
		Assert.assertEquals(sum, totalValue); 
		
	}
	
	public static double getAmount(String value) {
		value= value.substring(1);
		double amount2value=Double.parseDouble(value);
		return amount2value;
	}
	
		
}
