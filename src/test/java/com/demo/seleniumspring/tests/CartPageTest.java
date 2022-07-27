package com.demo.seleniumspring.tests;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.seleniumspring.SpringBaseTestNGTest;
import com.demo.seleniumspring.pageObjects.HomePage;
import com.demo.seleniumspring.util.LogUtil;
import com.demo.seleniumspring.util.ScreenShotUtil;

public class CartPageTest extends SpringBaseTestNGTest {
	
	@Autowired
    private HomePage homePage;

    @Lazy // only create the object when needed
    @Autowired
    private ScreenShotUtil screenShotUtil;
    
    @Lazy
    @Autowired
    private LogUtil log;
    
    
    // Login to the Flipkart application and add product to the cart
    
	@Test(priority = 1)
	public void addProductToTheCart() throws IOException, InterruptedException {
		
		report = extent.createTest("Verify Add products to the cart");
		homePage.goToFlipKartHomePage();
		homePage.getLoginPage().loginToFlipkartApp("7893706059", "Sowji@123");
		Thread.sleep(1000);
		Assert.assertTrue(homePage.getLoginPage().isAt());
		Assert.assertTrue(homePage.getLoginPage().getTitleOfTheHomePage().contains("Online Shopping Site for Mobiles"));
		Thread.sleep(3000);
		screenShotUtil.takeScreenShot("Test.png");
		homePage.navigateToHomeDecorProducts();
		homePage.clickOnWallClockProducts();
		homePage.addProductsTotheCart();
		String numberOfItems = homePage.getCartPage().verifyCart();
		System.out.println("Number of items in cart " + numberOfItems);
		Assert.assertTrue(numberOfItems.matches("[1-10]"));
		homePage.logout();
		//homePage.close();
    }
	
	// Login to the Flipkart application and remove added product from the cart
	@Test(priority = 2)
	public void removeAddedProductFromCart() throws InterruptedException, IOException {
		
		report = extent.createTest("Verify remove products to the cart");
		homePage.goToFlipKartHomePage();
		homePage.getLoginPage().loginToFlipkartApp("7893706059", "Sowji@123");
		Thread.sleep(1000);
		Assert.assertTrue(homePage.getLoginPage().isAt());
		Assert.assertTrue(homePage.getLoginPage().getTitleOfTheHomePage().contains("Online Shopping Site for Mobiles"));
		Thread.sleep(3000);
		screenShotUtil.takeScreenShot("Test.png");
		String numberOfItems = homePage.getCartPage().verifyCart();
		System.out.println("Number of items in cart " + numberOfItems);
		Assert.assertTrue(numberOfItems.matches("[1-3]"));
		homePage.getCartPage().verifyRemoveAddedProductFromCart();
		String cartItemsCount = homePage.getCartPage().navigateToMainHomePage();
		System.out.println("After product removing the cart : " + cartItemsCount);
		Assert.assertTrue(cartItemsCount.matches("[0-10]"));
		homePage.logout();
		homePage.close();
		
	}

}
