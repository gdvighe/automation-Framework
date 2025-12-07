package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.Utility.JSONUtility;
import com.Utility.PropertiesUtil;
import com.constant.Env;

public class MyRetryAnalyzer implements IRetryAnalyzer {
//private final static int MAX_NUMBER_OF_ATTEMPTS = Integer.parseInt( PropertiesUtil.readProperty(Env.DEV,"MAX_NUMBER_OF_ATTEMPTS"));

private final static int MAX_NUMBER_OF_ATTEMPTS = JSONUtility.readJSON(Env.QA).getMAX_NUMBER_OF_ATTEMPTS();

	private static int currentAttempts = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (currentAttempts <= MAX_NUMBER_OF_ATTEMPTS) {
			currentAttempts++;
			return true;
		}
		return false;
	}

}
