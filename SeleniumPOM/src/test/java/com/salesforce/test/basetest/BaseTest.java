package com.salesforce.test.basetest;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.salesforce.test.utility.CommonUtilities;
import com.salesforce.test.utility.GenerateReports;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	public static Logger logger =LogManager.getLogger(BaseTest.class);
	public static GenerateReports report=null;
	
	@BeforeTest
	public static void setupBeforeTest() {
		report=GenerateReports.getInstance();
		report.startExtentReports();
	}
	
	@Parameters({ "browsername" })
	@BeforeMethod
	
	public static void setUp(String browsername,Method m ) throws InterruptedException {
		logger.info("before method execution started");
		report.startsingleTestReport(m.getName());
		report.logTestinfo("before method execution started");
		getdriver(browsername);
		CommonUtilities CU = new CommonUtilities();
		Properties applicationPropertiesFile = CU.loadFile("applicationProperties");
		String url = CU.getApplicationProperty("url", applicationPropertiesFile);
		gotourl(url);
		//waitUntilPageLoads();
		Thread.sleep(2000);
	}
	
	@AfterMethod
	public static void tearDown() throws InterruptedException {
		logger.info("after method execution is started");
		report.logTestinfo("after method execution is started");
		Thread.sleep(2000);
		closebrowser();
	}	
	@AfterTest
	public static void teardownAfterTest() {
		report.endReport();
	}
	
	public static void getdriver(String browser) {
		switch(browser) {
		case "firefox":
				WebDriverManager.firefoxdriver().setup();
				
		driver=new FirefoxDriver();
						break;
		case "chrome":	
			 	WebDriverManager.chromedriver().setup();
						driver = new ChromeDriver();
						break;
		default: break;
		
		}			 
		
	}
	public static void gotourl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}
	public static void closebrowser() {
		driver.close();
	}
	public static void closeallbrowser() {
		driver.quit();
	}
	public static String getPageTitle() {
		return driver.getTitle();
	}

	private static void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

	}
	public static void ImplicitWaitmethod() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

}
