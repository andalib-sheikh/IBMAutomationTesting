package com.ibm.pages;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.JScrollBar;

import org.apache.commons.math3.analysis.function.Min;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.google.inject.Key;
import com.ibm.base.BasePage;
import com.ibm.utils.ElementUtil;
import com.ibm.utils.Timeout;

public class CareerPage extends BasePage{

	@FindBy(xpath="//a[@href='professionals/']")
	WebElement btnExperienceProfessionals;
	
	@FindBy(xpath="//a[@href='#jobs']")
	WebElement btnSeeAllJobs;
	
	@FindBy(xpath="//input[@id='search-area']")
	WebElement inputJobTitle;
	
	@FindBy(xpath="//select[@id='jobcategory-selector']")
	WebElement inputJobCategory;
	
	@FindBy(xpath="//select[@id='experiencelevel-selector']")
	WebElement inputExperienceLevel;

	@FindBy(xpath="//select[@id='view-number-selector']")
	WebElement inputView;
	
	@FindBy(xpath="//span[contains(text(),'Apply Now')]")
	WebElement btnApplyNow;
	
	@FindBy(xpath="//input[@id='fileupload']")
	WebElement inputResumeUpload;
	
	@FindBy(xpath="//input[@name='firstName']")
	WebElement inputFirstName;
	
	@FindBy(xpath="//select[@id='string64_73']")
	WebElement inputExperience;
	
	@FindBy(xpath="//input[@type='search']")
	WebElement inputAreaOfInterest;
	
	@FindBy(xpath="//input[@id='string64_31']")
	WebElement inputCommunitiesOfInterest;
	
	@FindBy(xpath="//select[@id='string64_87']")
	WebElement inputPreferredLanguage;
	
	@FindBy(xpath="//select[@id='string64_32']")
	WebElement inputPrimarySkillSet;
	
	@FindBy(xpath="//select[@id='country']")
	WebElement inputPrimaryCountry;
	
	
	ElementUtil elementUtil;
	public String text;
	
	public CareerPage()
	{
		PageFactory.initElements(driver, this);
		elementUtil=new ElementUtil(driver);
	}
	
	public String test01(String JobTitle, String JobCategory, String ExperienceLevel, String View, String Experience, String PreferredLangauge, String PrimarySkills, String PrimaryCountry) throws InterruptedException 
	{
		driver.findElement(By.xpath("//div[contains(text(),'Accept Default')]")).click();
		try{
		btnExperienceProfessionals.click();
		}
		catch(Exception e)
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();",btnExperienceProfessionals);
		}
		elementUtil.waitForElementToBeVisible(btnSeeAllJobs);
		btnSeeAllJobs.click();
		elementUtil.waitForElementToBeVisible(inputJobCategory);
		Select select = new Select(inputJobCategory);
		select.selectByVisibleText(JobCategory);
		select = new Select(inputExperienceLevel);
		select.selectByVisibleText(ExperienceLevel);
		select = new Select(inputView);
		select.selectByVisibleText(View);
		inputJobTitle.sendKeys(JobTitle);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[contains(text(),'"+JobTitle+"')]")).click();
		Thread.sleep(1000);
		String mainWindowHandle = driver.getWindowHandle();
		Set<String> windowHandles=driver.getWindowHandles();
		Iterator<String> iterator = windowHandles.iterator();
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
                if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
            }
        }
        elementUtil.waitForElementToBeVisible(btnApplyNow);
        btnApplyNow.click();
        elementUtil.waitForElementToBeVisible(inputResumeUpload);


        ((RemoteWebDriver)driver).setFileDetector(new LocalFileDetector());
		System.out.println("SSSSSSSSSSSSSSSSSSSSS "+System.getProperty("user.dir"));
		File file = new File("./src/main/java/TestData/AS_Resume.pdf"); 
	    System.out.println("file exists: " + file.exists());

	    String filePath = file.getAbsolutePath();
		//String filePath = System.getProperty("user.dir")+ "/src/main/java/TestData/AS_Resume.pdf"; 
	    inputResumeUpload.sendKeys(filePath);
	   select=new Select(inputExperience);
	   select.selectByVisibleText(Experience);
	   inputAreaOfInterest.sendKeys(JobCategory);
	   action.sendKeys(Keys.ENTER).perform();
	   JavascriptExecutor js = (JavascriptExecutor) driver;
	   js.executeScript("arguments[0].scrollIntoView();", inputCommunitiesOfInterest);
       try 
       {
    	   inputCommunitiesOfInterest.click();
       }
       catch(ElementClickInterceptedException e)
       {
    	   js.executeScript("arguments[0].click();", inputCommunitiesOfInterest);
       }
       select=new Select(inputPreferredLanguage);
	   select.selectByVisibleText(PreferredLangauge);
	   select=new Select(inputPrimarySkillSet);
	   select.selectByVisibleText(PrimarySkills);
	   select=new Select(inputPrimaryCountry);
	   select.selectByVisibleText(PrimaryCountry);
       Thread.sleep(5000);
       text="Success";
       return text;
	}
}
