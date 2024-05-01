package com.swaglabs.qa.listeners;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import com.swaglabs.qa.utils.ExtentReporter;
import com.swaglabs.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class MyListeners implements ITestListener {

    ExtentReports extentReport;
    ExtentTest extentTest;


    @Override
    public void onStart(ITestContext context) {

        extentReport = ExtentReporter.generateExtentReport();

    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReport.createTest(result.getName());
        extentTest.log(Status.INFO, result.getName() + " started Executing");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest = extentReport.createTest(result.getName());
        extentTest.log(Status.PASS, result.getName() + " got successfully executing");

    }

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = null;
        ;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String destinationScreenshotPath = Utilities.captureScreenShots(driver, result.getName());
        extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL, result.getName() + " got failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest = extentReport.createTest(result.getName());
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP, result.getName() + " got skipped");

    }


    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
        String pathofextentReport = "/Users/hemalathaa/Desktop/HemaWorkSpace/EnableSwagLabsProject/test-output/extentReport.html";
        File extentReport = new File(pathofextentReport);
        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


