package com.demo.seleniumspring.basepage;


import java.util.NoSuchElementException;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class Base {

    @Autowired
    protected WebDriver driver;

    @Autowired
    protected WebDriverWait wait;
   
    @PostConstruct
    private void init(){
    	
        PageFactory.initElements(this.driver, this);
    }
    
    
    public void moveToElement(WebElement ele) throws InterruptedException {
    	
    	Actions ac = new Actions(driver);
    	try {
			if(ele.isDisplayed()) {
				ac.moveToElement(ele).build().perform();
			}
			else {
				Thread.sleep(5000);
				ac.moveToElement(ele).build().perform();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
    }
    
    
    public void switchToWindows(String parentWindow,WebElement ele) throws InterruptedException {
    	
    	Set<String> allWindowHandles = driver.getWindowHandles();
        // Here we will check if child window has other child windows and will fetch the heading of the child window
           for(String childWindow : allWindowHandles) {
                if (!parentWindow.equalsIgnoreCase(childWindow)) {
                      driver.switchTo().window(childWindow);
                      Thread.sleep(5000);
                      System.out.println("child window" + driver.getTitle());
                      wait.until(driver1 -> ele.isDisplayed());
                      ele.click();
                      Thread.sleep(1000);
                      driver.close();
            }
              
        }
       driver.switchTo().window(parentWindow);
    }

  
}
