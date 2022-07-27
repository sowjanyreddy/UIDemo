package com.demo.seleniumspring;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


// Abstract base test class which integrates the Spring TestContext Framework with explicit ApplicationContext testing 
// support in a TestNG environment. 
@SpringBootTest
public class SpringBaseTestNGTest extends AbstractTestNGSpringContextTests {
	
	public static ExtentSparkReporter htmlReporter;
	 public static ExtentReports extent;
	 public ExtentTest report;
	

	
	@BeforeTest(alwaysRun = true)
	public void  createExtentReport1() {
	
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	   
		String date = sdf1.format(timestamp);
		String path = System.getProperty("user.dir")+"/reports/POCExtentReport-"+date+".html";
		System.out.println(path);
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/POCExtentReport-"+date+".html");

		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName("POC Test Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);

		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("HostName", "MyHost");
		extent.setSystemInfo("ProjectName", "SpringBoot-POC");
		extent.setSystemInfo("Tester", "Sowjanya");
		extent.setSystemInfo("OS", "Win10");
		extent.setSystemInfo("Browser", "Chrome");
		
	}
	
	@AfterMethod(alwaysRun = true)
    public void endReport(ITestResult result) {
         try {
              if (result.getStatus() == ITestResult.FAILURE) {
            	String methodName = result.getMethod().getMethodName();
          		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
          		report.fail("<details><summary><b><font color=red>" + 
          		"Exception Occured, Click on to see the detail:" +"</font></b></summary>"+
          		exceptionMessage.replaceAll(",", ",br>")+  " </details> \n"	);
          		
          		String logText = "<b> Test Method" + methodName + "Failed </b>";
          		report.log(Status.FAIL, MarkupHelper.createLabel(logText, ExtentColor.RED));
              } 
              else if (result.getStatus() == ITestResult.SUCCESS) {
            	  //test.log(Status.SKIP , "Test case skipped is" + result.getName());
            	  String logText = "<b> Test Method" + result.getMethod().getMethodName() + "Successful </b>";
            	  report.log(Status.PASS, MarkupHelper.createLabel(logText, ExtentColor.GREEN));
              }else if (result.getStatus() == ITestResult.SKIP) {
            	  String logText = "<b> Test Method" + result.getMethod().getMethodName() + "Skipped </b>";
            	  report.log(Status.SKIP, MarkupHelper.createLabel(logText, ExtentColor.YELLOW));
              }
         } catch (Exception e) {
              e.printStackTrace();
         }
    }

    @AfterTest(alwaysRun = true)
    public void endReport() {
         extent.flush();

         }
		
}