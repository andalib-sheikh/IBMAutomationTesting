package com.ibm.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ibm.base.BasePage;
import com.ibm.pages.CareerPage;
import com.ibm.utils.ReadExcel;

public class CareerPageTests extends BasePage{
	
	CareerPage adminPage;

	public CareerPageTests() {
		super();
	}
	
	@BeforeClass
	public void setup() throws Exception{
		try {
			initialization();
			adminPage=new CareerPage();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	@AfterClass
	public void tearDown() {
		try {
			Thread.sleep(3000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		driver.quit();
	}
	
	@Test(priority=1, dataProvider="getTest01Data", enabled=true)
	public void test01(String JobTitle, String JobCategory, String ExperienceLevel, String View,String Experience, String PreferredLangauge, String PrimarySkills, String PrimaryCountry) throws Exception
	{
		try {
		adminPage.test01(JobTitle, JobCategory, ExperienceLevel, View, Experience, PreferredLangauge, PrimarySkills, PrimaryCountry);
		Assert.assertEquals(adminPage.text, "Success");
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	@DataProvider
	 public static Object[][] getTest01Data()
	 {
	 	Object data[][] =  ReadExcel.getTestData("test01");
	 	return data;
	 }
}