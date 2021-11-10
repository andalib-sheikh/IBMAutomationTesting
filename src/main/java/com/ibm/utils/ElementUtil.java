package com.ibm.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
	}
	
	public WebElement waitForElementToBeVisible(WebElement el)
	{
		WebDriverWait wait=new WebDriverWait(driver, Timeout.EXPLICIT_WAIT);
		return wait.until(ExpectedConditions.visibilityOf(el));
	}
}