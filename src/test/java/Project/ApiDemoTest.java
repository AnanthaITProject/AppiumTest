package Project;
import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.Preferences;
import PageObjects.TestData;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ApiDemoTest extends Base{

	//public static void main(String[] args) throws InterruptedException, IOException {
		
	@Test(dataProvider="InputData",dataProviderClass=TestData.class)
	public void PageObjectMain(String input) throws IOException, InterruptedException
	{
		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities("ApiDemo");
		
		//Locator: xpath, id, className
		//Xpath
		HomePage hp = new HomePage(driver);
		Preferences p = new Preferences(driver);
		//driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Preference']")).click();
		hp.Preference.click();
		//driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		p.Dependencies.click();
		driver.findElementById("android:id/checkbox").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		
		driver.findElementByClassName("android.widget.EditText").sendKeys(input);
		driver.findElementById("android:id/button1").click();
		service.stop();

	}



}
