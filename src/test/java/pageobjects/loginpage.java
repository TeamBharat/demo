package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hooks.base;

public class loginpage {

	 WebDriver driver;
	  
	
	  @FindBy(xpath = "//input[@type='email']")
	  WebElement email;

	  
	  @FindBy(xpath = "//input[@type='password']")
	  WebElement password;
	  
	  @FindBy(xpath = "//button/span[contains(text(),'LOGIN')]")
	  WebElement login;


	    public loginpage(WebDriver driver){

	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	    
	    public void logIntoApplication() {
	    	waitForElementToDisplay(email);
	    	email.sendKeys("zome_analyst");
	    	password.sendKeys("SKa98n#D");
	    	login.click();
	    }
	    
	    public void waitForElementToDisplay(WebElement element) {
	    	WebDriverWait wait = new WebDriverWait(driver,30);
	    	wait.until(ExpectedConditions.visibilityOf(element));
	    }

}
