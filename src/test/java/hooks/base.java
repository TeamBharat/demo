package hooks;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import pageobjects.homepage;
import pageobjects.loginpage;


public class base {

	public WebDriver driver;
	public static ExtentReports extent;
	public ExtentTest logger;
	public loginpage login;
	public homepage home;

	@BeforeSuite
	public void beforeEachTest() {
		 getInstance();
	}

	
	public ExtentReports getInstance() {
		if (extent == null) {
			extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/ExtentReport.html", true);
			extent.loadConfig(new File(System.getProperty("user.dir") + "//extent-config.xml"));
		}
		return extent;
	}
	
	public void log(String data) {
		Reporter.log(data);
		logger.log(LogStatus.INFO, data);
	}


	public void StartTesting(String TestName) {
		ExtentReports rep = getInstance();
		logger = rep.startTest(TestName);
	}

	@AfterTest
	public void afterEachTest() {
		System.out.println("This is exceuted after each Test");
	}
	
	public String getScreenShot(String fileName) throws IOException {
		String pathScreenShotsFolder = System.getProperty("user.dir");

		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(pathScreenShotsFolder + "/test-output/" + fileName + ".jpg"));
			fileName = pathScreenShotsFolder + "/" + fileName + ".jpg";
			return fileName;
		} catch (Exception e) {
		}
		return fileName;
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		
		
		if (logger==null){
			logger=extent.startTest("Starting Test");
		}			 		
		if (result.getStatus() == ITestResult.FAILURE) {		
			logger.log(LogStatus.FAIL, "Test Case " + result.getName() + " is Failed");
			String screenshot_path = getScreenShot(result.getName());
			String image = logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.FAIL, "Title verification", image);
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case " + result.getName() + " is Skipped");
			String screenshot_path = getScreenShot(result.getName());
			String image = logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.SKIP, "Title verification", image);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "Test Case " + result.getName() + " is passed");
			String screenshot_path = getScreenShot(result.getName());
			String image = logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.PASS, "Title verification", image);
		}
		extent.endTest(logger);
		driver.quit();
	}
	
    @BeforeMethod
	public void setUp(Method method) {
    	System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://staging.zomepower.com/");
		login = new loginpage(driver);
		home=new homepage(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		StartTesting(method.getName());
	}
    
    @AfterTest
    public void afterSuite() {
    	extent.flush();
    	extent.close();
    }
}
