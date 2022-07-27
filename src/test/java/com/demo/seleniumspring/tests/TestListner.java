package com.demo.seleniumspring.tests;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.demo.seleniumspring.util.LogUtil;
import com.demo.seleniumspring.util.ScreenShotUtil;

//@SpringBootTest(classes = {TestListner.class})
public class TestListner implements ITestListener {
	
    
	@Autowired
    private ScreenShotUtil screenShotUtil;
	
	 @Autowired
	 protected ExtentReports extent;
	
	  @Lazy
	  @Autowired
	  private LogUtil log;

	//private static ExtentReports extentreports = extent.createExtentReport();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {

		log.info("created test");
		ExtentTest test = extent
				.createTest(result.getClass().getName() + "::" + result.getMethod().getMethodName());
		extentTest.set(test);

	}

	public void onTestSuccess(ITestResult result) {

		String logText = "<b> Test Method" + result.getMethod().getMethodName() + "Successful </b>";
		extentTest.get().log(Status.PASS, MarkupHelper.createLabel(logText, ExtentColor.GREEN));

	}

	public void onTestSkipped(ITestResult result) {

		String logText = "<b> Test Method" + result.getMethod().getMethodName() + "Skipped </b>";
		extentTest.get().log(Status.SKIP, MarkupHelper.createLabel(logText, ExtentColor.YELLOW));

	}
	
	public void onTestFailure(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summary><b><font color=red" + 
		"Exception Occured, Click on to see the detail:" +"</font></b></summary>"+
		exceptionMessage.replaceAll(",", ",br>")+  " </details> \n"	);
		
		try {
			screenShotUtil.takeScreenShot("image.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String path = "C:\\Users\\smukthaparapu\\Documents\\Latest-69\\SeleniumSpringBoot-poc\\screenshots";
		extentTest.get().fail("<b><font color=red>" + "Failure screenshot"+ "</font></b>",
				MediaEntityBuilder.createScreenCaptureFromPath(path, "image.png").build());
				
		String logText = "<b> Test Method" + methodName + "Failed </b>";
		extentTest.get().log(Status.FAIL, MarkupHelper.createLabel(logText, ExtentColor.RED));
	}
	
	public void onFinish() {
		
		if(extent!=null) {
			extent.flush();
		}
	}

}
