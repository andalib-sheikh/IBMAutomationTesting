package com.ibm.utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ibm.base.BasePage;

public class ReadExcel extends BasePage{

	private static Workbook workBook;
	private static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName)
	{
		Object data[][]=null;
		try 
		{
			FileInputStream fs=new FileInputStream(prop.getProperty("TestDataExcelPath"));
			workBook=WorkbookFactory.create(fs);
			sheet=workBook.getSheet(sheetName);
			data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int i=0;i<sheet.getLastRowNum();i++)
			{
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
				{
					try {
						data[i][j]=sheet.getRow(i+1).getCell(j).toString();
						System.out.println(data[i][j]);
					}
					catch(NullPointerException e)
					{
						data[i][j]="null";
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}
}