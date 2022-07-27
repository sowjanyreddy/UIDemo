package com.demo.seleniumspring.pageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.demo.seleniumspring.basepage.Base;


@Lazy
@Scope("prototype")
@Component
public class LoginPage extends Base {
	  
	
	
	 @FindBy(xpath = "//input[@type='text' and @class='_2IX_2- VJZDxU']")
	 private WebElement txtUserName;
	 
	 @FindBy(xpath = "//input[@type='password' and @class='_2IX_2- _3mctLh VJZDxU']")
	 private WebElement txtPassword;
	 
	 @FindBy(xpath = "//button[@type='submit' and @class='_2KpZ6l _2HKlqd _3AWRsL']")
	 private WebElement btnLogin;
	 
	 @FindBy(xpath = "//span[text()='Cart']")
	 private WebElement cart;
	 
	 
	 
	public void loginToFlipkartApp(String userName, String Pwd) {
		
		txtUserName.sendKeys(userName);
		txtPassword.sendKeys(Pwd);
		btnLogin.click();
		
	}
	
	 public String getTitleOfTheHomePage() {
		 
		 System.out.println(driver.getTitle());
		 return this.driver.getTitle();
	    	
	    }

	public boolean isAt() {
		return this.wait.until(driver1 -> this.cart.isDisplayed());
	}

}
