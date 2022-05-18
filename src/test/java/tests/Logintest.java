package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import hooks.base;
import pageobjects.homepage;


public class Logintest extends base {


	@Test
	@Parameters({"Mygateway"})
	public void getDeviceCount(String gateway) {
        homepage.SelectFromCity("New Delhi");
		homepage.SelectToCity("Bengaluru");
        homepage.ClickOnSearchBtn();
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		
	    driver.quit();
	}
}
