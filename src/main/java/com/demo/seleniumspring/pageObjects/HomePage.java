package com.demo.seleniumspring.pageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.demo.seleniumspring.basepage.Base;


@Lazy
@Scope("prototype")
@Component 
public class HomePage extends Base {
	

	@Autowired
	private ProductsSearchPage searchComponent;

	@Autowired
	private CartPage cartPage;

	@Autowired
	private LoginPage loginPage;

	@Value("${application.url}")
	private String url;

	@FindBy(xpath = "//div[text()='Flipkart']")
	private WebElement lnkFlipKart;

	@FindBy(xpath = "//div[text()='Logout']")
	private WebElement lnkLogout;

	@FindBy(xpath = "//div[text()='Home']")
	private WebElement lnkHome;

	@FindBy(xpath = "//a[text()='Home Decor']")
	private WebElement lnkHomeDecor;

	@FindBy(xpath = "//a[text()='Clocks']")
	private WebElement lnkClocks;

	@FindBy(xpath = "//a[@title='Wall Clocks']")
	private WebElement lnkWallClocks;

	@FindBy(xpath = "//div[@class='_13oc-S']/div[1]")
	private List<WebElement> ResultsPage;

	@FindBy(xpath = "//ul[@class='row']/li[1]")
	private WebElement btnAddBasket;

	@FindBy(xpath = "//span[text()='Cart']/preceding-sibling::div")
	private WebElement lnkCartItemsNumber;

	// launch website
	public void goToFlipKartHomePage() {

		this.driver.manage().window().maximize();
		this.driver.get(url);
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void logout() throws InterruptedException {

		moveToElement(lnkFlipKart);
		Thread.sleep(3000);
		moveToElement(lnkLogout);
		lnkLogout.click();
		Thread.sleep(3000);

	}

	public void navigateToHomeDecorProducts() throws InterruptedException {

		moveToElement(lnkHome);
		moveToElement(lnkHomeDecor);
		moveToElement(lnkClocks);
		lnkClocks.click();
		this.wait.until(driver -> this.lnkWallClocks.isDisplayed());
		lnkWallClocks.click();

	}

	public void clickOnWallClockProducts() throws InterruptedException {

		this.driver.navigate().refresh();
		this.wait.until(driver -> this.ResultsPage.get(0).isDisplayed());
		try {
			ResultsPage.get(0).click();
			Thread.sleep(3000);
			System.out.println(this.driver.getTitle());
		} catch (StaleElementReferenceException e) {
			System.out.println("product not clicked");
		}
	}

	public void addProductsTotheCart() throws InterruptedException {

		String parentWindow = driver.getWindowHandle();
		switchToWindows(parentWindow, btnAddBasket);
	}

	public ProductsSearchPage getSearchComponent() {
		return searchComponent;
	}

	public LoginPage getLoginPage() {
		return loginPage;
	}

	public CartPage getCartPage() {
		return cartPage;
	}

	public boolean isAt() {
		return this.searchComponent.isAt();
	}

	public void close() {
		this.driver.quit();
	}
}
