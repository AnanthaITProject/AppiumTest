package Project;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	
	public static AndroidDriver<AndroidElement> driver;
	
	public static void AddOriginalTest()
	{
		System.out.println("Original added here");
	}
	
	public static void killAppiumMethod() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\TaskKiller.bat");
		//Runtime.getRuntime().exec("taskkill /F /IM node.exet");//If got 1 lineof command can execute like this
		Thread.sleep(2000);
	}
	
	//Start Emulator
	public static void startEmulator() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\startEmulator.bat");
		Thread.sleep(6000);
	}
	
	//if port not starting, use this command: taskkill /F /IM node.exe
	//Start Appium
	/*
	 * Dependency needed 
	 * - slf4j-simple
	 * - slf4j-api
	 * - commons-lang3
	 * - commons-io
	 * - commons-validator
	 */
	public static AppiumDriverLocalService service;
	
	public AppiumDriverLocalService startServer()
	{
		boolean flag = checkServerIsRunning(4723);
		if (!flag) 
		{
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		} 
		return service;
	}
	
	public static boolean checkServerIsRunning(int port)
	{
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try 
		{
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} 
		catch (IOException e) 
		{
			isServerRunning = true;
		}
		finally 
		{
			serverSocket = null;
		}
		return isServerRunning;
		
	}
	
	public static AndroidDriver<AndroidElement> Capabilities(String appName) throws IOException, InterruptedException {

		//System.getProperty("user.dir");
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Project\\global.properties");
		prop.load(fis);
		
		File f = new File("src");
		File fs = new File(f, (String) prop.get(appName));

		DesiredCapabilities cap = new DesiredCapabilities();
		//String device = (String) prop.get("emulator");	//data from global.properties
		
		//deviceName was set so can run the emulator type from maven and not by global.properties.
		//maven commad: mvn test -DdeviceName=Nexus5XPie9Emulator
		String device = System.getProperty("deviceName");
		if (device.contains("emulator")) 
		{
			startEmulator();
		} 
		
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		//cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Uiautomator2");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 15);
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		cap.setCapability("chromedriverExecutable", "D:\\AppiumTutorial\\Projects\\Introduction\\BrowserDriver\\Google\\chromedriver.exe");
		//cap.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		}
	
	public static void getScreenshot(String s) throws IOException
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\src\\ScreenShots\\"+s+".png"));
		
	}

}
