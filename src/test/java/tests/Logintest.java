package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import hooks.base;
import pageobjects.homepage;
import pageobjects.loginpage;


public class Logintest extends base {
	
	@Test
	@Parameters({"Mygateway"})
	public void addTemperature(String gateway) {
		String previousTemp="";
		login.logIntoApplication();
		log("User logged in to the application");
		home.clickOnGateway(gateway);		
		previousTemp=home.UpdatedTemp.getText();
		log("Clicked on " +gateway);		
		log("Current Temperature = "+previousTemp);
		home.addTemperature();
		home.setTemperature();
		Assert.assertEquals(home.isElementDisplayed(home.successMesssage), true);	
		log("Temperature updated successfully");
		driver.navigate().refresh();
		home.clickOnGateway(gateway);
		log("updated Temperature = "+home.UpdatedTemp.getText());
		Assert.assertEquals(Integer.parseInt(home.UpdatedTemp.getText().replace("°F", ""))>Integer.parseInt(previousTemp.replace("°F", "")), true);
		log("Temp increased and updated Successfully");
		home.logout();
		log("Logout succesfully");
	}
	
	@Parameters({"Mygateway"})
	@Test
	public void minusTemperature(String gateway) {
		String previousTemp="";
		login.logIntoApplication();
		log("User logged in to the application");
		home.clickOnGateway(gateway);
		previousTemp=home.UpdatedTemp.getText();
		log("Clicked on " +gateway);	
		log("Current Temperature = "+previousTemp);
		home.minusTemperature();
		home.setTemperature();
		Assert.assertEquals(home.isElementDisplayed(home.successMesssage), true);	
		log("Temperature updated successfully");
		driver.navigate().refresh();
		home.clickOnGateway(gateway);
		log("updated Temperature = "+home.UpdatedTemp.getText());
		Assert.assertEquals(Integer.parseInt(home.UpdatedTemp.getText().replace("°F", ""))<Integer.parseInt(previousTemp.replace("°F", "")), true);
		log("Temp decreased and updated Successfully");
		home.logout();
		log("Logout succesfully");
	}
	
	@Test
	@Parameters({"Mygateway"})
	public void getDeviceCount(String gateway) {
		login.logIntoApplication();
		log("User logged in to the application");
		home.clickOnGateway(gateway);
		home.GetThermostatAndSmartSwitchCount();
		log("Thermostate count = "+home.thermoState);
		log("SmartSwitch count = "+home.smartSwitch);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		
	    driver.quit();
	}
}
