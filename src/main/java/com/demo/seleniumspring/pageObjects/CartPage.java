package com.demo.seleniumspring.pageObjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.demo.seleniumspring.basepage.Base;

@Lazy
@Scope("prototype")
@Component 
public class CartPage extends Base {
	
	@FindBy(xpath = "//span[text()='Cart']/preceding-sibling::div")
    private WebElement lnkCartItemsNumber;
	
	@FindBy(xpath = "//div[@class='_1AtVbE col-12-12']/div")
    private WebElement cartProductsPage;
	
	@FindBy(xpath = "//div[text()='Remove']")
    private WebElement lnkRemove;
	
	@FindBy(xpath = "//img[@title='Flipkart']")
    private WebElement imgLogo;
	
	@FindBy(xpath = "//div[@class='td-FUv WDiNrH']/div[text()='Remove']")
    private WebElement btnRemove;
	
	
	
	public void verifyRemoveAddedProductFromCart() throws InterruptedException {
		
		lnkCartItemsNumber.click();
		this.driver.navigate().refresh();
		Thread.sleep(5000);
		List<WebElement> clocks = this.driver.findElements(By.xpath("//div[@class='_1AtVbE col-12-12']/div"));
		for(WebElement ele : clocks) {
			String text = ele.getText();
			if(text.contains("Clock")) {
				lnkRemove.click();
				btnRemove.click();
				break;
			}
		}
	}
	
	public String navigateToMainHomePage() throws InterruptedException {
		
		imgLogo.click();
		driver.navigate().refresh();
		Thread.sleep(3000);
		String itemsCount = lnkCartItemsNumber.getText();
		Thread.sleep(3000);
		return itemsCount;
	}
	
	
	
	public String verifyCart() {

		this.driver.navigate().refresh();
		String cartItemsNumber = lnkCartItemsNumber.getText();
		return cartItemsNumber;

	}

	
	public boolean isAt() {
		return false;
	}

	
}
