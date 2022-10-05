package com.salesforce.test.utility;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class GenerateReports {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static GenerateReports obj;
	private GenerateReports() {
		
	}
	public static GenerateReports getInstance() {
		if(obj==null) {
			obj=new GenerateReports();
		}
		return obj;
	}
	public void startExtentReports() {
		htmlReporter =new ExtentHtmlReporter(Constants.GENERATE_REPORT_PATH);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Salesforce");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "TestSree");
		htmlReporter.config().setDocumentTitle("Test Execution Report");
		htmlReporter.config().setReportName("SalesForce regression tests");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
	}
	public void startsingleTestReport(String testname) {
		logger=extent.createTest(testname);
	}
	
	public void logTestinfo(String message) {
		logger.log(Status.INFO,  message);
	}
	public void logTestpassed(String testcaseName) {
		logger.log(Status.PASS,MarkupHelper.createLabel(testcaseName + "is passTest",ExtentColor.GREEN )) ;
		
	}
	public void logTestfail(String testcaseName) {
		logger.log(Status.FAIL,MarkupHelper.createLabel(testcaseName  + "is Failed",ExtentColor.RED));
	}
	public void logTestskip(String testcaseName) {
		logger.log(Status.SKIP, MarkupHelper.createLabel(testcaseName  +"is skipped ", ExtentColor.BLACK));
	}
	
	public void endReport() {
		extent.flush();
	}
	public void logTestFailedWithException(Exception exception) {
		logger.log(Status.ERROR,exception);

	}
   public void attachScreeshot(String path) throws IOException {
		
		logger.addScreenCaptureFromPath(path);
	}
	
}

