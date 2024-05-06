package KiranKabothula.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import KiranKabothula.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal(); //Thread safe
	
    @Override
    public void onTestStart(ITestResult result) {
        // This method will be called when a test method starts
    	test = extent.createTest(result.getMethod().getMethodName()); 
    	extentTest.set(test); //unique thread ID
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // This method will be called when a test method is successful
    	extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // This method will be called when a test method fails
    	extentTest.get().fail(result.getThrowable());
    	
    	try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    			
    	String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // This method will be called when a test method is skipped
    }

    // Implement other methods as needed

    // Example of unimplemented method
    @Override
    public void onFinish(ITestContext context) {
        // This method will be called when all the test methods finish
    	extent.flush();
    
    }
}
