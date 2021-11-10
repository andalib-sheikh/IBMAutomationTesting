package com.ibm.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenshot {

	public static void captureScreenshot(WebDriver driver, String screenshotName , String testName) throws Exception
	{
		try {
			String screenshotDirectory="./ImpetusTestScreenshots";
			String screenshotTestFolder=screenshotDirectory+"/"+testName;
			String screenshotname=screenshotTestFolder+"/"+screenshotName+".png";
			Path path1=Paths.get(screenshotDirectory);
			Path path2=Paths.get(screenshotTestFolder);
			if(!Files.exists(path1))
			{
				Files.createDirectory(path1);
				Files.createDirectory(path2);
			}
			if(!Files.exists(path2))
			{
				Files.createDirectory(path2);
			}
			
			TakesScreenshot ss = ((TakesScreenshot)driver);
			File srcFile=ss.getScreenshotAs(OutputType.FILE);
			File DestFile=new File(screenshotName);
			FileUtils.copyFile(srcFile, DestFile);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
