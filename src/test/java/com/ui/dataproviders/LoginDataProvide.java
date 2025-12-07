package com.ui.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.Utility.CSVReaderUtility;
import com.Utility.ExcelReaderUtility;
import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;

public class LoginDataProvide {
@DataProvider(name = "LoginTestDataProvider")
	public Iterator<Object[]> loginDataProvider() throws FileNotFoundException {
		Gson gson = new Gson();
		File testDataFile = new File(System.getProperty("user.dir")+ "\\testData\\loginData.json");
		FileReader fileReader = new FileReader(testDataFile);
		TestData data = gson.fromJson(fileReader, TestData.class); //deserilization
		
		
		List<Object[]>dataToReturn =new ArrayList<Object[]>();
		for(User user :data.getData()) {
			dataToReturn.add(new Object[] {user});
			
		}
		return dataToReturn.iterator();

	}
	@DataProvider(name = "LoginTestCSVDataProvider")
	public Iterator<User> loginCsvDataProvider() {
	return	CSVReaderUtility.readCSVFile("loginData.csv");
	}
	
	@DataProvider(name = "LoginTestExcelDataProvider")
	public Iterator<User> loginExcelDataProvider() {
	return	ExcelReaderUtility.readExcelFile("LoginData.xlsx");
	}
	
}
