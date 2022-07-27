package com.demo.seleniumspring.tests;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.seleniumspring.SpringBaseTestNGTest;
import com.demo.seleniumspring.pageObjects.HomePage;
import com.demo.seleniumspring.util.ScreenShotUtil;

public class ProductsSearchPageTest extends SpringBaseTestNGTest {
	
   // used for dependency injection , auto-wire the dependent bean object/constructor/property to target bean
    @Autowired
    private HomePage homePage;

    @Lazy // only create the object when needed
    @Autowired
    private ScreenShotUtil screenShotUtil;
    
    
    // Login to the Flipkart application and search the product
    
	@Test
	public void searchTheProductAndClickOnFirstResult() throws IOException, InterruptedException {
		
		report = extent.createTest("Verify searching the products ");
		homePage.goToFlipKartHomePage();
		homePage.getLoginPage().loginToFlipkartApp("7893706059", "Sowji@123");
		Thread.sleep(1000);
		Assert.assertTrue(homePage.getLoginPage().isAt());
		Assert.assertTrue(homePage.getLoginPage().getTitleOfTheHomePage().contains("Online Shopping Site for Mobiles"));
		Thread.sleep(3000);
		screenShotUtil.takeScreenShot("Test.png");
		homePage.getSearchComponent().search("televesions");
	    Assert.assertTrue(this.homePage.getSearchComponent().isAt());
		homePage.logout();
		homePage.close();
		
	}
	
}
