package Project;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.FormPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Ecommerce extends Base{
	
	@BeforeTest
	public void killAppium() throws IOException, InterruptedException
	{
		killAppiumMethod();
	}

	@Test
	public void ValidateTotal() throws InterruptedException, IOException
	{
		System.out.println("Clone project added");
	
		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities("GeneralAppStore");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Test Case 2: Fist page fill in and proceed to product page
		
		FormPage fp = new FormPage(driver);
		fp.DropDown.click();
		//driver.findElement(By.id("android:id/text1")).click();
		fp.Scroll("Brazil");
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"));");
		fp.Brazil.click();
		//driver.findElement(By.xpath("//*[@text='Brazil']")).click();
		fp.NameField.sendKeys("James Bond Page Object");
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("James Bond");
		driver.hideKeyboard();
		fp.FemaleRadioButton.click();
		//driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();
		fp.LetsShopButton.click();
		//driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		
		CartPage cp = new CartPage(driver);
		cp.AddCartButton.get(0).click();
		cp.AddCartButton.get(1).click();
		cp.CartButton.click();
		//driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(0).click();
		//driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(1).click();
		//driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(3000);	
		
		CheckoutPage ChekP = new CheckoutPage(driver);
		double SumAmount = 0; 
		int Count = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
		System.out.println(Count);
		for (int i = 0; i < Count; i++) 
		{
			
			String amount = ChekP.getProductList().get(i).getText();
			double amountValue = getAmount(amount);
			System.out.println("Item Price: "+amountValue);
			Thread.sleep(2000);
			System.out.println("I No: "+i);
			SumAmount = SumAmount+ amountValue;
			System.out.println("Sum Amount: "+SumAmount);
		}
		
		System.out.println("Sum Amount: "+SumAmount);
		
		String SysAmount = ChekP.TotalAmountLabel.getText();
		SysAmount = SysAmount.substring(1);
		double SysAmountValue = Double.parseDouble(SysAmount);
		System.out.println("System Sum Amount: "+SysAmountValue);
		Assert.assertEquals(SumAmount, SysAmountValue);
		service.stop();
		
	}
	

	
	public static double getAmount(String value)
	{
		value = value.substring(1);
		double amountValue = Double.parseDouble(value);
		return amountValue;
	}

}


