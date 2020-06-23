package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {
	
	//public AppiumDriver
	
	//constructor
	public HomePage(AppiumDriver driver) 
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//Page object factory
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Preference']")
	public WebElement Preference;
	
	

}
