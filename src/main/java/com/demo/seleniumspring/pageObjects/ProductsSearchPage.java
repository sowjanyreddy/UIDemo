package com.demo.seleniumspring.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.demo.seleniumspring.basepage.Base;

import java.util.List;

// allows Spring Framework automatically detects classes annotated with @Component

@Lazy
@Scope("prototype")
@Component
public class ProductsSearchPage extends Base {

    @FindBy(xpath = "//input[@title='Search for products, brands and more']")
    private WebElement searchBox;

    @FindBy(xpath="//div[@class='MIXNux']")
    private WebElement searchResults;
    
    @FindBy(xpath = "//span[contains(text(),'Showing')]")
    private WebElement elementPresent;

    public void search(final String keyword) {
    	
    	this.searchBox.click();
        this.searchBox.sendKeys(keyword);
        this.searchBox.sendKeys(Keys.ENTER);
        
    }
    
    public void clickOnSearchResult() throws InterruptedException {
    	
    	List<WebElement> list =  this.driver.findElements(By.xpath("//div[@class='MIXNux']"));
    	System.out.println(list.size());
        for(WebElement ele : list ) {
        	System.out.println(ele);
        	Thread.sleep(4000);
        	ele.click();
        	Thread.sleep(4000);
        	break;
        }
    	
    }

    public boolean isAt() {
        return this.wait.until(driver1 -> this.elementPresent.isDisplayed());
    }

	
}
