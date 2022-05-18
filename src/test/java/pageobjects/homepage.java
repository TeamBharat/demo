package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hooks.base;

public class homepage {

	 WebDriver driver;
 	public int thermoState;
 	public int smartSwitch;
	
	  @FindBy(xpath = "//button[contains(text(),'TESTBED-STAGE')]")
	  WebElement testBedStage;
	  
	  @FindBy(xpath = "(//tbody/tr/td[text()='Thermostatdevice']/following-sibling::td/descendant::img[2])[1]")
	  WebElement temperature;
	  
	  @FindBy(xpath = "//button[text()='+']")
	  WebElement addTemperatureButton;
	  
	  @FindBy(xpath = "//button[text()='-']")
	  WebElement minusTemperatureButton;

	  @FindBy(xpath = "//div/button[contains(@class,'temp-set-btn')]")
	  WebElement setTemperatureButton;
	  
	  @FindBy(xpath = "//span[contains(text(),'Device temperature update successfully')]")
	 public WebElement successMesssage;
	  
	  @FindBy(xpath = "(//span[contains(@class,'avatar')])[2]")
      public WebElement profile;
	  
	  @FindBy(xpath = "//span[contains(text(),'Logout')]")
      public WebElement logout;
	  
	  @FindBy(xpath = "//*[@data-icon='close']")
      public WebElement closeButton;
	  
	  @FindBy(xpath = "(//*[contains(@class,'temperature-display')]/descendant::p)[2]")
      public WebElement Temperarture;
	  
	  @FindBy(xpath = "(//tbody/tr/td[text()='Thermostatdevice']/preceding-sibling::td[1])[1]")
      public WebElement UpdatedTemp;
	  
	  @FindBy(xpath = "//tbody/tr/td[text()='Thermostatdevice']/following-sibling::td/descendant::img[2]")
      public List<WebElement> ThermostatDevice;
	  
	  @FindBy(xpath = "//tbody/tr/td[text()='Smartswitch']/following-sibling::td/descendant::img[2]")
      public List<WebElement> Smartswitch;
	  
	  @FindBy(xpath = "//li[@title='Next Page'][@aria-disabled='false']")
      public WebElement NextButton;
	  
	  @FindBy(xpath = "//li[contains(@class,'pagination-item')]")
      public List<WebElement> Rows;
	  
	  @FindBy(xpath = "//th[text()='Device Name']")
      public WebElement DeviceName;
	  
	  @FindBy(xpath = "//span[contains(text(),'Home')]")
      public WebElement Home;
	  
	  @FindBy(xpath = "//label[@for='toggle1']")
      public WebElement Toggle;
	  
	  @FindBy(xpath = "//button[@aria-label='Close']")
      public WebElement CloseButton;
	  
	  


	    public homepage(WebDriver driver){
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	    
	    public void clickOnGateway(String gateway) {
	    	waitForElementToDisplay(testBedStage);
	    	driver.findElement(By.xpath("//button[contains(text(),'"+gateway+"')]")).click();
	    	waitForElementToDisplay(DeviceName);
	    }
	    
	    public void minusTemperature() {
	    	waitForElementToDisplay(temperature);
	    	temperature.click();
	    	waitForElementToDisplay(minusTemperatureButton);
	    	minusTemperatureButton.click();
	    }
	    
	    public void addTemperature() {
	    	waitForElementToDisplay(temperature);
	    	temperature.click();
	    	waitForElementToDisplay(addTemperatureButton);
	    	closeButton.click();
	    	waitForElementToDisplay(temperature);
	    	temperature.click();
	    	waitForElementToDisplay(addTemperatureButton);
	    	addTemperatureButton.click();
	    }
	    
	    
	    public void setTemperature() {
	    	setTemperatureButton.click();
	    }
	    
	    public void waitForElementToDisplay(WebElement element) {
	    	WebDriverWait wait = new WebDriverWait(driver,30);
	    	wait.until(ExpectedConditions.visibilityOf(element));
	    }
	    
	    public boolean isElementDisplayed(WebElement element) {
	    	try {
	    		return element.isDisplayed();
	    	}catch(Exception e) {
	    		return false;
	    	}
	    }
	    
	    public void logout() {
	    	profile.click();
	    	waitForElementToDisplay(logout);
	    	logout.click();
	    }
	    
	    public void GetThermostatAndSmartSwitchCount() { 
	    	for(int i=0;i<Rows.size();i++) {
	    		thermoState=thermoState+ThermostatDevice.size();
	    		smartSwitch=smartSwitch+Smartswitch.size();
	    		for(int j=0;j<Smartswitch.size();j++) {
	    			Smartswitch.get(j).click();	
	    			Toggle.click();
	    			CloseButton.click();
	    		}
	    		try {
	    			NextButton.click();
	    		}catch(Exception e)
	    		{
	    			break;
	    		}
	    	}
	    	
	    }
	    


}
