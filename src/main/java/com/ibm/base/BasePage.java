package com.ibm.base;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ibm.utils.Timeout;

public class BasePage {

	public enum Browser
	{
		Edge
	}
	public static WebDriver driver;
	public static Properties prop;
	
	public BasePage()
	{
		try 
		{
			prop=new Properties();
			FileInputStream fs=new FileInputStream("./src/main/java/com/ibm/config/config.properties");
			prop.load(fs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	/*public static void initialization()
	{
		System.setProperty("webdriver.edge.driver",prop.getProperty("edgeDriverPath"));
		driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Timeout.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Timeout.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get("https://www.ibm.com/in-en/employment/");
	}*
	
	public static void initialization() throws MalformedURLException
	{
		MutableCapabilities sauceOptions=new MutableCapabilities();
		sauceOptions.setCapability("name", "IBM Automation Tests");
		DesiredCapabilities cap= new DesiredCapabilities();
		cap.setCapability("sauce:options", sauceOptions);
		cap.setCapability("platformName", "Windows 10");
		cap.setCapability("browserName", "microsoftedge");
		cap.setCapability("browserVersion", "92");
		
		
		System.setProperty("webdriver.edge.driver","build/msedgedriver");
		String huburl="https://oauth-andalib.sheikh22062-25b67:b486ce09-bf02-4979-a32e-4d81788dc344@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
		driver=new RemoteWebDriver(new URL(huburl),cap);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Timeout.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Timeout.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.navigate().to(prop.getProperty("url"));
	}
	
}
