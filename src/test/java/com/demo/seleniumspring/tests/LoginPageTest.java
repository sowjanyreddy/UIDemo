package com.demo.seleniumspring.tests;

import com.demo.seleniumspring.SpringBaseTestNGTest;
import com.demo.seleniumspring.pageObjects.HomePage;
import com.demo.seleniumspring.util.LogUtil;
import com.demo.seleniumspring.util.ScreenShotUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginPageTest extends SpringBaseTestNGTest {
	
	@Autowired
    private HomePage homePage;

    @Lazy // only create the object when needed
    @Autowired
    private ScreenShotUtil screenShotUtil;
    
    @Lazy
    @Autowired
    private LogUtil log;
    
    // Login to the Flipkart application and verify the title
    
	@Test(priority = 1)
	public void LoginTest() throws IOException, InterruptedException {
		
		log.startTestCase("Login To flipkart Applgggggggggggggggication");
		report = extent.createTest("Login To FlipKart Application");
		homePage.goToFlipKartHomePage();
		report.info("Navigated to Homepage");
		homePage.getLoginPage().loginToFlipkartApp("7893706059", "Sowji@123");
		Thread.sleep(1000);
		Assert.assertTrue(homePage.getLoginPage().isAt());
		Assert.assertTrue(homePage.getLoginPage().getTitleOfTheHomePage().contains("Online Shopping Site for Mobiles"));
		log.info("login to application successfully");
		report.info("Logined to the Application");
		Thread.sleep(3000);
		screenShotUtil.takeScreenShot("Test.png");
		homePage.logout();
		log.endTestCase("Logout from application");
		homePage.close();
    }
}
