package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ExtentReport;

import java.io.IOException;

public class listener extends base implements ITestListener {

    public WebDriver driver;
    ExtentReports extentReport = ExtentReport.getExtentReport();
    ExtentTest extentTest;
    Logger logger = LogManager.getLogger(listener.class.getName());
    ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        // String testName = result.getName();
        extentTest = extentReport.createTest(result.getName()+" execution started");
        extentTestThread.set(extentTest);
        logger.info("\n============== Test '{}' started ==============\n", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS,result.getName()+" Test Passed");
        extentTestThread.get().log(Status.PASS,result.getName()+"Test Passed");
        logger.info("\n============== Test '{}' passed ==============\n", result.getName());
        //extentTestThread.get().pass(result.getThrowable());
       // extentTestThread.get().pass(result.getThrowable());

    String testMethodName = result.getName();
        try {
            driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String screenshotFilePath = takeScreenshot(testMethodName,driver);
            extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, testMethodName);
        } catch (IOException e) {

            e.printStackTrace();
        }

    }


    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL,result.getName()+" Test Failed");
        extentTestThread.get().log(Status.FAIL,result.getThrowable()+"Test Failed");
        logger.error("\n============== Test '{}' failed ==============\n", result.getName(), result.getThrowable());

        extentTestThread.get().fail(result.getThrowable());
//        extentTest.fail(result.getThrowable());

        String testMethodName = result.getName();

        try {
            driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String screenshotFilePath = takeScreenshot(testMethodName,driver);
            extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, testMethodName);
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {

//        logger.warn("============== Test '{}' skipped ==============", result.getName(), result.getThrowable());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {



    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {



    }

    @Override
    public void onStart(ITestContext context) {

//        logger.info("============== Test Suite '{}' started ==============", context.getName());

    }

    @Override
    public void onFinish(ITestContext context) {

        extentReport.flush();
        logger.info("\n============== Test Suite '{}' finished ==============\n", context.getName());
    }



}

