package com.swaglabs.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;



public class ExtentReporter {
	
	public static ExtentReports generateExtentReport(){
		
		ExtentReports extentReport = new ExtentReports();
		
		File extentReportFile = new File(("/Users/hemalathaa/Desktop/HemaWorkSpace/EnableSwagLabsProject/test-output/extentReport.html"));
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("EnableSwagLabsProject");
		sparkReporter.config().setDocumentTitle("EnableSwagLabsProject TestAutomation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		Properties configProp = new Properties();
		File configPropFile = new File(("/Users/hemalathaa/Desktop/HemaWorkSpace/EnableSwagLabsProject/src/main/java/com/swaglabs/qa/config/config.properties"));
		try {
			FileInputStream fisConfigProp = new FileInputStream(configPropFile);
			configProp.load(fisConfigProp);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL",configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Username", configProp.getProperty("SValidUserName"));
		extentReport.setSystemInfo("Password", configProp.getProperty("SValidPassword"));
		extentReport.setSystemInfo("os.name", System.getProperty("os.name"));
		extentReport.setSystemInfo("java.version", System.getProperty("java.version"));

		return extentReport;
		
	}
	

}
