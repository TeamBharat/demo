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


	@FindBy(xpath = "//span[normalize-space()='From']")
	private WebElement fromCityLabel;

	@FindBy(xpath = "//input[@placeholder='From']")
	private WebElement fromCityValue;

	@FindBy(xpath = "//span[normalize-space()='To']")
	private WebElement toCityLabel;

	@FindBy(xpath = "//input[@placeholder='To']")
	private WebElement toCityValue;

	@FindBy(xpath = "//a[normalize-space()='Search']")
	private WebElement ClickOnSearch;

	public homepage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void SelectFromCity(String fromCity) {
		waitForElementToDisplay(fromCityLabel);
		fromCityLabel.click();
		fromCityValue.clear();
		fromCityValue.sendKeys(fromCity);
		toCityLabel.click();
	}

	public void SelectToCity(String toCity) {
		toCityValue.clear();
		toCityValue.sendKeys(toCity);
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

	public void ClickOnSearchBtn(String toCity) {
		ClickOnSearch.click();
	}


}
