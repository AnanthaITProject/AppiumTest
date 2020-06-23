package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {
	
	public AndroidDriver<AndroidElement> driver;
	
	public FormPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver=driver;
	}
	
	@AndroidFindBy(id="android:id/text1")
	public WebElement DropDown;
	
	
	
	public AndroidElement Scroll(String text)
	{
		return driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
	}
	
	@AndroidFindBy(xpath="//*[@text='Brazil']")
	public WebElement Brazil;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	public WebElement NameField;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	public WebElement FemaleRadioButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement LetsShopButton;

}
